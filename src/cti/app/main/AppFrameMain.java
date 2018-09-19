package cti.app.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.StringUtils;

import cti.app.constant.AppConstant;
import cti.app.controller.CutterController;
import cti.app.controller.TextFindFileController;

public class AppFrameMain extends AppConstant {
	public static JFrame jf;
	public static JTabbedPane jtp = new JTabbedPane();
	public static JPanel jp1 = new JPanel();
	public static JPanel jp2 = new JPanel();
	public static JLabel jl_msg = new JLabel();// 系統訊息

	private static long now;
	public static boolean isTimerOn;
	public static long timer;

	/*** 視窗初始設定 ***/
	public static void setFrameBegin() {
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
	public static void setFramePosition() {
		jf.add(jl_msg, BorderLayout.SOUTH);
	}

	/*** 設定元件 ***/
	public static void setFrameComponent() {
		CutterController.setJPanel(jp1);
		TextFindFileController.setJPanel(jp2);

		jtp.addTab(APP_TAB01_TITLE, jp1);
		jtp.addTab(APP_TAB02_TITLE, jp2);
		jtp.addTab(APP_TAB03_TITLE, new JPanel());
		jtp.addTab(APP_TAB04_TITLE, new JPanel());
		jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);// 左右滾動

		setAppStyle(jtp, "jtp", APP_COLOR_DEFAULT);
		setAppStyle(jl_msg, "jl_msg", APP_COLOR_MSG);// 系統訊息
	}

	/*** 設定Listener ***/
	public static void setFrameListener() {
		jtp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				showMsg("歡迎進入 " + APP_TABS_TITLE[jtp.getSelectedIndex()]);
			}
		});
	}

	/*** 視窗結尾設定 ***/
	public static void setFrameEnd() {
		jf.add(jtp);
		jf.setVisible(true);// 視窗顯示
	}

	/*** 計時器 ***/
	public static void isTimerWork(boolean isOn) {
		timer = 0;
		isTimerOn = isOn;
		if (isOn) {
			now = Calendar.getInstance().getTime().getTime();
		} else {
			timer = Calendar.getInstance().getTime().getTime() - now;
		}
	}

	/*** 樣式：一般 ***/
	public static void setAppStyle(JComponent jc, String name, Color fontColor) {
		if (StringUtils.isNotBlank(name)) {
			jc.setName(name);
		}
		jc.setFont(APP_FONT);
		jc.setForeground(fontColor);
	}

	/*** 樣式：純顯示訊息 ***/
	public static void setAppStyle4Info(JComponent jc, String name, Color fontColor) {
		setAppStyle(jc, name, fontColor);
		jc.setBackground(null);
		jc.setBorder(null);
		((JTextComponent) jc).setEditable(false);
	}

	/*** 樣式：一般TextArea ***/
	public static void setAppStyle4TextArea(JComponent jc, String name, Color fontColor) {
		setAppStyle(jc, name, fontColor);
		jc.setBorder(APP_COLOR_COMPONENT_BORDER);
		((JTextComponent) jc).setEditable(false);
	}

	/*** 訊息欄 ***/
	public static void showMsg(String msg) {
		if (isTimerOn) {
			isTimerWork(false);
			String timeMsg = "";
			if (timer >= 1000L) {
				timeMsg = String.format(FORMAT_MSG_TIMER_S, timer / 1000d);
			} else {
				timeMsg = String.format(FORMAT_MSG_TIMER_MS, timer);
			}
			jl_msg.setText(String.format(FORMAT_MSG, APP_TABS_TITLE[jtp.getSelectedIndex()], APPMSG_SDF.format(Calendar.getInstance().getTime()), MSG_SUCCESS, msg, timeMsg));
		} else {
			jl_msg.setText(String.format(FORMAT_MSG, APP_TABS_TITLE[jtp.getSelectedIndex()], APPMSG_SDF.format(Calendar.getInstance().getTime()), MSG_SUCCESS, msg, ""));
		}
	}

	/*** 訊息欄 ***/
	public static void showMsg(String msgType, String msg) {
		if (isTimerOn) {
			isTimerWork(false);
		}
		jl_msg.setText(String.format(FORMAT_MSG, APP_TABS_TITLE[jtp.getSelectedIndex()], APPMSG_SDF.format(Calendar.getInstance().getTime()), msgType, msg, ""));
	}

	/*** 訊息欄 ***/
	public static void showMsg(String msgType, String msg1, String msg2) {
		if (isTimerOn) {
			isTimerWork(false);
		}
		jl_msg.setText(String.format(FORMAT_MSG, APP_TABS_TITLE[jtp.getSelectedIndex()], APPMSG_SDF.format(Calendar.getInstance().getTime()), msgType, msg1, msg2));
	}
}
