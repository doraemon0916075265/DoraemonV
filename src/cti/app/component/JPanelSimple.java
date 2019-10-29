package cti.app.component;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class JPanelSimple extends JPanel {

	private static final long serialVersionUID = 1L;

	public JPanelSimple() {
		super();
	}

	public void add(List<JComponent> jComponents) {
		for (JComponent jComponent : jComponents) {
			super.add(jComponent);
		}
	}

}
