package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import Auxiliar.Persona;
import Programa.Logica;
import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.InvalidKeyException;


public class TabListadoPacientes extends JPanel {
	
	private Logica logica;
	private JTextArea textAreaListado;
	
	public TabListadoPacientes(JTabbedPane tabbedPane, Logica logica) {
		this.logica = logica;
		tabbedPane.addTab("Listado pacientes", null, this, null);
		this.setLayout(null);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(10, 20, 89, 23);
		this.add(btnListar);
		btnListar.addActionListener((ActionListener) new ActionListenerBtnListar());
		
		JButton btnListarInvertido = new JButton("Listar Invertido");
		btnListarInvertido.setBounds(100, 20, 120, 23);
		this.add(btnListarInvertido);
		btnListarInvertido.addActionListener((ActionListener) new ActionListenerBtnListarInvertido());
		
		textAreaListado = new JTextArea();
		textAreaListado.setBounds(10, 66, 618, 174);
		add(textAreaListado);
		
	}
	
	private class ActionListenerBtnListar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				
				textAreaListado.setText(logica.listaPacientes());
			} catch (EmptyPriorityQueueException | InvalidKeyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage() + "No hay pacientes para mostrar", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ActionListenerBtnListarInvertido implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*Persona per = null;
			try {
				per = logica.inscribir(textNombre.getText(), textApellido.getText(), Integer.parseInt(textDNI.getText()), Integer.parseInt(comboBoxGrupoR.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(null, per.toString() + "GR: " + comboBoxGrupoR.getSelectedItem().toString(), "Inscripción exitosa", JOptionPane.PLAIN_MESSAGE);
			} catch (NumberFormatException | InvalidKeyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}*/
			
		}
	}
}
