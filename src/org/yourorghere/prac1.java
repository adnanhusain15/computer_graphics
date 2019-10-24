

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.swing.*;

class SecondGLEventListener implements GLEventListener {

private GLU glu;

public void init(GLAutoDrawable gld) {
    GL gl = gld.getGL();
    glu = new GLU();

    gl.glClearColor(0.0f, 1.0f, 1.0f, 0.0f);
    gl.glViewport(0,0,640,480);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(0,640,0,480);
}

public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glColor3f(1.0f, 1.0f, 0.0f );
    drawLine(gl);

}

public void reshape(GLAutoDrawable drawable, int x, int y, int width,int height) {
}

public void displayChanged(GLAutoDrawable drawable,boolean modeChanged, boolean deviceChanged) {
}

private void drawLine(GL gl) {
    gl.glPointSize(3.0f);
    gl.glBegin(GL.GL_TRIANGLES); //top
    
    gl.glColor3f(1.0f,1.0f,1.0f);
    
    gl.glVertex2i(150,250);
    gl.glVertex2i(100,150);
    gl.glVertex2i(300,150);
    
   gl.glEnd();
   
   gl.glBegin(GL.GL_POLYGON);//rect
   
   gl.glColor3f(0.0f,0.0f,1.0f);
   
   gl.glVertex2i(120,0);
   gl.glVertex2i(280,0);
   
   gl.glVertex2i(280,150);
   gl.glVertex2i(120,150);
   
   gl.glVertex2i(280,150);
   gl.glVertex2i(280,150);
   
   gl.glVertex2i(120,150);
   gl.glVertex2i(120,0);
   
   gl.glEnd();
   
   gl.glBegin(GL.GL_POLYGON);//door
   gl.glColor3f(1.0f,1.0f,0.0f);
   gl.glVertex2i(130,0);
   gl.glVertex2i(185,0);
   
   gl.glVertex2i(185,75);
   gl.glVertex2i(130,75);
   
   gl.glVertex2i(185,0);
   gl.glVertex2i(185,75);
   
   gl.glVertex2i(130,75);
   gl.glVertex2i(130,0);
   gl.glEnd();
   
   gl.glBegin(GL.GL_POLYGON);//window
   
   gl.glColor3f(1.0f,1.0f,0.0f);
   
   gl.glVertex2i(200,110);
   gl.glVertex2i(225,110);
   
   gl.glVertex2i(225,130);
   gl.glVertex2i(200,130);
   
   gl.glVertex2i(225,110);
   gl.glVertex2i(225,130);
   
   gl.glVertex2i(200,130);
   gl.glVertex2i(200,110);
   
   gl.glEnd();
   
   gl.glBegin(GL.GL_POLYGON);
   
   gl.glColor3f(1.0f,1.0f,0.0f);
   
   gl.glVertex2i(235,110);
   gl.glVertex2i(260,110);
   
   gl.glVertex2i(260,130);
   gl.glVertex2i(235,130);
   
   gl.glVertex2i(260,110);
   gl.glVertex2i(260,130);
   
   gl.glVertex2i(235,130);
   gl.glVertex2i(235,110);
   
   gl.glEnd();
   
  
   
   double theta;
    gl.glBegin(GL.GL_POLYGON);//sun
    
    gl.glColor3d(1.0f,1.0f,1.0f);
    for(int i=0;i<360;i++)
    {
        theta= i*3.142*180;
        gl.glVertex2d(530+40*Math.cos(theta),420+40*Math.sin(theta));
    }
    gl.glEnd();
}

public void dispose(GLAutoDrawable arg0)
{
    
}
}
public class prac1
{
public static void main(String args[])
{
    GLCapabilities capabilities=new GLCapabilities();
    
    final GLCanvas glcanvas=new GLCanvas(capabilities);
    SecondGLEventListener b=new SecondGLEventListener();
    glcanvas.addGLEventListener(b);
    glcanvas.setSize(400, 400);
    
    final JFrame frame=new JFrame("Mannat");
    
    frame.add(glcanvas);
    frame.setSize(1080,720);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
