package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTree;

import Programa.Logica;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GUI {

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	protected Logica logica;
	private final JPanel panel = new JPanel();
	private final JTextArea textAreaListado = new JTextArea();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logica = new Logica();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 659, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 643, 328);
		frame.getContentPane().add(tabbedPane);
		
		initializeTabs();
		
		
	}
	
	private void initializeTabs() {
		new TabInscripcion(tabbedPane, logica);
		new TabEliminarPaciente(tabbedPane, logica);
		new TabListadoPacientes(tabbedPane, logica);
		new TabPacienteMasRiesgoso(tabbedPane);
		
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		textAreaListado.setBounds(10, 66, 618, 174);
	
	}
}
