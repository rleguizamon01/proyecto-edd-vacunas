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

import Programa.Logica;
import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.InvalidKeyException;

public class TabPacientesHistoricos extends JPanel {
	
	private JTextField textDNI;
	private Logica logica;
	
	public TabPacientesHistoricos(JTabbedPane tabbedPane, Logica logica) {
		
		this.logica = logica;
		

		tabbedPane.addTab("Pacientes Historicos", null, this, null);
		this.setLayout(null);
		
		JLabel lblNmeroDeDocumetno_1 = new JLabel("N\u00FAmero de documento");
		lblNmeroDeDocumetno_1.setBounds(10, 58, 258, 14);
		this.add(lblNmeroDeDocumetno_1);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(10, 83, 269, 20);
		this.add(textDNI);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(10, 130, 89, 23);
		this.add(btnBuscar);
		btnBuscar.addActionListener((ActionListener) new ActionListenerBtnBuscar());
	}
	
	private class ActionListenerBtnBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				String cadenaPersona = "";
				cadenaPersona = logica.pacienteHistorico(Integer.parseInt(textDNI.getText()));
				JOptionPane.showMessageDialog(null , cadenaPersona, "Paciente encontrado exitosamente", JOptionPane.PLAIN_MESSAGE);
				textDNI.setText("");
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "El campo DNI no puede estar vacío y debe estar compuesto únicamente por números", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (NotFoundException e1) {
				JOptionPane.showMessageDialog(null, "El DNI ingresado no corresponde a un paciente eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Error inesperado: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
