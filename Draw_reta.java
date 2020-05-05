package Package;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jogamp.common.util.locks.RecursiveLock;
import com.jogamp.nativewindow.NativeSurface;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAnimatorControl;
import com.jogamp.opengl.awt.GLCanvas ;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLRunnable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLCapabilitiesImmutable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLDrawable;
import com.jogamp.opengl.GLDrawableFactory;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLAutoDrawable;

public class Draw_reta implements GLEventListener{
	
	private Retas reta;
	private boolean DDA;
	private boolean ponto_medio;
	private boolean equacao;
	private boolean exibir_malha;
	private boolean exibir_reta;
	private int size_malha;

	//SETS
  public void setReta(Retas reta) {
		  
	  this.reta = reta;
  }
  
  public void setDDA(boolean DDA) {
	  
	  this.DDA = DDA;
  }
  
  public void setPonto_medio(boolean ponto_medio) {
	  
	  this.ponto_medio = ponto_medio;
  }
  
  public void setEquacao(boolean equacao) {
	  
	  this.equacao = equacao;
  }
  
  public void setExibir_malha(boolean exibir_malha) {
	  
	  this.exibir_malha = exibir_malha;
  }
  
  public void setExibir_reta(boolean exibir_reta) {
	  
	  this.exibir_reta = exibir_reta;
  }
  
  public void setSize_malha(int size) {
		  
	  this.size_malha = size;
  }
  
	@Override
    public void display(GLAutoDrawable arg0) {
       final GL2 gl = arg0.getGL().getGL2();
          int x1 = reta.getX1();
          int x2 = reta.getX2();
          int y1 = reta.getY1();
          int y2 = reta.getY2();
          
          if(exibir_malha == true) {
        	  draw_malha(gl, size_malha);
          }
          
          if(exibir_reta == true) {
        	  draw_real_line(gl);
          }
          
          if(DDA == true) {
        	  dda(gl, x1, x2, y1, y2);
          }
          
          if(ponto_medio == true) {
        	// ponto_medio(gl, x1, x2, y1, y2);
             ponto_medio2(gl, x1, x2, y1, y2);
          }
          
          if(equacao == true) {
        	  equacao(gl, x1, x2, y1, y2);
          }
          

            gl.glFlush(); 
       
    }
    @Override
    public void dispose(GLAutoDrawable arg0) {
       
    }
    @Override
    public void init(GLAutoDrawable arg0) {
       
        final GL2 gl = arg0.getGL().getGL2();
          
        // Limpar a Tela e Setar a cor Branca
        gl.glClearColor(1, 1, 1, 0);
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT);
          
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glOrtho(0, size_malha, 0, size_malha, 1, -1);
        gl.glMatrixMode(gl.GL_MODELVIEW);
  
    }
    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
    	
    }
    
   private void  draw_malha(GL2 gl, int size) {
    	
        //Inicia A cor Preta
        gl.glColor3f(0, 0, 0);
  	
    	gl.glBegin(gl.GL_LINES);
        for (int i = 0; i < size; i++) {
        	gl.glVertex2f(i, 0);
        	gl.glVertex2f(i, size);

        	gl.glVertex2f(0, i);
        	gl.glVertex2f(size, i);
        }
        gl.glEnd();
    }
   
   
   private void draw_pixel(GL2 gl, int x, int y, String cor) {
       if(cor.compareTo("BLACK") == 0) {
    	   //Inicia A cor Preto
           gl.glColor3f(0, 0, 0);
    	   
       }
       
       if(cor.compareTo("BLUE") == 0) {
    	   //Inicia A cor Vermelha
           gl.glColor3f(0, 0, 1);
    	   
       }
       
       if(cor.compareTo("GREEN") == 0) {
    	   //Inicia A cor Verde
           gl.glColor3f(0, 1, 0);
    	   
       }
	   
	   
       int lados = 60;
       double raio = 0.5;
       double PI = 3.14159265;
       
       gl.glBegin(gl.GL_POLYGON);
           for (int i = 0; i < lados; ++i) {
           	gl.glVertex2d(x + raio*Math.cos(i*2*PI/lados), y + raio*Math.sin(i*2*PI/lados));
           }
           gl.glEnd();
   }
      
   
   private void draw_real_line(GL2 gl) {
       int x1 = reta.getX1();
       int x2 = reta.getX2();
       int y1 = reta.getY1();
       int y2 = reta.getY2();
	   
       //Inicia A cor Vermelho
       gl.glColor3f(1, 0, 0);
      
       gl.glLineWidth(10);
       
       gl.glBegin(gl.GL_LINES);
   			gl.glVertex2f(x1, y1);
   			gl.glVertex2f(x2, y2);
   	   gl.glEnd();
  
   }
   public void ponto_medio2(GL2 gl, int x1, int x2, int y1, int y2) {
	   
	   int dx, dy,dstart = 0, incrJ = 0, incrK = 0;
	   dx = x2 - x1;
	   dy = y2 - y1;
	   int eixo_X_j= 0, eixo_Y_j= 0 ,eixo_X_k= 0,eixo_Y_k= 0;
	   
	   
	// Quadrante 1
       if (dx >= 0 && dy >= 0) { 
           if (dx >= dy) {// Octante 1
        	   dstart = 2 * dy - dx;
        	   incrJ  = 2 * (dy - dx); //NE
        	   incrK  = 2 * dy; // E
        	   eixo_X_j = 1;
        	   eixo_Y_j = 1;
        	   eixo_X_k = 1;
        	   eixo_Y_k = 0;
           }
           else { // Octante 2
        	   dstart = dy - (2 * dx);
        	   incrJ  = 2 * (-dy); // N
        	   incrK  = 2 * (dy - dx); //NE
        	   eixo_X_j = 0;
        	   eixo_Y_j = 1;
        	   eixo_X_k = 1;
        	   eixo_Y_k = 1;
           }
          
       }
   //  Quadrante 2
       else if (dx < 0 && dy >= 0) { 
    	   if (dx >= dy) {// Octante 3
    		   dstart = - dy - (2 * dx);
    		   incrJ  = 2 * (-dy - dx); //NW
    		   incrK  = 2 * (-dx); // N
        	   eixo_X_j = -1;
        	   eixo_Y_j = 1;
        	   eixo_X_k = 0;
        	   eixo_Y_k = 1;
           }
           else { // Octante 4
        	   dstart = - dy - (2 * dx);
        	   incrJ  = 2 * (-dy); // W
        	   incrK  =  2 * (-dy - dx); //NW
        	   eixo_X_j = -1;
        	   eixo_Y_j = 0;
        	   eixo_X_k = -1;
        	   eixo_Y_k = 1;
           }
       }
   //  Quadrante 3
       else if (dx < 0 && dy < 0) { 
    	   if (dx >= dy) {// Octante 5
        	   dstart = (2 * -dy) + dx;
        	   incrJ  = 2 * (-dy + dx); //SW
        	   incrK  = 2 * (-dy); // W
        	   eixo_X_j = 1;
        	   eixo_Y_j = 1;
        	   eixo_X_k = 1;
        	   eixo_Y_k = 0;
           }
           else { // Octante 6
        	   dstart =  -dy + (2 * dx);
        	   incrJ  = 2 * dx; //S
        	   incrK  = 2 * (-dy + dx); //SW
        	   eixo_X_j = -1;
        	   eixo_Y_j = -1;
        	   eixo_X_k = -1;
        	   eixo_Y_k = 0;
           }
       }
   //  Quadrante 4
       else if (dx >= 0 && dy < 0) { 
     	  if (dx >= dy) {// Octante 7
          	  dstart = dy + (2 * dx);
        	  incrJ  = 2 * (dy + dx); //SE
     		  incrK  = 2 * dx; //S
       	 	  eixo_X_j = 1;
       	 	  eixo_Y_j = -1;
       	 	  eixo_X_k = 0;
       	 	  eixo_Y_k = -1;
           }
           else { // Octante 8
        	   dstart = 2 * dy + dx;
        	   incrJ  = 2 * dy; // E
        	   incrK  = 2 * (dy + dx); //SE
        	   eixo_X_j = 1;
        	   eixo_Y_j = 0;
        	   eixo_X_k = 1;
        	   eixo_Y_k = -1;
           }
       }
	   
      draw_line_p_medio(gl, x1, x2, y1, y2, dstart, incrJ, incrK, eixo_X_j, eixo_Y_j, eixo_X_k, eixo_Y_k);
	   
   }
   
  public void draw_line_p_medio(GL2 gl, int x1, int x2, int y1, int y2, int dstart, int incrJ, int incrK, int eixo_X_j,int eixo_Y_j, int eixo_X_k , int eixo_Y_k){
	  draw_pixel(gl, x1, y1,"BLACK");
	  
	  int x = x1, y = y1;
	  int d = dstart;
	  
	  while(x != x2 && y != y2)  {
		  if (d<= 0) {
			  d = d +incrK;
			  x = x + eixo_X_k;
			  y = y + eixo_Y_k;
		  }else {
			  d = d +incrJ;
			  x = x + eixo_X_j;
			  y = y + eixo_Y_j;
			  
		  }
		  draw_pixel(gl, x, y,"BLACK");
	  }
	  
	   
   }
   
   public void ponto_medio(GL2 gl, int xP, int xQ, int yP, int yQ) {
       int dx, dy, x, y, yInc;

       if(xP >= xQ){
         
           if(xP == xQ){ // Nao Permitido porque dividimos por dx = xQ - xP
               return;
           }
         
           // xP > xQ, então permute os ponto P e Q
           int t;
     
           t = xP;
           xP = xQ;
           xQ = t;
     
           t = yP;
           yP = yQ;
           yQ = t;
       }
     
       //Agora xP < xQ
       if(yQ >= yP){
           yInc = 1;
           dy = yQ - yP; // Caso normal, yP < yQ
       }else{
           yInc = -1;
           dy = yP - yQ;
       }
     
       dx = xQ - xP; // dx > 0 , dy >0
     
       int dy4 = dy * 4,
           v = dy4 - dx,
           dx2 = 2 * dx,
           dy2 = 2 * dy,
           dy4Minusdx2 = dy4 - dx2,
           dy4Minusdx4 = dy4Minusdx2 - dx2;
           
      // draw_pixel(gl, xP, yP);
     
       y = yP;
     
       for( x = xP; x < xQ -1; ){
           if(v < 0){ //Padrão 1 , equivalente a d + 2 * m < 0,5
               draw_pixel(gl, x++, y, "BLACK");
               draw_pixel(gl, x++, y, "BLACK");
               v = dy4; // Equivalente a d + 2 * m
             
           }else if( v < dx2){    //Padrão 2 ou 3 , equivalente a d + 2 * m < 1,5
               if(v < dy2){ //Padrão 2 , equivalente a d + m < 0,5
                   draw_pixel(gl, x++, y, "BLACK");
                   draw_pixel(gl, x++, y += yInc, "BLACK");
                   v += dy4Minusdx2; // Equivalente a d += 2 * m - 1
               }else{ // Padrão 3
                   draw_pixel(gl, x++, y += yInc, "BLACK");
                   draw_pixel(gl, x++, y, "BLACK" );
                   v += dy4Minusdx2; // Equivalente a d += 2 * m - 1
               }
           }else{ //Padrão 4
               draw_pixel(gl, x++, y += yInc, "BLACK");
               draw_pixel(gl, x++, y += yInc, "BLACK");
               v += dy4Minusdx4; // Equivalente a d += 2 * m - 2
             
           }
       }
       if(x < xQ){ // x = xQ -1
           draw_pixel(gl, xQ, yQ, "BLACK");
       }
   }
   
   public void dda(GL2 gl, int x1, int x2, int y1, int y2) {
	   
	  int Lenght;
	  float x = x1, x2_float = x2;
	  float y = y1;
	  float Xinc, Yinc;
	  int x_aux, y_aux;
	  
	  int dx,dy;
	  dx = Math.abs(x2 - x1);
	  dy = Math.abs(y2 - y1);
	  
	  Lenght = Math.abs(x2 - x1);
	  
	  if (dx > dy) {
		  
		  Lenght = dx;
		  
	  }else {
		  
		  Lenght = dy;
	  }
	  
	  Xinc = (float) dx / Lenght;
	  Yinc = (float) dy / Lenght;
	  
	  for (int i = 0; i <= Lenght; i++) {
		  x_aux = Math.round(x);
          y_aux = Math.round(y);  
		  draw_pixel(gl, x_aux, y_aux, "BLUE");
		  
		  x = x + Xinc;
		  y = y + Yinc;
		  
	  }
	   
   }
   
   public void equacao(GL2 gl, int x1, int x2, int y1, int y2) {
	   
	  int y_aux;
	  
	  int x;
	  float m,y;
	  int dx,dy;
	  dx = Math.abs(x2 - x1);
	  dy = Math.abs(y2 - y1);
	  
	  m = (float) dy / dx;
	  x = x1;
	  while(x <= x2) {
		 y =  m * (x - x1) + y1;
		 y_aux = Math.round(y);
		 draw_pixel(gl, x, y_aux, "GREEN");
		 x++;
	  }
	   
   }

   public static void main(String[] args) {
 
    }


}
