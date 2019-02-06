package org.yourorghere;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;


class BRE implements GLEventListener {
/**
 * Interface to the GLU library.
 */
private GLU glu;
/**
 * Take care of initialization here.
 */
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
public void display(GLAutoDrawable drawable) {
GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(1.0f, 0.00f, 0.0f );
BRE(200,400,400,400,gl);
BRE(200,200,400,200,gl);
BRE(100,300,200,400,gl);
BRE(100,300,200,200,gl);
BRE(500,300,400,400,gl);
BRE(500,300,400,200,gl);

BREdotted(200,390,400,390,gl);
BREdotted(200,210,400,210,gl);
BREdotted(110,300,200,390,gl);
BREdotted(110,300,200,210,gl);
BREdotted(490,300,400,390,gl);
BREdotted(400,210,490,300,gl);

BREdotted1(200,370,400,370,gl);
BREdotted1(200,230,400,230,gl);
BREdotted1(130,300,200,370,gl);
BREdotted1(130,300,200,230,gl);
BREdotted1(470,300,400,370,gl);
BREdotted1(400,230,470,300,gl);

Thick(200,350,400,350,gl);
Thick(200,250,400,250,gl);
Thick(150,300,200,350,gl);
Thick(150,300,200,250,gl);
Thick(450,300,400,350,gl);
Thick(400,250,450,300,gl);
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
private void BREdotted(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
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
            if(i%2==0)
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


private void BREdotted1(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
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
            if(i%5!=0)
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
private void Thick(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
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
            gl.glVertex2i((int)X, (int)Y+1);
            gl.glVertex2i((int)X, (int)Y+2);
            gl.glVertex2i((int)X, (int)Y+3);
            gl.glVertex2i((int)X, (int)Y+4);
            
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
public class prac2_bre
{
public static void main(String args[])
{
GLCapabilities capabilities=new GLCapabilities();
// The canvas
final GLCanvas glcanvas=new GLCanvas(capabilities);
BRE b=new BRE();
glcanvas.addGLEventListener(b);
glcanvas.setSize(400, 400);
final JFrame frame=new JFrame("Basic frame");
//adding canvas to frame
frame.add(glcanvas);
frame.setSize(640,480);
frame.setVisible(true);
}
}