package org.yourorghere;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
//import javax.media.opengl.GLCapabilities;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;


class ELIPSE implements GLEventListener {
/**
 * Interface to the GLU library.
 */
private GLU glu;
  public int xi;
   public int yi;
    public int e;

  
/**
 * Take care of initialization here.
 */
public void init(GLAutoDrawable gld) {
    GL gl = gld.getGL();
    glu = new GLU();

    gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
    gl.glViewport(0,0,640,480);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(0,640,0,480);
}

/**
 * Take care of drawing here.
 */
public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    /*
     * put your code here
     */
    //drawLine(gl, 0, 0, 100, 100);
    gl.glColor3f(1f, 0f, 0.0f );
 

elipse(gl,70,100,60,30 );
elipse(gl,70,100,20,30 );
elipse(gl,70,115,30,15 );


}

public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
}

public void displayChanged(GLAutoDrawable drawable,
    boolean modeChanged, boolean deviceChanged) {
}


public void dispose(GLAutoDrawable arg0)
{
}



private void elipse(GL gl,int x1,int y1,int a,int b) {
  
     xi=0;
     yi=b;
     e=b*b-2*b*a*a+a*a;
    int d;
    int d1;
 
    gl.glBegin(GL.GL_POINTS);// begin plotting points
    while(yi>=0)
    {
         gl.glVertex2i((int)Math.floor(xi+x1),(int)Math.floor(yi+y1));
       
           gl.glVertex2i(-(int)Math.floor(xi)+x1,(int)Math.floor(yi)+y1);
       
             gl.glVertex2i((int)Math.floor(xi)+x1,-(int)Math.floor(yi)+y1);
            
               gl.glVertex2i(-(int)Math.floor(xi)+x1,-(int)Math.floor(yi)+y1);
        if(e<0)
        {
           d=2*e+2*yi*a*a-a*a;
           if(d<=0)
           {
               mh(b);
           }
           else{
               md(a,b);
           }
        }else if(e>0)
        {
            d1=2*e-2*xi*b*b-b*b;
            if(d1<=0)
            {
                md(a,b);
            }
            else
            {
                mv(a);
            }
          
        }
        else
                {
                    md(a,b);
                }
    }
 
     gl.glEnd();
}

void mh(int b)
{
    xi=xi+1;
    e=e+2*xi*b*b+b*b;
}
void mv(int a)
{
    yi=yi-1;
    e=e-2*yi*a*a+a*a;
}
void md(int a,int b)
{
    xi=xi+1;
    yi=yi-1;
    e=e+2*xi*b*b-2*yi*a*a+a*a+b*b;
}


}
public class prac3_elipse
{
public static void main(String args[])
{
    //getting the capabilities object of GL2 profile
    //final GLProfile profile=GLProfile.get(GLProfile.GL);
    GLCapabilities capabilities=new GLCapabilities();
    // The canvas
    final GLCanvas glcanvas=new GLCanvas(capabilities);
    ELIPSE b=new ELIPSE();
    glcanvas.addGLEventListener(b);
    glcanvas.setSize(400, 400);
    //creating frame
    final JFrame frame=new JFrame("Basic frame");
    //adding canvas to frame
    frame.add(glcanvas);
    frame.setSize(640,480);
    frame.setVisible(true);
 
}
}

	
	
	
