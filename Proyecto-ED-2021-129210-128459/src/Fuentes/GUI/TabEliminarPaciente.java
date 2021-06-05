package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Auxiliar.NotFoundException;
import Auxiliar.Persona;
import Programa.Logica;
import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.InvalidKeyException;

public class TabEliminarPaciente extends JPanel {
	private JTextField textDNI;
	private Logica logica;
	
	public TabEliminarPaciente(JTabbedPane tabbedPane, Logica logica) {
		
		this.logica = logica;
		
		tabbedPane.addTab("Eliminar paciente", null, this, null);
		this.setLayout(null);
		
		JLabel lblNmeroDeDocumetno_1 = new JLabel("N\u00FAmero de documento");
		lblNmeroDeDocumetno_1.setBounds(10, 55, 258, 14);
		this.add(lblNmeroDeDocumetno_1);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(10, 80, 269, 20);
		this.add(textDNI);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(10, 130, 89, 23);
		this.add(btnEliminar);
		btnEliminar.addActionListener((ActionListener) new ActionListenerBtnEliminar());
	}
	
	private class ActionListenerBtnEliminar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Persona per = null;
			try {
				per = logica.eliminar(Integer.parseInt(textDNI.getText()));
				JOptionPane.showMessageDialog(null , "a", "Eliminado exitosamente", JOptionPane.PLAIN_MESSAGE);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage() + "El DNI debe estar compuesto únicamente por números", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(InvalidKeyException | TDAMapeo.InvalidKeyException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage() + "DNI incorrecto" , "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(NotFoundException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage() + "El DNI ingresado no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(EmptyPriorityQueueException e5) {
				JOptionPane.showMessageDialog(null, e5.getMessage() + "No hay ningún paciente ingresado", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
