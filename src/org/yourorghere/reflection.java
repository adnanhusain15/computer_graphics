package org.yourorghere;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class reflection implements GLEventListener {
/**
 * Interface to the GLU library.
 */
private GLU glu;

public void init(GLAutoDrawable gld) {
     GL gl = gld.getGL();
     glu = new GLU();
     gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
     gl.glViewport(0,0,640,480);
     gl.glMatrixMode(GL.GL_PROJECTION);
     gl.glLoadIdentity();
     glu.gluOrtho2D(0,640,0,480);
}

public void display(GLAutoDrawable drawable) {
    int matx[][]={{1,0,0},{0,-1,0},{0,0,1}};
    int maty[][]={{-1,0,0},{0,1,0},{0,0,1}};
    int mato[][]={{-1,0,0},{0,-1,0},{0,0,1}};
    int obj[][]=new int[5][3];
    int c[][]=new int[5][3];
    int cy[][]=new int[5][3];
    int co[][]=new int[5][3];
    int xv[]=new int[5];
    int yv[]=new int[5];
    xv[0]=340;
    xv[1]=390;
    xv[2]=440;
    xv[3]=440;
    xv[4]=340;
    yv[0]=260;
    yv[1]=300;
    yv[2]=260;
    yv[3]=360;
    yv[4]=360;
    int i,j,k;
    for(i=0;i<5;i++){
        obj[i][0]=xv[i];
        obj[i][1]=yv[i];
        obj[i][2]=1;
        c[i][0]=0;
        c[i][1]=0;
        c[i][2]=0;
    }
    for (i = 0; i < 5; i++)
    {
    for (j = 0; j < 3; j++)
    {
        for ( k = 0; k < 3; k++)
        {
          
            if(k==0)
                co[i][j] = co[i][j] + (obj[i][k]-320) * mato[k][j];
            else if(k==1)
                co[i][j] = co[i][j] + (obj[i][k]-240) * mato[k][j];
            else
                co[i][j] = co[i][j] + obj[i][k] * mato[k][j];
        }
    }
    }
    for (i = 0; i < 5; i++)
    {
    for (j = 0; j < 3; j++)
    {
        for ( k = 0; k < 3; k++)
        {
          
            if(k==0)
                cy[i][j] = cy[i][j] + (obj[i][k]-320) * maty[k][j];
            else if(k==1)
                cy[i][j] = cy[i][j] + (obj[i][k]-240) * maty[k][j];
            else
                cy[i][j] = cy[i][j] + obj[i][k] * maty[k][j];
        }
    }
    }
    for (i = 0; i < 5; i++)
    {
    for (j = 0; j < 3; j++)
    {
        for ( k = 0; k < 3; k++)
        {
          
            if(k==0)
                c[i][j] = c[i][j] + (obj[i][k]-320) * matx[k][j];
            else if(k==1)
                c[i][j] = c[i][j] + (obj[i][k]-240) * matx[k][j];
            else
                c[i][j] = c[i][j] + obj[i][k] * matx[k][j];
        }
    }
    }
    GL gl = drawable.getGL();
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
   
    //drawLine(gl, 0, 0, 100, 100);
    gl.glColor3f(1.0f, 0.0f, 0.0f );
    drawLine(gl,340,390,260,300);
    drawLine(gl,390,440,300,260);
    drawLine(gl,440,440,260,360);
    drawLine(gl,440,340,360,360);
    drawLine(gl,340,340,360,260);
    //drawLine(gl,60,260,310,400);
    drawLine(gl,0,640,240,240);
    drawLine(gl,320,320,480,0);
    for(i=0;i<5;i++){
        //System.out.println(c[i][0]+","+c[i][1]);
        if(i==4){
            drawLine(gl,c[i][0]+320,c[0][0]+320,c[i][1]+240,c[0][1]+240);
        }
        else
            drawLine(gl,c[i][0]+320,c[i+1][0]+320,c[i][1]+240,c[i+1][1]+240);
    }
    for(i=0;i<5;i++){
        //System.out.println(c[i][0]+","+c[i][1]);
        if(i==4){
            drawLine(gl,cy[i][0]+320,cy[0][0]+320,cy[i][1]+240,cy[0][1]+240);
        }
        else
            drawLine(gl,cy[i][0]+320,cy[i+1][0]+320,cy[i][1]+240,cy[i+1][1]+240);
    }
    for(i=0;i<5;i++){
        //System.out.println(c[i][0]+","+c[i][1]);
        if(i==4){
            drawLine(gl,co[i][0]+320,co[0][0]+320,co[i][1]+240,co[0][1]+240);
        }
        else
            drawLine(gl,co[i][0]+320,co[i+1][0]+320,co[i][1]+240,co[i+1][1]+240);
    }
}

public void reshape(GLAutoDrawable drawable, int x, int y, int width,
    int height) {
}

public void displayChanged(GLAutoDrawable drawable,
     boolean modeChanged, boolean deviceChanged) {
}
@SuppressWarnings("empty-statement")

private void drawLine(GL gl,int x1,int x2,int y1,int y2) {
        int dx,dy;
        gl.glColor3f(1.0f, 0.0f, 0.0f );
        gl.glBegin(GL.GL_POINTS);// begin plotting points

        dx=x2-x1;
        dy=y2-y1;
        int step;
        step = Math.abs(dx)> Math.abs(dy)?Math.abs(dx) : Math.abs(dy);;
        float Xinc = dx / (float) step;
        float Yinc = dy / (float) step;

        // Put pixel for each step
        float x = x1;
        float y = y1; 
   
        for (float i = 0; i <= step; i++){
           //if(i%2!=0) {
                gl.glVertex2f(x,y);
         //   }
            x += Xinc;
            y += Yinc;
    }
        gl.glEnd();
      
}


public void dispose(GLAutoDrawable arg0){
}

}
/*
public class reflection
{
    public static void main(String args[]){
 //getting the capabilities object of GL2 profile
 //final GLProfile profile=GLProfile.get(GLProfile.GL);
 GLCapabilities capabilities=new GLCapabilities();
 // The canvas
 final GLCanvas glcanvas=new GLCanvas(capabilities);
 REFLECTION b=new REFLECTION();
 glcanvas.addGLEventListener(b);
 glcanvas.setSize(400, 400);
 //creating frame
 final JFrame frame=new JFrame("Basic frame");
 //adding canvas to frame
 frame.add(glcanvas);
 frame.setSize(640,480);
 frame.setVisible(true);
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}*/

