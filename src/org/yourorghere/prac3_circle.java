
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

/**
 *
 * @author RAGHAV
 */
class Circle implements GLEventListener {
    private GLU glu;
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        glu = new GLU();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glViewport(0,0,640,480);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0,640,0,480);
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //drawLine(gl, 0, 0, 100, 100);
        gl.glColor3f(1.0f, 1.0f, 1.0f );
        drawLine(gl);
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    
    void bCircle(int h, int k, int r, GL gl, int mode){
        int xi = 0, yi = r;
        int delI = 2*(1-r);
        gl.glBegin(GL.GL_POINTS);
        while(yi>=0){
            if(mode == 1){
                for(int i=-2;i<2;i++){
                    gl.glVertex2i(xi+h+i, yi+k+i);
                    gl.glVertex2i(-xi+h+i, yi+k+i);
                    gl.glVertex2i(-xi+h+i, -yi+k+i);
                    gl.glVertex2i(xi+h+i, -yi+k+i);
                }
            }
            else{
                if(mode == 2)
                    for(int i=-2;i<2;i++)
                        gl.glVertex2i(xi+h+i, yi+k+i);
                else
                    for(int i=-2;i<2;i++)
                        gl.glVertex2i(-xi+h+i, -yi+k+i);
                    
            }
            if(delI < 0){
                int del = 2*delI + 2*yi-1;
                if(del<=0){
                    xi += 1;
                    delI = delI+2*xi+1;
                }
                else{
                    xi += 1;
                    yi -= 1;
                    delI = delI + 2*(xi-yi+1);
                }
            }
            else{
                if(delI > 0){
                    int del = 2*delI - 2*xi - 1;
                    if(del <= 0){
                        xi += 1;
                        yi -= 1;
                        delI = delI + 2*(xi-yi+1);
                    }
                    else{
                        yi -= 1;
                        delI = delI - 2*yi + 1;
                    }
                }
                else{
                    xi += 1;
                    yi -= 1;
                    delI = delI + 2*(xi-yi+1);
                }
            }
        }
        gl.glEnd();
    }
    
    private void drawLine(GL gl) {
        gl.glColor3f(0.0f, 0.0f, 1.0f );
        bCircle(100,240,50,gl,1);
        
        gl.glColor3f(0.0f, 0.0f, 0.0f );
        bCircle(220,240,50,gl,1);
        
        gl.glColor3f(1.0f, 0.0f, 0.0f );
        bCircle(340,240,50,gl,1);
        
        gl.glColor3f(1.0f, 1.0f, 0.0f );
        bCircle(155,200,50,gl,1);
        
        gl.glColor3f(0.0f, 1.0f, 0.0f );
        bCircle(275,200,50,gl,1);
        
        gl.glColor3f(0.0f, 0.0f, 1.0f );
        bCircle(100,240,50,gl,2);
        
        gl.glColor3f(0.0f, 0.0f, 0.0f );
        bCircle(220,240,50,gl,3);
        bCircle(220,240,50,gl,2);
        
        gl.glColor3f(1.0f, 0.0f, 0.0f );
        bCircle(340,240,50,gl,3);
    }
    
    public void dispose(GLAutoDrawable arg0){}
}

public class prac3_circle {
    public static void main(String args[]){
        //getting the capabilities object of GL2 profile
        //final GLProfile profile=GLProfile.get(GLProfile.GL);
        GLCapabilities capabilities=new GLCapabilities();
        // The canvas
        final GLCanvas glcanvas=new GLCanvas(capabilities);
        Circle b=new Circle();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(640, 480);
        //creating frame
        final JFrame frame=new JFrame("Bresenhem Circle frame");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640,480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   
}
