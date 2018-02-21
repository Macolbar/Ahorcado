package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Control;

public class Principal extends AhorcadoUI{
	
	Control control=new Control();
	
	public Principal() {
		super();
		txtLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnYa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPalabraSecreta.getText().isEmpty())
					txtMensaje.setText("Escribe algo");
				else{
					//aqui hemos llegado porque la palabra es valida
					control.setPalabreja(txtPalabraSecreta.getText());
					control.iniciaAciertos();
					//comportamiento del ui tras validar palabra
					//Ocultar los componentes que se han usado para introducir la 
					//palabra secreta
					txtPalabraSecreta.setVisible(false);
					lblPalabraSecreta.setVisible(false);
					btnYa.setVisible(false);
					//hacemos visible el titulo
					lblTitulo.setVisible(true);
					//habilitar el txt de la letra
					txtLetra.setEditable(true);
					
					
				}
			}
		});
		
		txtLetra.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!txtLetra.getText().isEmpty()){
					txtMensaje.setText("");	
					char letra= txtLetra.getText().charAt(0);
					txtLetra.setText("");
					
					if(control.getFallos()<6){
						if(control.anotarAciertos(letra)){
							txtAciertos.setText(control.getAciertos());
							if(control.palabraAcertada()){
								txtMensaje.setText("Has ganado");
								txtLetra.setEditable(false);
							}
						}
					}else{
						txtFallos.setText(String.valueOf(control.getFallos()));
						txtMensaje.setText("GAME OVER. Has perdido");
						txtLetra.setEditable(false);
					}
					
					
				}else{
					txtMensaje.setText("Escribe algo");
					
				}
			}
		});
		
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//IMPORTANTE: Se crea un objeto de Principal no del UI
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
