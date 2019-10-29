package cti.app.component;

import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;

import cti.app.constant.AppConstant;

public class JComboBoxSimple<E> extends JComboBox<E> {

	private static final long serialVersionUID = 1L;

	public JComboBoxSimple() {
		super();
		selfSetting();
	}

	public JComboBoxSimple(List<E> items) {
		super();
		selfSetting();
		for (E string : items) {
			addItem(string);
		}
	}

	private void selfSetting() {
		setFont(AppConstant.APP_FONT);
		setForeground(AppConstant.APP_COLOR_DEFAULT);
		setBackground(Color.WHITE);
	}

}
