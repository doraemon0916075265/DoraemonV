package cti.app.component;

import javax.swing.JTextArea;

import cti.app.appService.Style;
import cti.app.constant.AppConstant;

public class JTextAreaSimple extends JTextArea {

	private static final long serialVersionUID = 1L;

	public JTextAreaSimple() {
		super();
		selfSetting();
	}

	public JTextAreaSimple(int rowCount, boolean isEditable) {
		super(rowCount, Style.TEXTAREA_WIDTH);
		setEditable(isEditable);
		selfSetting();
	}

	private void selfSetting() {
		setForeground(AppConstant.APP_COLOR_DEFAULT);
		setFont(AppConstant.APP_FONT);
	}

}
