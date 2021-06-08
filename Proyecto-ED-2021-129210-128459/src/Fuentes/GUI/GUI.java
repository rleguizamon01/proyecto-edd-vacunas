package GUI;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Programa.Logica;

public class GUI {

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	protected Logica logica;

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
		new TabPacientesHistoricos(tabbedPane, logica);
	
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
