package Package;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas ;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import javax.swing.JFrame;

public class Retas {
   
	private int x1 = 2;
    private int x2 = 8;
    private int y1 = 6;
    private int y2 = 4;
    
 // *-----> Construtor
    public Retas(int x1, int x2,int y1,int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

   public int getX1() {
	   return this.x1;
   }
   public int getX2() {
	   return this.x2;
   }
   public int getY1() {
	   return this.y1;
   }
   public int getY2() {
	   return this.y2;
   }
   
   
   public void setX1(int x) {
	   this.x1 = x;
   }
   public void setX2(int x) {
	   this.x2 = x;
   }
   public void setY1(int y) {
	   this.y1 = y;
   }
   public void setY2(int y) {
	   this.y2 = y;
   }
 
}