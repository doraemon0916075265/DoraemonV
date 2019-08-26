package cti.app.component;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cti.app.constant.AppConstant;

public class JTextFieldSimple extends JTextField {
	public JTextFieldSimple() {
		super();
		selfSetting();
	}

	public JTextFieldSimple(JLabel jl) {
		super();
		setName(jl.getName());
	}

	private void selfSetting() {
		setFont(AppConstant.APP_FONT);
		setForeground(AppConstant.APP_COLOR_DEFAULT);
	}

}
