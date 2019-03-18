/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yourorghere;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;


class TRANSFORM implements GLEventListener {
/**
 * Interface to the GLU library.
 */
private GLU glu;
/**
 * Take care of initialization here.
 */
//Arraylost that stroes the polygon boundary pixels
ArrayList<Long> ax = new ArrayList<Long>();
ArrayList<Long> ay = new ArrayList<Long>();
//Arraylist storing the couloured pixel
ArrayList<Long> ny = new ArrayList<Long>();
ArrayList<Long> nx = new ArrayList<Long>();
public Stack<Long>  bx = new Stack<Long>();
public Stack<Long>  by = new Stack<Long>();
public int  count = 0;
@Override
public void init(GLAutoDrawable gld) {
GL gl = gld.getGL();
glu = new GLU();

gl.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);
gl.glViewport(0,0,640,480);
gl.glMatrixMode(GL.GL_PROJECTION);
gl.glLoadIdentity();
glu.gluOrtho2D(0,640,0,480);
}

/**
 * Take care of drawing here.
 */
@Override
@SuppressWarnings("empty-statement")
public void display(GLAutoDrawable drawable) {
GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(1.0f, 0.00f, 0.0f );


    //
    Scanner sc = new Scanner(System.in);
    int a =sc.nextInt();
    if(a==1)
    scaling(gl);
    if(a==2)
    rotate(gl);
    if(a==3)
        translation(gl);
  ///  reflection(gl);
/*CALLING THE SEEDFILL ALGORITHM WITH SEED PIXEL AS 30 30*/
//seedfill(gl,30,30);
    
}

public  void translation(GL gl)
    {
       
        int pcount = 4;//sc.nextInt();
        int[][] points ={{100,100,1},{200,100,1},{200,200,1},{100,200,1}};
   
        int tx = 10; //sc.nextInt();
        int ty = 10; //sc.nextInt();
        int[][] trans_mat = {{1,0,0},{0,1,0},{tx,ty,1}};
        int[][] result_points = new int[pcount][3];
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                int sum = 0;
                for(int k = 0;k<3;k++)
                {
                    sum  = sum + points[i][k]*trans_mat[k][j];
                }
                result_points[i][j] = sum;
            }
        }
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                    System.out.print(result_points[i][j]+" ");
            }
            System.out.println("");
        }
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
            BRE(points[i][0],points[i][1],points[i+1][0],points[i+1][1],gl);
            else
            BRE(points[i][0],points[i][1],points[0][0],points[0][1],gl);
        }
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
            BRE(result_points[i][0],result_points[i][1],result_points[i+1][0],result_points[i+1][1],gl);
            else
                BRE(result_points[i][0],result_points[i][1],result_points[0][0],result_points[0][1],gl);
        }
    }
public void scaling(GL gl)
{
        
        int pcount = 4;//sc.nextInt();
        int[][] points ={{100,100,1},{200,100,1},{200,200,1},{100,200,1}};
        int sx = 2; //sc.nextInt();
        int sy = 2; //sc.nextInt();
        int[][] trans_mat = {{sx,0,0},{0,sy,0},{0,0,1}};
        int[][] result_points = new int[pcount][3];
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                int sum = 0;
                for(int k = 0;k<3;k++)
                {
                    sum  = sum + points[i][k]*trans_mat[k][j];
                }
                result_points[i][j] = sum;
            }
        }
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                    System.out.print(result_points[i][j]+" ");
            }
            System.out.println("");
        }
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
            BRE(points[i][0],points[i][1],points[i+1][0],points[i+1][1],gl);
            else
            BRE(points[i][0],points[i][1],points[0][0],points[0][1],gl);
        }
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
            BRE(result_points[i][0],result_points[i][1],result_points[i+1][0],result_points[i+1][1],gl);
            else
                BRE(result_points[i][0],result_points[i][1],result_points[0][0],result_points[0][1],gl);
        }
}
public void rotate(GL gl)
{
         int pcount = 4;//sc.nextInt();
        int[][] points ={{300,100,1},{400,100,1},{400,200,1},{300,200,1}};
        int angle = 45;
        double ang = 45.0;
        double b = Math.toRadians(ang);
    double co = Math.cos(b);
    double si = Math.sin(b);
   
    double[][] rotate = {{co,-si,1},{si,co,1},{0,0,1}};
        double[][] result_points = new double[pcount][3];
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                double sum = 0;
                for(int k = 0;k<3;k++)
                {
                    //System.out.println(sum);
                    sum  = sum + (points[i][k]*rotate[k][j]);
                    
                }
                result_points[i][j] = sum;
            }
        }
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
                BRE((int)points[i][0],(int)points[i][1],(int)points[i+1][0],(int)points[i+1][1],gl);
            else
                BRE((int)points[i][0],(int)points[i][1],(int)points[0][0],(int)points[0][1],gl);
        }
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
                 BRE((int)result_points[i][0],(int)result_points[i][1],(int)result_points[i+1][0],(int)result_points[i+1][1],gl);
            else
                 BRE((int)result_points[i][0],(int)result_points[i][1],(int)result_points[0][0],(int)result_points[0][1],gl);
        }
}
public void reflection(GL gl)
{
    int pcount = 4;//sc.nextInt();
        int[][] points ={{340,340,1},{300,340,1},{300,440,1},{340,440,1}};
        int cx =300 , cy = 300;
        int tx = cx - 340; //sc.nextInt();
        int ty = cy - 340; //sc.nextInt();
        int[][] trans_mat = {{1,0,0},{0,1,0},{tx,ty,1}};
        int[][] result_points = new int[pcount][3];
        for(int i=0;i<pcount;i++)
        {
            if(i!=pcount-1)
            BRE(points[i][0],points[i][1],points[i+1][0],points[i+1][1],gl);
            else
            BRE(points[i][0],points[i][1],points[0][0],points[0][1],gl);
        }
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                int sum = 0;
                for(int k = 0;k<3;k++)
                {
                    sum  = sum + points[i][k]*trans_mat[k][j];
                }
                result_points[i][j] = sum;
            }
        }
        int[][] trans_mat1 = {{1,0,0},{0,1,0},{tx,ty*-1,1}};
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                int sum = 0;
                for(int k = 0;k<3;k++)
                {
                    sum  = sum + points[i][k]*trans_mat1[k][j];
                }
                result_points[i][j] = sum;
            }
        }
        for(int i=0;i<pcount;i++)
        {
            for(int j=0;j<3;j++)
            {
                    System.out.print(result_points[i][j]+" ");
            }
            System.out.println("");
        }
        
}
@Override
public void reshape(GLAutoDrawable drawable, int x, int y, int width,
int height) {
}

@Override
public void displayChanged(GLAutoDrawable drawable,
boolean modeChanged, boolean deviceChanged) {
}

private void BRE(int x1,int y1,int x2,int y2,GL gl) {
     /*BRESENHAMS LINE DRAWING ALGORITHM*/
        gl.glBegin(GL.GL_POINTS);
        int X = x1;
        int Y = y1;
        int dx = Math.abs(x2-x1);
        int dy = Math.abs(y2-y1);
        int s1,s2;
        if(x2-x1>0) s1=1;
        else if(x2-x1==0) s1=0;
        else s1=-1;
        if(y2-y1>0) s2=1;
        else if(y2-y1==0) s2=0;
        else s2=-1;
        
        int intr=0;
        if(dx<dy) {
        int temp = dx;
        dx = dy;
        dy=dx;
        intr=1;
        }
        int e1 = 2*dy - dx;
        for( int i=0;i<=dx;i++)
        {
            gl.glVertex2i((int)X, (int)Y);
            
            while(e1>0)
            {
                if(intr==1) X+=s1;
                else Y+=s2;
                e1-=2*dx;
            }
            if(intr==0) X+=s1;
                else Y+=s2;
            e1+=2*dy;
        }
        
        gl.glEnd();
}



public void dispose(GLAutoDrawable arg0)
{

}
}
public class prac6_transform
{
public static void main(String args[])
{
GLCapabilities capabilities=new GLCapabilities();
// The canvas
final GLCanvas glcanvas=new GLCanvas(capabilities);
TRANSFORM b=new TRANSFORM();
glcanvas.addGLEventListener(b);
glcanvas.setSize(400, 400);
final JFrame frame=new JFrame("Basic frame");
//adding canvas to frame
frame.add(glcanvas);
frame.setSize(640,480);
frame.setVisible(true);
}
}