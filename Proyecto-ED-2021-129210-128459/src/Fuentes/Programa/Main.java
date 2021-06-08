package Programa;

import java.awt.EventQueue;

import GUI.GUI;

/**
 * Ejecuta la aplicación
 * @author Antich Octavio y Leguizamón Rodolfo
 *
 */
public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
