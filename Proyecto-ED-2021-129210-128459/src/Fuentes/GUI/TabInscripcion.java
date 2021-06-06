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
		btnInscribir.setBounds(10, 200, 89, 23);
		this.add(btnInscribir);
		btnInscribir.addActionListener((ActionListener) new ActionListenerBtnInscribir());
	}
	
	private class ActionListenerBtnInscribir implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				logica.inscribir(textNombre.getText(), textApellido.getText(), Integer.parseInt(textDNI.getText()), Integer.parseInt(comboBoxGrupoR.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(null, "El paciente fue agregado exitosamente", "Inscripción exitosa", JOptionPane.PLAIN_MESSAGE);
				textNombre.setText("");
				textApellido.setText("");
				textDNI.setText("");
				comboBoxGrupoR.setSelectedIndex(0);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "El DNI no puede ser vacío y debe estar compuesto solo por números", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch(InvalidKeyException e2) {
				JOptionPane.showMessageDialog(null, "Error inesperado", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
