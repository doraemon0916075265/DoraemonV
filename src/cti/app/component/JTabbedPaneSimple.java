package cti.app.component;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cti.app.constant.AppConstant;
import cti.app.constant.Tab01_CutterConstant;
import cti.app.constant.Tab03_FindFileConstant;
import cti.app.constant.Tab02_SpecManagerConstant;
import cti.app.constant.Tab05_TestConstant;
import cti.app.service.AppService;
import cti.app.view.Tab01_CutterView;
import cti.app.view.Tab03_FindFileView;
import cti.app.view.Tab04_LogManagerView;
import cti.app.view.Tab02_SpecManagerView;
import cti.app.view.Tab05_TestView;

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
		addTab(Tab01_CutterConstant.TAB_NAME, Tab01_CutterView.createView());
		addTab(Tab02_SpecManagerConstant.TAB_NAME, Tab02_SpecManagerView.createView());
		addTab(Tab03_FindFileConstant.TAB_NAME, Tab03_FindFileView.createView());
		addTab(Tab04_LogManagerView.TAB_NAME, Tab04_LogManagerView.createView());
		addTab(Tab05_TestConstant.TAB_NAME, Tab05_TestView.createView());
		addTab("未定", new JPanel());

		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				if (Tab02_SpecManagerConstant.TAB_NAME.equals(getTitleAt(getSelectedIndex()))) {
					Tab02_SpecManagerView.setEnterTab();
				}
				AppService.showSatusNoTimer(Arrays.asList(AppConstant.MSG_WELCOME, getTitleAt(getSelectedIndex())));
			}
		});
	}
}
