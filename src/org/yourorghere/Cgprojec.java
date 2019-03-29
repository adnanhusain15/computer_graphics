package org.yourorghere;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import java.awt.*;
import static java.lang.Math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

    
class thirdGLEventListener implements GLEventListener {
private GLU glu;
private GLUT glut;
float rotation=0.0f;
@Override
public void init(GLAutoDrawable gld) {
     GL gl = gld.getGL();
    glu = new GLU();
    glut=new GLUT();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glViewport(0,0,640,480);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
   // gl.glOrtho(-3*1,3*1, -3,3, -3,3);
    glu.gluPerspective(45, (float)640/(float)480,3.0, 20.0);
    gl.glMatrixMode(GL.GL_MODELVIEW);
    gl.glLoadIdentity();
}

@Override
public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    
    gl.glLoadIdentity();
    
    try {
        drawLine(gl);
    } catch (AWTException ex) {
        Logger.getLogger(thirdGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
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

private void drawLine(GL gl) throws AWTException {
    
//    gl.glRotated(45, 1.0f, 1.0f, 1.0f); 
//    gl.glRotated(45, 0.0f, 1.0f, 0.0f); 
   double Ex = -2*3*sin(PI/180*(45))*cos(PI/180*(45));
   double Ey = 2*3*sin(PI/180*(45));
   double Ez = 2*3*cos(PI/180*(45))*cos(PI/180*(45));
   glu.gluLookAt(Ex,Ey,Ez , 0,0,0 , 0,cos(PI/180*(45)),0);
    
//    gl.glRotatef(rotation,1,1,1);
    gl.glBegin(GL.GL_LINES);
    
    gl.glColor3f(0.0f, 10.0f, 0.0f);
    gl.glVertex3f(0, 0, 0);
    gl.glVertex3f(10.5f, 0, 0);
    gl.glColor3f(0.0f, 0.0f, 10.0f);
    gl.glVertex3f(0, 0, 0);
    gl.glVertex3f(0.0f, 10.5f, 0);
    gl.glColor3f(10.0f, 0.0f, 0.0f);
    gl.glVertex3f(0, 0, 0);
    gl.glVertex3f(0.0f, 0, 10.5f);
    
    gl.glEnd();
    
    gl.glBegin(GL.GL_QUADS);   
    
    //Top Quadrilateral  
    gl.glColor3f(0f,0f,1f); //Blue color  
    gl.glVertex3f(1f, 1f, 0.0f); //Upper Right  
    gl.glVertex3f( 0.0f, 1f, 0.0f); // Upper Left  
    gl.glVertex3f( 0.0f, 1f, 1f ); // Bottom Left  
    gl.glVertex3f( 1f, 1f, 1f ); // Bottom Right  
            //Below Quadrilateral  
    gl.glColor3f( 1f,0f,0f ); //Red color  
    gl.glVertex3f( 1f, 0.0f, 0.0f ); // Upper Right   
    gl.glVertex3f( 0.0f, 0.0f, 0.0f ); // Upper Left   
    gl.glVertex3f( 0.0f, 0.0f, 1f ); // Bottom Left   
    gl.glVertex3f( 1f, 0.0f, 1f ); // Bottom Right   
          //Front Quadrilateral  
    gl.glColor3f( 0f,1f,0f ); //Green color  
    gl.glVertex3f( 1f, 1f, 1f ); // Upper Right   
    gl.glVertex3f( 0.0f, 1f, 1f ); // Upper Left   
    gl.glVertex3f( 0.0f, 0.0f, 1f ); // Bottom Left   
    gl.glVertex3f( 1f, 0.0f, 1f ); // Bottom Right  
          //Back Quadrilateral  
    gl.glColor3f( 1f,1f,0f ); //Yellow  
    gl.glVertex3f( 0.0f, 0.0f, 0.0f ); // Bottom Left   
    gl.glVertex3f( 1f, 0.0f, 0.0f ); // Bottom Right   
    gl.glVertex3f( 1f, 1f, 0.0f ); // Upper Right   
    gl.glVertex3f( 0.0f, 1f, 0.0f ); // Upper Left   
          //Left Quadrilateral  
    gl.glColor3f( 1f,0f,1f ); //Purple  
    gl.glVertex3f( 0.0f, 1f, 0.0f ); // Upper Right  
    gl.glVertex3f( 0.0f, 1f, 1f ); // Upper Left   
    gl.glVertex3f( 0.0f, 0.0f, 1f ); // Bottom Left   
    gl.glVertex3f( 0.0f, 0.0f, 0.0f ); // Bottom Right   
          //Right Quadrilateral  
    gl.glColor3f( 0f,1f, 1f ); //Cyan  
    gl.glVertex3f( 1f, 1f, 0.0f ); // Upper Right   
    gl.glVertex3f( 1f, 1f, 1f ); // Upper Left   
    gl.glVertex3f( 1f, 0.0f, 1f ); // Bottom Left   
    gl.glVertex3f( 1f, 0.0f, 0.0f ); // Bottom Right   
    
    gl.glEnd();   
    gl.glFlush();
    rotation+=0.6f;
}

public void dispose(GLAutoDrawable arg0)
{
    
}
}
public class Cgprojec{
    public static void main(String args[]){
        //getting the capabilities object of GL2 profile
        //final GLProfile profile=GLProfile.get(GLProfile.GL);
        GLCapabilities capabilities=new GLCapabilities();
        // The canvas
        final GLCanvas glcanvas=new GLCanvas(capabilities);
        thirdGLEventListener b=new thirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame=new JFrame("Basic Frame");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640,480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 200,true);  
        animator.start(); 
    }
}

