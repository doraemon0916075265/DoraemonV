package cti.app.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import cti.app.constant.AppConstant;

public class JButtonSimple extends JButton {

	private static final long serialVersionUID = 1L;

	private static JframeSimple jf = AppConstant.jf;

	public JButtonSimple() {
		super();
		selfSetting();
	}

	public JButtonSimple(String name) {
		super(name);
		setName(name);
		selfSetting();
	}

	// 元件設定
	private void selfSetting() {
		setFont(AppConstant.APP_FONT);
		setForeground(AppConstant.APP_COLOR_DEFAULT);

		addMouseListener(new MouseListener() {
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
