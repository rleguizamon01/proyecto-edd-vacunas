package GUI;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import Auxiliar.Persona;
import Programa.Logica;
import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.InvalidKeyException;
import TDAPila.EmptyStackException;


public class TabListadoPacientes extends JPanel {
	
	private Logica logica;
	private JTextArea textAreaListado;
	private final JScrollPane listadoScroll;
	private final JLabel lblPacienteMasRiesgoso;
	private final JTextArea textAreaPacienteMasRiesgoso;
	
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
		textAreaListado.setEditable(false);
		add(textAreaListado);
		
		listadoScroll = new JScrollPane(textAreaListado);
		listadoScroll.setBounds(10, 66, 618, 141);
		add(listadoScroll);
		
		
		textAreaPacienteMasRiesgoso = new JTextArea();
		textAreaPacienteMasRiesgoso.setBounds(10, 248, 618, 33);
		textAreaPacienteMasRiesgoso.setEditable(false);
		add(textAreaPacienteMasRiesgoso);
		
		lblPacienteMasRiesgoso = new JLabel("Paciente m\u00E1s riesgoso");
		lblPacienteMasRiesgoso.setBounds(10, 220, 221, 14);
		add(lblPacienteMasRiesgoso);	
		
	}
	
	private class ActionListenerBtnListar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				String listadoPacientes = "";
				
				for(Persona paciente : logica.listaPacientes()) {
					listadoPacientes += paciente.toString();
					listadoPacientes += "\n";
				}
				textAreaListado.setText(listadoPacientes);
				textAreaPacienteMasRiesgoso.setText(logica.pacienteRiesgoso().toString());
			} catch (EmptyPriorityQueueException e1) {
				JOptionPane.showMessageDialog(null, "No hay pacientes para mostrar", "ERROR", JOptionPane.ERROR_MESSAGE);
				textAreaListado.setText("");
				textAreaPacienteMasRiesgoso.setText("");
			} catch(InvalidKeyException e2) {
				JOptionPane.showMessageDialog(null, "Error inesperado", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(Exception e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ActionListenerBtnListarInvertido implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				String listadoPacientesInv = "";
				
				for(Persona paciente : logica.listaPacientesInverso()) {
					listadoPacientesInv += paciente.toString();
					listadoPacientesInv += "\n";
				}
				
				textAreaListado.setText(listadoPacientesInv);
				textAreaPacienteMasRiesgoso.setText(logica.pacienteRiesgoso().toString());
			} catch (EmptyPriorityQueueException e1) {
				textAreaListado.setText("");
				textAreaPacienteMasRiesgoso.setText("");
				JOptionPane.showMessageDialog(null, "No hay pacientes para mostrar", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Error inesperado: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
