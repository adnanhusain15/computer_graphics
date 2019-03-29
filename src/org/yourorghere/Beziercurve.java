package org.yourorghere;

import java.awt.*;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

    
class CURVE implements GLEventListener {
private GLU glu;

@Override
public void init(GLAutoDrawable gld) {
    GL gl = gld.getGL();
    glu = new GLU();

    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glViewport(0,0,640,480);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(0,640,0,480);
}

@Override
public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    try {
        drawLine(gl);
    } catch (AWTException ex) {
        Logger.getLogger(CURVE.class.getName()).log(Level.SEVERE, null, ex);
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
    int xx,yy,i,c;
    double cc,x = 0,y = 0;
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter no of control points:");
    c=sc.nextInt();
    int xlist[]=new int[c+1];
    int ylist[]=new int[c+1];
    ArrayList<Pair<Double,Double> >alist=new ArrayList<Pair<Double,Double>>();
    System.out.println("Enter coordinates of control points:");
    for(i=0;i<c;i++){
        xx=sc.nextInt();
        yy=sc.nextInt();
        xlist[i]=xx;
        ylist[i]=yy;
    }
    for(double u=0;u<=1;u+=0.001){
        x=0;
        y=0;
        for(i=0;i<c;i++){
            cc=((double)factorial(c-1)/(factorial((c-1)-i)*factorial(i)))*pow(u,i)*pow(1-u,(c-1)-i);
            x+=cc*xlist[i];
            y+=cc*ylist[i];
        }
        alist.add(new Pair(x,y));
    }
    gl.glBegin(GL.GL_LINES);
    gl.glVertex2d(100, 300);
    gl.glVertex2d(550, 300);
    gl.glVertex2d(100, 300);
    gl.glVertex2d(100, 400);
    gl.glEnd();
    
    gl.glBegin(GL.GL_POINTS);
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    for(i=0;i<alist.size();i++){
        gl.glVertex2d(alist.get(i).getKey(),alist.get(i).getValue());
    }
    gl.glEnd();
               
}


int factorial(int x){
    int i,f=1;
    for(i=1;i<=x;i++)
        f*=i;
    return f;
}
public void dispose(GLAutoDrawable arg0)
{
    
}
}
public class Beziercurve{
    public static void main(String args[]){
        //getting the capabilities object of GL2 profile
        //final GLProfile profile=GLProfile.get(GLProfile.GL);
        GLCapabilities capabilities=new GLCapabilities();
        // The canvas
        final GLCanvas glcanvas=new GLCanvas(capabilities);
        CURVE b=new CURVE();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame=new JFrame("Basic Frame");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640,480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

