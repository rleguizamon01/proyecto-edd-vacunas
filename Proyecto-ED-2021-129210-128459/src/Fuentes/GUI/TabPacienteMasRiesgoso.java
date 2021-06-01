package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabPacienteMasRiesgoso extends JPanel{
	public TabPacienteMasRiesgoso(JTabbedPane tabbedPane) {
		tabbedPane.addTab("Paciente m\u00E1s riesgoso", null, this, null);
	}
}
