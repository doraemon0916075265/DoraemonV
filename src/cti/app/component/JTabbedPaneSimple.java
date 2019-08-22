package cti.app.component;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cti.app.constant.AppConstant;
import cti.app.constant.CutterConstant;
import cti.app.constant.FindFileConstant;
import cti.app.constant.SpecInfoConstant;
import cti.app.constant.TestConstant;
import cti.app.service.AppService;
import cti.app.view.CutterView;
import cti.app.view.FindFileView;
import cti.app.view.SpecInfoView;
import cti.app.view.TestView;

public class JTabbedPaneSimple extends JTabbedPane {
	JLabel jl = AppConstant.jl_status;

	public JTabbedPaneSimple() {
		super();
		SelfSetting();
	}

	private void SelfSetting() {
		setForeground(Color.BLACK);
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);// 左右滾動
	}

	public void setComponent() {
		setFont(AppConstant.APP_FONT);

		addTab(CutterConstant.TAB_NAME, CutterView.createView());
		addTab(SpecInfoConstant.TAB_NAME, SpecInfoView.createView());
		addTab(FindFileConstant.TAB_NAME, FindFileView.createView());
		addTab(TestConstant.TAB_NAME, TestView.createView());
		addTab("未定", new JPanel());

		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				if (SpecInfoConstant.TAB_NAME.equals(getTitleAt(getSelectedIndex()))) {
					SpecInfoView.setEnterTab();
				}
				AppService.showTabChangeSatus(AppConstant.MSG_WELCOME);
			}
		});
	}
}
