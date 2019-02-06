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


class SEEDF implements GLEventListener {
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

/*DRAWING A POLYGON USING BRESENHAMS ALGORITHM*/
BRE(20,20,20,100,gl);
BRE(20,100,90,100,gl);
BRE(90,100,50,60,gl);
BRE(50,60,90,20,gl);
BRE(90,20,20,20,gl);

    
/*CALLING THE SEEDFILL ALGORITHM WITH SEED PIXEL AS 30 30*/
seedfill(gl,30,30);
    
}
public void seedfill(GL gl,int xx,int yy)
{
    long x =xx , y=yy;
    bx.push(x);
    by.push(y);
    gl.glColor3f(1.0f, 0.00f, 0.0f );
    gl.glBegin(GL.GL_POINTS);
    while(bx.empty()!=true && by.empty()!=true)
    {
    x = bx.pop();
    y = by.pop();
    if(findn(x,y))
    {
        
        gl.glVertex2i((int)x, (int)y);
        System.out.println(x + " " +y);
        ny.add(y);
        nx.add(x);            
    }
    if(findn(x+1,y) && findb(x+1,y))
        {
            bx.push(x+1);
            by.push(y);
        }
    if(findn(x,y+1) && findb(x,y+1))
        {
            bx.push(x);
            by.push(y+1);
        }
    if(findn(x-1,y) && findb(x-1,y))
        {
            bx.push(x-1);
            by.push(y);
        }
    if(findn(x,y-1) && findb(x,y-1))
        {
            bx.push(x);
            by.push(y-1);
        }
    }
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
public class prac4_seedfil
{
public static void main(String args[])
{
GLCapabilities capabilities=new GLCapabilities();
// The canvas
final GLCanvas glcanvas=new GLCanvas(capabilities);
SEEDF b=new SEEDF();
glcanvas.addGLEventListener(b);
glcanvas.setSize(400, 400);
final JFrame frame=new JFrame("Basic frame");
//adding canvas to frame
frame.add(glcanvas);
frame.setSize(640,480);
frame.setVisible(true);
}
}