package cti.app.component;

import javax.swing.JLabel;

import cti.app.constant.AppConstant;

public class JLabelSimple extends JLabel {

	public JLabelSimple() {
		super();
		selfSetting();
	}

	public JLabelSimple(String name) {
		super(name);
		selfSetting();
		setName(name);
	}

	private void selfSetting() {
		setFont(AppConstant.APP_FONT);
		setForeground(AppConstant.APP_COLOR_DEFAULT);
	}
}
