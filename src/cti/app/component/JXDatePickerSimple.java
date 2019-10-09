package cti.app.component;

import javax.swing.JLabel;

import org.jdesktop.swingx.JXDatePicker;

import cti.app.constant.AppConstant;

public class JXDatePickerSimple extends JXDatePicker {

	private static final long serialVersionUID = 1L;

	public JXDatePickerSimple() {
		super();
		selfSetting();
	}

	public JXDatePickerSimple(JLabel jl) {
		super();
		setName(jl.getName());
		selfSetting();
	}

	private void selfSetting() {
		setFont(AppConstant.APP_FONT);
		setForeground(AppConstant.APP_COLOR_DEFAULT);
	}

}
