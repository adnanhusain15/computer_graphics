
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

class LineClipping implements GLEventListener{
    private GLU glu;
    int xl,xr,yb,yt;
    LineClipping(){
        xl = 50;
        yb = 50;
        xr = 600;
        yt = 400;
    }
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
        //drawLine(gl, 0, 0, 100, 100);
        gl.glColor3f(1.0f, 1.0f, 1.0f );
        drawLine(gl);
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    
    private void drawLine(GL gl) {
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2i(50, 50);
        gl.glVertex2i(600, 50);
        gl.glVertex2i(600, 400);
        gl.glVertex2i(50, 400);
        gl.glEnd();
        demonstrateLineClipping(gl);
    }    
    
    private void demonstrateLineClipping(GL gl){
        int x1,y1,x2,y2;
        System.out.println("The Co-ordinates of the rectangle are (50,50) and (600,400)");
        //Demonstrating the case of both points inside the Subject Polygon
        clipLine(100,100,300,300,gl);
        
        //Demonstrate the case of Line Completely Outside
        clipLine(50 ,425,300,425,gl);
        
        //Demonstrate the case of Line Partially Visible
        clipLine(580,425,460,35,gl);
        
        //Demonstrate the case of Line Partially Visible
        clipLine(325,80,35,80,gl);
    }
    
    private void clipLine(int x1, int y1, int x2, int y2, GL gl){
        System.out.println("x1 = "+ x1 +", y1 = "+ y1 +", x2 = "+ x2 +", y2 = "+y2);
        int pCode1[] = new int[4];
        int pCode2[] = new int[4];
        pCode1 = getOpcode(x1,y1);
        pCode2 = getOpcode(x2,y2);
        System.out.print("Opcode of Point p1 = ");
        for(int i=0;i<4;i++)
            System.out.print(pCode1[i]+" ");
        System.out.println("");
        System.out.print("Opcode of Point p2 = ");
        for(int i=0;i<4;i++)
            System.out.print(pCode2[i]+" ");
        System.out.println("");
        int chk = checkVisibility(pCode1,pCode2);
        if(chk == 0){
            gl.glColor3f(0f,0f,1f);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2i(x1, y1);
            gl.glVertex2i(x2, y2);
            gl.glEnd();
        }
        else
            if(chk == 2){
                gl.glColor3f(1f,0f,0f);
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2i(x1, y1);
                gl.glVertex2i(x2, y2);
                gl.glEnd();
            }
            else{
                gl.glColor3f(1f,0f,0f);
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2i(x1, y1);
                gl.glVertex2i(x2, y2);
                gl.glEnd();
                CohenSutherlandSubdivision(x1,y1,x2,y2,gl,(double)(y2-y1)/(x2-x1));
            }
    }
    
    void CohenSutherlandSubdivision(int x1,int y1,int x2,int y2,GL gl,double m){
        int pCode[] = getOpcode(x1,y1);
        if(pCode[0] == 0 && pCode[1] == 0 && pCode[2] == 0 && pCode[3] == 0){
            int pCode1[] = getOpcode(x2,y2);
            if(pCode1[0] == 0 && pCode1[1] == 0 && pCode1[2] == 0 && pCode1[3] == 0){
                gl.glColor3f(0,0,1);
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2d(x1, y1);
                gl.glVertex2d(x2, y2);
                gl.glEnd();
                return;
            }
            else{
                CohenSutherlandSubdivision(x2,y2,x1,y1,gl,m);
                return;
            }
        }
        int yl = (int)((double)y1 + m*(double)(xl-x1));
        int yr = (int)((double)y1 + m*(double)(xr-x1));
        int xb = (int)((double)x1 + (1/m)*(double)(yb-y1));
        int xt = (int)((double)x1 + (1/m)*(double)(yt-y1));
        pCode = getOpcode(xl,yl);
        if(pCode[0] == 0 && pCode[1] == 0 && pCode[2] == 0 && pCode[3] == 0 && xt != x2 && yt != y2){
            CohenSutherlandSubdivision(xl,yl,x2,y2,gl,m);
            //return;
        }
        else{
            pCode = getOpcode(xr,yr);
            if(pCode[0] == 0 && pCode[1] == 0 && pCode[2] == 0 && pCode[3] == 0 && xt != x2 && yt != y2){
                CohenSutherlandSubdivision(xr,yr,x2,y2,gl,m);
                //return;
            }
            else{
                pCode = getOpcode(xt,yt);
                if(pCode[0] == 0 && pCode[1] == 0 && pCode[2] == 0 && pCode[3] == 0 && xt != x2 && yt != y2){
                    CohenSutherlandSubdivision(xt,yt,x2,y2,gl,m);
                    //return;
                }
                else{
                    CohenSutherlandSubdivision(xb,yb,x2,y2,gl,m);
                    //return;
                }
            }
        }
    }
    
    private int []getOpcode(int x, int y){
        int []pCode = new int[4];
        int xl = 50, xr = 600, yb = 50, yt = 400;
        if(x<xl)
            pCode[3] = 1;
        else
            pCode[3] = 0;
        if(x>xr)
            pCode[2] = 1;
        else
            pCode[2] = 0;
        if(y<yb)
            pCode[1] = 1;
        else
            pCode[1] = 0;
        if(y>yt)
            pCode[0] = 1;
        else
            pCode[0] = 0;
        return pCode;
    }
    
    private int checkVisibility(int p1code[], int p2code[]){
        int flag1 = 0, flag2 = 0;
        for(int i=0;i<4;i++){
            if(p1code[i] != 0){
                flag1 = 1;
                break;
            }
        }
        for(int i=0;i<4;i++){
            if(p2code[i] != 0){
                flag2 = 1;
                break;
            }
        }
        if(flag1 == 0 && flag2 == 0)
            return 0;
        int outcode[] = new int[4];
        for(int i=0;i<4;i++)
            outcode[i] = p1code[i]&p2code[i];
        int sum = 0;
        for(int i=0;i<4;i++)
            sum += outcode[i];
        if(sum == 0)
            return 1;
        return 2;
    }
    
    public void dispose(GLAutoDrawable arg0){}

}

class Point{
    int x,y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class CohenSutherland {
    public static void main(String args[]){
    //getting the capabilities object of GL2 profile
    //final GLProfile profile=GLProfile.get(GLProfile.GL);
    GLCapabilities capabilities=new GLCapabilities();
    // The canvas
    final GLCanvas glcanvas=new GLCanvas(capabilities);
    LineClipping b=new LineClipping();
    glcanvas.addGLEventListener(b);
    glcanvas.setSize(400, 400);
    //creating frame
    final JFrame frame=new JFrame("Line Clipping");
    //adding canvas to frame
    frame.add(glcanvas);
    frame.setSize(640,480);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
