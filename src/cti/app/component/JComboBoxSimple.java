package cti.app.component;

import java.awt.Color;

import javax.swing.JComboBox;

import cti.app.constant.AppConstant;

public class JComboBoxSimple<E> extends JComboBox<E> {

	private static final long serialVersionUID = 1L;

	public JComboBoxSimple() {
		super();
		selfSetting();
	}

	private void selfSetting() {
		setFont(AppConstant.APP_FONT);
		setForeground(AppConstant.APP_COLOR_DEFAULT);
		setBackground(Color.WHITE);
	}

}
