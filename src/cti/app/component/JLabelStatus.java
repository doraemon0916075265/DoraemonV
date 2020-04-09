package cti.app.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import cti.app.constant.AppConstant;
import cti.app.service.AppService;

public class JLabelStatus extends JLabelSimple {

	private static final long serialVersionUID = 1L;

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
		String now = AppService.now(AppConstant.SDF_HHMMSS_1);
		String msg = String.join(AppConstant.SIGN_SPACE, msgs);
		System.out.println("msg=" + msgs);

		super.setText(String.format(AppConstant.FORMAT_MSG, page, now, status, msg).trim());
	}
}
