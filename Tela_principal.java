package Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_principal extends JFrame {

	public JPanel contentPane;
	public JTextField Field_Y_1;
	public JTextField Field_X_1;
	public JTextField Field_X_2;
	public JTextField Field_Y_2;
	public JTextField Field_malha;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_principal frame = new Tela_principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_principal() {
		setLocation(new Point(1000, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
		);
		panel.setLayout(null);
		
		Field_Y_1 = new JTextField();
		Field_Y_1.setBounds(250, 67, 52, 29);
		panel.add(Field_Y_1);
		Field_Y_1.setColumns(2);
		
		JLabel Label_Inicial = new JLabel("Informe os pontos iniciais:");
		Label_Inicial.setFont(new Font("Tahoma", Font.BOLD, 19));
		Label_Inicial.setBounds(63, 25, 274, 23);
		panel.add(Label_Inicial);
		
		JLabel Label_X_1 = new JLabel("X:");
		Label_X_1.setBounds(67, 70, 18, 23);
		panel.add(Label_X_1);
		
		Field_X_1 = new JTextField();
		Field_X_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Field_X_1.setBounds(90, 67, 52, 29);
		panel.add(Field_X_1);
		Field_X_1.setColumns(2);
		
		JLabel Label_Y_1 = new JLabel("Y:");
		Label_Y_1.setBounds(230, 70, 18, 23);
		panel.add(Label_Y_1);
		
		JLabel Label_final = new JLabel("Informe os pontos finais:");
		Label_final.setFont(new Font("Tahoma", Font.BOLD, 19));
		Label_final.setBounds(63, 114, 255, 23);
		panel.add(Label_final);
		
		Field_X_2 = new JTextField();
		Field_X_2.setColumns(2);
		Field_X_2.setBounds(90, 156, 52, 29);
		panel.add(Field_X_2);
		
		JLabel Label_X_2 = new JLabel("X:");
		Label_X_2.setBounds(67, 159, 18, 23);
		panel.add(Label_X_2);
		
		Field_Y_2 = new JTextField();
		Field_Y_2.setColumns(2);
		Field_Y_2.setBounds(250, 156, 52, 29);
		panel.add(Field_Y_2);
		
		JLabel Label_Y_2 = new JLabel("Y:");
		Label_Y_2.setBounds(230, 159, 18, 23);
		panel.add(Label_Y_2);
		
		JButton Trocar_ini = new JButton("");
		Trocar_ini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String aux = Field_X_1.getText();
			Field_X_1.setText(Field_Y_1.getText());
			Field_Y_1.setText(aux);
			}
		});
		Trocar_ini.setIcon(new ImageIcon("C:\\Users\\emman\\Downloads\\Trocar.png"));
		Trocar_ini.setBounds(169, 67, 40, 31);
		panel.add(Trocar_ini);
		
		JButton Trocar_fin = new JButton("");
		Trocar_fin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = Field_X_2.getText();
				Field_X_2.setText(Field_Y_2.getText());
				Field_Y_2.setText(aux);
			}
		});
		Trocar_fin.setIcon(new ImageIcon("C:\\Users\\emman\\Downloads\\Trocar.png"));
		Trocar_fin.setBounds(169, 156, 40, 31);
		panel.add(Trocar_fin);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(17, 204, 342, 8);
		panel.add(separator_1);
		
		JCheckBox CB_DDA = new JCheckBox("Algoritmo DDA");
		CB_DDA.setBounds(82, 214, 159, 31);
		panel.add(CB_DDA);
		
		JCheckBox CB_Ponto_medio = new JCheckBox("Algoritimo do Ponto M\u00E9dio");
		CB_Ponto_medio.setBounds(82, 245, 255, 31);
		panel.add(CB_Ponto_medio);
		
		JCheckBox CB_Equacao = new JCheckBox("Equa\u00E7\u00E3o expl\u00EDcita da Reta");
		CB_Equacao.setBounds(82, 275, 255, 31);
		panel.add(CB_Equacao);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(17, 321, 342, 8);
		panel.add(separator_2);
		
		JCheckBox CB_Reta = new JCheckBox("Exibir Reta");
		CB_Reta.setBounds(17, 364, 159, 31);
		panel.add(CB_Reta);
		
		JCheckBox CB_malha = new JCheckBox("Mostrar Malha");
		CB_malha.setBounds(17, 332, 159, 31);
		panel.add(CB_malha);
		
		Field_malha = new JTextField();
		Field_malha.setText("15");
		Field_malha.setColumns(2);
		Field_malha.setBounds(307, 332, 61, 29);
		panel.add(Field_malha);
		
		JLabel Label_malha = new JLabel("Tamanho da Malha:");
		Label_malha.setBounds(189, 335, 122, 23);
		panel.add(Label_malha);
		
		JButton BT_Desenhar = new JButton("Desenhar");
		BT_Desenhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					         
		           if(!Field_X_1.getText().isEmpty() &&
		              !Field_X_2.getText().isEmpty() &&
		              !Field_Y_1.getText().isEmpty() &&
		              !Field_Y_2.getText().isEmpty() &&
		              !Field_malha.getText().isEmpty()) {
		        	   
				       int x1 = Integer.parseInt(Field_X_1.getText());
				       int x2 = Integer.parseInt(Field_X_2.getText());
				       int y1 = Integer.parseInt(Field_Y_1.getText());
			           int y2 = Integer.parseInt(Field_Y_2.getText());
			           int size = Integer.parseInt(Field_malha.getText());
		        	   
		        	   final GLProfile profile = GLProfile.get(GLProfile.GL2);
				        GLCapabilities capabilities = new GLCapabilities(profile);
				      
				      // Cria o Canvas
				        final GLCanvas glcanvas = new GLCanvas(capabilities);


				       Retas reta = new Retas(x1,x2,y1,y2);
				       
				       Draw_reta r = new Draw_reta();
				       r.setReta(reta);
				       r.setSize_malha(size);
				       
				       if (CB_DDA.isSelected()) {
				    	  r.setDDA(true);
				       }	
				       
				       if (CB_Ponto_medio.isSelected()) {
				    	   r.setPonto_medio(true);  
				       }
				       
				       if (CB_Equacao.isSelected()) {
				    	   r.setEquacao(true);
				       }
				       
				       if (CB_Reta.isSelected()) {
				    	   r.setExibir_reta(true);  
				       }
				       
				       if (CB_malha.isSelected()) {
				    	   r.setExibir_malha(true); 
				       }
				
				       	glcanvas.addGLEventListener(r);
				        glcanvas.setSize(400, 400);
				      
				      //Criar A tela (Swing)
				        final JFrame frame = new JFrame ("Reta");
				      
				      //Adiciona o canvas na tela
				       frame.getContentPane().add(glcanvas);
				       frame.setSize(frame.getContentPane().getPreferredSize());
				       frame.setVisible(true);
					
		           }
				
			   
			     
				
			}
		});
		BT_Desenhar.setBounds(115, 410, 131, 31);
		panel.add(BT_Desenhar);
		contentPane.setLayout(gl_contentPane);
	}
}
