package org.yourorghere;


import java.util.ArrayList;
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


class ASSIGN implements GLEventListener {
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
public long  count = 0;
@Override
public void init(GLAutoDrawable gld) {
GL gl = gld.getGL();
glu = new GLU();

gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
gl.glViewport(0,0,640,480);
gl.glMatrixMode(GL.GL_PROJECTION);
gl.glLoadIdentity();
glu.gluOrtho2D(0,1000,0,1000);
}

/**
 * Take care of drawing here.
 */

@Override
@SuppressWarnings("empty-statement")

public void display(GLAutoDrawable drawable) {
GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(1.0f, 0.00f, 1.0f );
//BRE(200,200,200,600,gl);
//BRE(200,600,600,600,gl);
//BRE(600,600,600,200,gl);
//BRE(600,200,200,200,gl);
int sx=1,sy=400;

for(int i=0;i<2;i++){
drawbox(sx,sy,gl);
sx+=200;
}
sx=100;
sy=500;
for(int i=0;i<=1;i++){
drawbox(sx,sy,gl);
sx+=200;
}
sx=1;
sy=600;
for(int i=0;i<2;i++){
drawbox(sx,sy,gl);
sx+=200;
}
sx=100;
sy=700;
for(int i=0;i<=1;i++){
drawbox(sx,sy,gl);
sx+=200;
}
drawboxbig(gl);
}

public void drawbox(int sx,int sy,GL gl)
{
    double ang = 45.0;
    int[][] points = {{sx,sy,1},{sx+100,sy,1},{sx+100,sy+100,1},{sx,sy+100,1}};
    double[][] points_1 = new double[4][3];
    double b = Math.toRadians(ang);
    double co = Math.cos(b);
    double si = Math.sin(b);
    System.out.println(co +" " +si);
    double[][] rotate = {{co,-si,1},{si,co,1},{0,0,1}};
   for(int i=0;i<4;i++)
        {
            for(int j=0;j<3;j++)
            {
                double sum = 0;
                for(int k = 0;k<3;k++)
                {
                    //System.out.println(sum);
                    sum  = sum + (points[i][k]*rotate[k][j]);
                    
                }System.out.println(sum);
                points_1[i][j] = sum;
                //System.out.print(points[i][j]+" ");
            }
           // System.out.println("");
        }
    //System.out.println("");
    for(int i=0;i<4;i++)
        {
            if(i!=3)
                BRE((int)points_1[i][0],(int)points_1[i][1],(int)points_1[i+1][0],(int)points_1[i+1][1],gl);
            else
                BRE((int)points_1[i][0],(int)points_1[i][1],(int)points_1[0][0],(int)points_1[0][1],gl);
        }
    int midx = (int)(points_1[0][0]+points_1[2][0])/2;
    int midy = (int)(points_1[0][1]+points_1[2][1])/2;
    seedfill(gl,midx,midy);
}
public void drawboxbig(GL gl)
{
    
    int[][] points = {{1,400,1},{401,400,1},{400,800,1},{1,800,1}};
    double[][] points_1 = new double[4][3];
    double b = Math.toRadians(45);
    double co = Math.cos(b);
    double si = Math.sin(b);
    System.out.println(co +" " +si);
    double[][] rotate = {{co,-si,1},{si,co,1},{0,0,1}};
   for(int i=0;i<4;i++)
        {
            for(int j=0;j<3;j++)
            {
                double sum = 0;
                for(int k = 0;k<3;k++)
                {
                    //System.out.println(sum);
                    sum  = sum + (points[i][k]*rotate[k][j]);
                    
                }System.out.println(sum);
                points_1[i][j] = sum;
                System.out.print(points[i][j]+" ");
            }
            System.out.println("");
        }
    System.out.println("");
    for(int i=0;i<4;i++)
        {
            if(i!=3)
                BRE((int)points_1[i][0],(int)points_1[i][1],(int)points_1[i+1][0],(int)points_1[i+1][1],gl);
            else
                BRE((int)points_1[i][0],(int)points_1[i][1],(int)points_1[0][0],(int)points_1[0][1],gl);
        }
    int midx = (int)(points_1[0][0]+points_1[2][0])/2;
    int midy = (int)(points_1[0][1]+points_1[2][1])/2;
   // seedfill(gl,midx,midy);
}
public void seedfill(GL gl,int xx,int yy)
{
    long co = 1;
    long x =xx , y=yy;
    bx.push(x);
    by.push(y);
    gl.glColor3f(0.0f, 0.00f, 0.0f );
    gl.glBegin(GL.GL_POINTS);
    while(bx.empty()!=true && by.empty()!=true)
    {
    x = bx.pop();
    y = by.pop();
    gl.glVertex2i((int)x, (int)y);
    ny.add(y);
    nx.add(x);
    long savex = x;
    x=x+1;
    while(findb(x,y))
    {
            gl.glVertex2i((int)x, (int)y);
            ny.add(y);
            nx.add(x);
            x = x+1;
    }
    long xright = x-1;
    x = savex ;
    x = x-1;
    while(findb(x,y))
    {
            gl.glVertex2i((int)x, (int)y);
            ny.add(y);
            nx.add(x);
            x = x-1;
    }
    long xleft = x+1;
    x = savex;
    x = xleft;
    y = y+1;
    while(x<=xright)
    {
        long pflag = 0;
        while(findb(x,y)&&findn(x,y) && x<xright)
        {
            if(pflag==0) pflag=1;
            x = x+1;
        }
        if(pflag == 1)
        {
            if(x==xright && findb(x,y)&&findn(x,y))
            {
                bx.push(x);
                by.push(y);
                        co++;
            }
            else
            {
                bx.push(x-1);
                by.push(y);
                   co++;
                }
            pflag=0;
        }    
         long xcenter = x;
         while(findb(x,y)==false && findn(x,y)==false && x<xright)
         {
             x = x+1;
         }
         if(x==xcenter ) x=x+1;
    }
     x = xleft;
    y = y-2;
    while(x<=xright)
    {
        long pflag = 0;
        while(findb(x,y) && findn(x,y) && x<xright)
        {
            if(pflag==0) pflag=1;
            x = x+1;
        }
        if(pflag == 1)
        {
            if(x==xright && findb(x,y)&&findn(x,y))
            {
                co++;
                bx.push(x);
                by.push(y);
                        
            }
            else
            {
                co++;
                bx.push(x-1);
                by.push(y);
            }
            pflag = 0;
        }    
         long xcenter = x;
         while(findb(x,y)==false && findn(x,y)==false && x<xright)
         {
             x = x+1;
         }
         if(x==xcenter ) x=x+1;
    }
    
    }
    System.out.println(co);
    gl.glEnd();
}
boolean findn(long x,long y)
{
    /*FUNCTION RETURNS TRUE IF THE PIXEL IS ALREADY COLOURED ELSE FALSE*/
    for(int i=0;i<ny.size();i++)
    {
        if(nx.get(i)==x && ny.get(i)==y)
            return false;
    }
return true;
}
boolean findb(long x,long y)
{
     /*FUNCTION RETURNS TRUE IF THE PIXEL IS BOUNDARY PIXEL ELSE FALSE*/
    for(int i=0;i<ay.size();i++)
    {
        if(ax.get(i)==x && ay.get(i)==y)
            return false;
    }
    return true;
}
@Override
public void reshape(GLAutoDrawable drawable, int x, int y, int width,
int height) {
}

@Override
public void displayChanged(GLAutoDrawable drawable,
boolean modeChanged, boolean deviceChanged) {
}

private void BRE(long x1,long y1,long x2,long y2,GL gl) {
     /*BRESENHAMS LINE DRAWING ALGORITHM*/
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(0.0f, 0.00f, 0.0f );
        long X = x1;
        long Y = y1;
        long dx = Math.abs(x2-x1);
        long dy = Math.abs(y2-y1);
        long s1,s2;
        if(x2-x1>0) s1=1;
        else if(x2-x1==0) s1=0;
        else s1=-1;
        if(y2-y1>0) s2=1;
        else if(y2-y1==0) s2=0;
        else s2=-1;
        
        long intr=0;
        if(dx<dy) {
        long temp = dx;
        dx = dy;
        dy=dx;
        intr=1;
        }
        long e1 = 2*dy - dx;
        for( long i=0;i<=dx;i++)
        {
            gl.glVertex2i((int)X, (int)Y);
            ax.add(X);
            ay.add(Y);
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
public class asssignment
{
public static void main(String args[])
{
GLCapabilities capabilities=new GLCapabilities();
// The canvas
final GLCanvas glcanvas=new GLCanvas(capabilities);
ASSIGN b=new ASSIGN();
glcanvas.addGLEventListener(b);
glcanvas.setSize(400, 400);
final JFrame frame=new JFrame("Basic frame");
//adding canvas to frame
frame.add(glcanvas);
frame.setSize(640,480);
frame.setVisible(true);
}
}