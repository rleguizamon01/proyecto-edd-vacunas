package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Auxiliar.Persona;

import javax.swing.JOptionPane;
import Programa.Logica;
import TDAColaCP.InvalidKeyException;

public class TabInscripcion extends JPanel {
	private JTextField textNombre;
	private JTextField textDNI;
	private JTextField textApellido;
	private JComboBox comboBoxGrupoR;
	private Logica logica;
	
	
	public TabInscripcion(JTabbedPane tabbedPane, Logica logica) {
		
		this.logica = logica;
		
		tabbedPane.addTab("Inscripci\u00F3n", null, this, null);
		this.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 83, 269, 20);
		this.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 58, 269, 14);
		this.add(lblNewLabel);
		
		JLabel lblDNI = new JLabel("N\u00FAmero de documento");
		lblDNI.setBounds(10, 124, 258, 14);
		this.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(10, 149, 269, 20);
		this.add(textDNI);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(307, 58, 258, 14);
		this.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(307, 83, 258, 20);
		this.add(textApellido);
		
		JLabel lblGrupoR = new JLabel("Grupo de riesgo (1 menos riesgoso)");
		lblGrupoR.setBounds(307, 124, 258, 14);
		this.add(lblGrupoR);
		
		comboBoxGrupoR = new JComboBox();
		comboBoxGrupoR.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBoxGrupoR.setBounds(307, 148, 258, 20);
		this.add(comboBoxGrupoR);
		
		JButton btnInscribir = new JButton("Inscribir");
		btnInscribir.setBounds(10, 216, 89, 23);
		this.add(btnInscribir);
		btnInscribir.addActionListener((ActionListener) new ActionListenerBtnInscribir());
	}
	
	private class ActionListenerBtnInscribir implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Persona per = null;
			try {
				per = logica.inscribir(textNombre.getText(), textApellido.getText(), Integer.parseInt(textDNI.getText()), Integer.parseInt(comboBoxGrupoR.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(null, per.toString() + "GR: " + comboBoxGrupoR.getSelectedItem().toString(), "Inscripción exitosa", JOptionPane.PLAIN_MESSAGE);
			} catch (NumberFormatException | InvalidKeyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
