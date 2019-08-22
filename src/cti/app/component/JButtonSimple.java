package cti.app.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import cti.app.constant.AppConstant;

public class JButtonSimple extends JButton {
	private static JframeSimple jf = AppConstant.jf;

	public JButtonSimple() {
		super();
		componentListener(this);
	}

	public JButtonSimple(String name) {
		super(name);
		componentListener(this);
	}

	// 元件設定
	private static void componentListener(JButton jb) {
		jb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent me) {
				jf.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent me) {
				jf.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				jf.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				jf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent me) {
				jf.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
}
