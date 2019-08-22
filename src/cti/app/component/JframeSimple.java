package cti.app.component;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

import cti.app.constant.AppConstant;

public class JframeSimple extends JFrame {

	public JframeSimple() {
		super();
		SelfSetting();
	}

	public JframeSimple(String name) {
		super(name);
		SelfSetting();
	}

	private void SelfSetting() {
		setLocale(JComponent.getDefaultLocale());
		setSize(AppConstant.APP_FRAME_WIDTH, AppConstant.APP_FRAME_HEIGHT);// 視窗大小
		setLocationRelativeTo(null);// 視窗置中
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setLayout(new BorderLayout());
	}

}
