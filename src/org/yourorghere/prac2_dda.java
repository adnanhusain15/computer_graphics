package org.yourorghere;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;


class DDA implements GLEventListener {
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

gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
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
gl.glColor3f(1.0f, 111.0f, 1.0f );
DDA(200,400,400,400,gl);
DDA(200,200,400,200,gl);
DDA(100,300,200,400,gl);
DDA(100,300,200,200,gl);
DDA(500,300,400,400,gl);
DDA(500,300,400,200,gl);

DDAdotted(200,390,400,390,gl);
DDAdotted(200,210,400,210,gl);
DDAdotted(110,300,200,390,gl);
DDAdotted(110,300,200,210,gl);
DDAdotted(490,300,400,390,gl);
DDAdotted(400,210,490,300,gl);

DDAdotted1(200,370,400,370,gl);
DDAdotted1(200,230,400,230,gl);
DDAdotted1(130,300,200,370,gl);
DDAdotted1(130,300,200,230,gl);
DDAdotted1(470,300,400,370,gl);
DDAdotted1(400,230,470,300,gl);

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

private void DDA(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
        int length=0;        
        if(Math.abs(x2-x1)>=Math.abs(y2-y1))
            length=Math.abs(x2-x1);
        else
            length=Math.abs(y2-y1);       
        float deltaX=(float)(x2-x1)/length;       
        float deltaY=(y2-y1)/length;
        float x=x1,y=y1;        
        for(int i=1;i<=length;i++){
            gl.glVertex2i((int)x, (int)y);
            x+=deltaX;
            y+=deltaY;  
            System.out.println(x+" "+y);
        }
        gl.glEnd();
}
private void DDAdotted(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
        int length=0;        
        if(Math.abs(x2-x1)>=Math.abs(y2-y1))
            length=Math.abs(x2-x1);
        else
            length=Math.abs(y2-y1);       
        float deltaX=(float)(x2-x1)/length;       
        float deltaY=(y2-y1)/length;
        float x=x1,y=y1;        
        for(int i=1;i<=length;i++){
            if(i%2==0)
                gl.glVertex2i((int)x, (int)y);
            x+=deltaX;
            y+=deltaY;  
            System.out.println(x+" "+y);
        }
        gl.glEnd();
}


private void DDAdotted1(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
        int length=0;        
        if(Math.abs(x2-x1)>=Math.abs(y2-y1))
            length=Math.abs(x2-x1);
        else
            length=Math.abs(y2-y1);       
        float deltaX=(float)(x2-x1)/length;       
        float deltaY=(y2-y1)/length;
        float x=x1,y=y1;        
        for(int i=1;i<=length;i++){
            if(i%5!=0)
                gl.glVertex2i((int)x, (int)y);
            x+=deltaX;
            y+=deltaY;  
            System.out.println(x+" "+y);
        }
        gl.glEnd();
}
private void Thick(int x1,int y1,int x2,int y2,GL gl) {
        gl.glBegin(GL.GL_POINTS);
        int length=0;        
        if(Math.abs(x2-x1)>=Math.abs(y2-y1))
            length=Math.abs(x2-x1);
        else
            length=Math.abs(y2-y1);       
        float deltaX=(float)(x2-x1)/length;       
        float deltaY=(y2-y1)/length;
        float x=x1,y=y1;        
        for(int i=1;i<=length;i++){
            gl.glVertex2i((int)x, (int)y);
            gl.glVertex2i((int)x, (int)y+1);
            gl.glVertex2i((int)x, (int)y+2);
            gl.glVertex2i((int)x, (int)y+3);
            gl.glVertex2i((int)x, (int)y+4);
            x+=deltaX;
            y+=deltaY;  
            System.out.println(x+" "+y);
        }
        gl.glEnd();
}

public void dispose(GLAutoDrawable arg0)
{

}
}
public class prac2_dda
{
public static void main(String args[])
{
GLCapabilities capabilities=new GLCapabilities();
// The canvas
final GLCanvas glcanvas=new GLCanvas(capabilities);
DDA b=new DDA();
glcanvas.addGLEventListener(b);
glcanvas.setSize(400, 400);
final JFrame frame=new JFrame("Basic frame");
//adding canvas to frame
frame.add(glcanvas);
frame.setSize(640,480);
frame.setVisible(true);
}
}