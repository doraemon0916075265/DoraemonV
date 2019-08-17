package cti.app.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class JButtonSimple extends JButton {

	public JButtonSimple() {
		super();
		setButtonListener(this);
	}

	public JButtonSimple(String name) {
		super(name);
		setButtonListener(this);
	}

	// 按鈕設定
	private static void setButtonListener(JButton jb) {
		jb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent me) {
				jb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent me) {
				jb.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				jb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				jb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent me) {
				jb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
}
