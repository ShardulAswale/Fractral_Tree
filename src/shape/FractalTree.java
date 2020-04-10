package shape;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

class ThirdGLEventListener implements GLEventListener {
    private GLU glu;
    @Override
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        glu = new GLU();
        gl.glClearColor(0.9f, 0.9f, 1.0f, 1.0f);
        gl.glViewport(0, 0,2000 ,1080);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0, 2000, 0, 1080);
    }
    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        drawLine(gl);
    }
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,int height) {
    }
    @Override
    public void displayChanged(GLAutoDrawable drawable,boolean modeChanged, boolean deviceChanged) {
    }

    private void drawLine(final GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.9f, 0.0f, 0.0f);
        //balcony
        gl.glVertex2i(0,0);
        gl.glVertex2i(0,400);
        gl.glVertex2i(2000,400);
        gl.glVertex2i(2000,0);
        //table top
        gl.glColor3f(0.3f, 0.0f, 0.0f);
        gl.glVertex2i(125,300);
        gl.glVertex2i(1275,300);
        gl.glVertex2i(1250,150);
        gl.glVertex2i(150,150);
        //table legs
        gl.glColor3f(0.3f, 0.0f, 0.0f);
        gl.glVertex2i(300,150);
        gl.glVertex2i(350,150);
        gl.glVertex2i(350,0);
        gl.glVertex2i(300,0);
        
        gl.glColor3f(0.3f, 0.0f, 0.0f);
        gl.glVertex2i(1050,150);
        gl.glVertex2i(1100,150);
        gl.glVertex2i(1100,0);
        gl.glVertex2i(1050,0);
        gl.glEnd();
        //sun
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        for(double i=0d;i<2*Math.PI;i=i+0.01d){
            gl.glVertex2d(1700,900);
            gl.glVertex2d(1700+100*Math.cos(i),900+100*Math.sin(i));
        }
        gl.glEnd();
        
                //make the plants
        plant(gl,300,250,Math.PI/2,50);
        plant(gl,700,275,Math.PI/2,70);
        plant(gl,1100,250,Math.PI/2,50);
   
    }
    public void plant(GL gl,double x,double y,double angle,double len){
        gl.glColor3f(0.3f, 0.2f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        for(double i=0d;i<2*Math.PI;i=i+0.01d){
            gl.glVertex2d(x,y);
            gl.glVertex2d(x+(len/2)*Math.cos(i),y+(len*0.3)*Math.sin(i));
        }
        gl.glColor3f(1.0f, 0.3f, 0.0f);
        for(double i=Math.PI;i<2*Math.PI;i=i+0.01d){
            gl.glVertex2d(x+len/2*Math.cos(i),y+len*0.3*Math.sin(i));
            gl.glVertex2d(x+len/2*Math.cos(i),y-len*0.7+len*0.3*Math.sin(i));
        }
        gl.glEnd();       
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        branch(gl,x,y,angle,len);
    }
    public void branch(GL gl,double x,double y,double angle,double len){
        gl.glBegin(GL.GL_LINES); 
        gl.glVertex2d(x,y);
        gl.glVertex2d(x+len*Math.cos(angle),y+len*Math.sin(angle));
        gl.glEnd();
        if(len>5){
            x=x+len*Math.cos(angle);
            y=y+len*Math.sin(angle);
            len*=0.8;
            branch(gl,x,y,angle+4*Math.PI/16,len);
            branch(gl,x,y,angle-4*Math.PI/16,len);
        }
    }
    public void dispose(GLAutoDrawable arg0) {
    }
}

public class FractalTree {

    public static void main(String args[]) {
        GLCapabilities capabilities = new GLCapabilities();
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(2000,1080);
        //creating frame
        final JFrame frame = new JFrame("Basic frame");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(2000,1080);
        frame.setVisible(true);
    }
}
