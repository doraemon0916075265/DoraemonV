package cti.app.component;

import java.util.List;

import cti.app.constant.AppConstant;

public class JLabelStatus extends JLabelSimple {

	public JLabelStatus() {
		super();
		selfSetting();
	}

	public JLabelStatus(String name) {
		super(name);
		selfSetting();
	}

	private void selfSetting() {
		setForeground(AppConstant.APP_COLOR_MSG);
	}

	// [%s][%s %s] %s
	public void setText(String status, List<String> msgs) {
		if (status.equals(AppConstant.MSG_SUCCESS)) {
			setForeground(AppConstant.APP_COLOR_MSG);
		} else {
			setForeground(AppConstant.APP_COLOR_ERRMSG);
		}

		JTabbedPaneSimple jtp = AppConstant.jtp;
		String page = (jtp == null ? "" : jtp.getTitleAt(jtp.getSelectedIndex()));
		String now = AppConstant.APP_MSG_FMT.format(System.currentTimeMillis());
		String msg = String.join(AppConstant.SIGN_SPACE, msgs);

		super.setText(String.format(AppConstant.FORMAT_MSG, page, now, status, msg).trim());
	}
}
