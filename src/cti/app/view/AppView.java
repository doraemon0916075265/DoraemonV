package cti.app.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cti.app.service.AppService;

public class AppView extends AppService {
	private static JFrame jf;

	public static void executeApp() {
		setFrameBegin();
		setFramePosition();
		setFrameComponent();
		setFrameListener();
		setFrameEnd();
	}

	/*** 視窗初始設定 ***/
	private static void setFrameBegin() {
		jf = new JFrame(APP_FRAME_TITLE);
		jf.setLocale(JComponent.getDefaultLocale());
		jf.setSize(APP_FRAME_WIDTH, APP_FRAME_HEIGHT);// 視窗大小
		jf.setLocationRelativeTo(null);// 視窗置中
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(new BorderLayout());
		jf.setResizable(false);
		jf.setLayout(new BorderLayout());
	}

	/*** 設定位置 ***/
	private static void setFramePosition() {
		jf.add(jl_msg, BorderLayout.SOUTH);
	}

	/*** 設定元件 ***/
	private static void setFrameComponent() {
		jtp.addTab(APP_TAB_NAME[0], CutterView.createView());
		jtp.addTab(APP_TAB_NAME[1], FindFileView.createView());
		jtp.addTab(APP_TAB_NAME[2], TestView.createView());
		jtp.addTab(APP_TAB_NAME[3], null);
		jtp.addTab(APP_TAB_NAME[4], null);
		jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);// 左右滾動

		setAppStyle(jtp, "頁籤", APP_COLOR_DEFAULT);
		setAppStyle(jl_msg, "系統訊息", APP_COLOR_MSG);
	}

	/*** 設定Listener ***/
	private static void setFrameListener() {
		jtp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				showMsg("歡迎進入 " + getSelectedTabName());
			}
		});
	}

	/*** 視窗結尾設定 ***/
	private static void setFrameEnd() {
		jf.add(jtp);
		jf.setVisible(true);// 視窗顯示
	}

}
