package cti.app.component;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cti.app.constant.AppConstant;
import cti.app.constant.CutterConstant;
import cti.app.constant.FindFileConstant;
import cti.app.constant.SpecManagerConstant;
import cti.app.constant.TestConstant;
import cti.app.service.AppService;
import cti.app.view.CutterView;
import cti.app.view.FindFileView;
import cti.app.view.LogManagerView;
import cti.app.view.SpecManagerView;
import cti.app.view.TestView;

public class JTabbedPaneSimple extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	JLabel jl = AppConstant.jl_status;

	public JTabbedPaneSimple() {
		super();
		selfSetting();
	}

	private void selfSetting() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);// 左右滾動
	}

	public void setComponent() {
		setForeground(AppConstant.APP_COLOR_DEFAULT);
		setFont(AppConstant.APP_FONT);
		addTab(CutterConstant.TAB_NAME, CutterView.createView());
		addTab(SpecManagerConstant.TAB_NAME, SpecManagerView.createView());
		addTab(FindFileConstant.TAB_NAME, FindFileView.createView());
		addTab(LogManagerView.TAB_NAME, LogManagerView.createView());
		addTab(TestConstant.TAB_NAME, TestView.createView());
		addTab("未定", new JPanel());

		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				if (SpecManagerConstant.TAB_NAME.equals(getTitleAt(getSelectedIndex()))) {
					SpecManagerView.setEnterTab();
				}
				AppService.showSatusNoTimer(Arrays.asList(AppConstant.MSG_WELCOME, getTitleAt(getSelectedIndex())));
			}
		});
	}
}
