package cti.app.view;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cti.app.appService.Style;
import cti.app.component.JButtonFilePath;
import cti.app.component.JButtonSimple;
import cti.app.component.JLabelSimple;
import cti.app.component.JPanelSimple;
import cti.app.component.JTextAreaSimple;
import cti.app.component.JTextFieldSimple;
import cti.app.constant.TestConstant;

public class TestView extends TestConstant {
	private static JPanelSimple jp = new JPanelSimple();

	private static JLabelSimple jl_logFilePath = new JLabelSimple("btn1");
	protected static JTextFieldSimple jtf_logFilePath = new JTextFieldSimple(jl_logFilePath);
	private static JButtonFilePath jb_logFilepath = new JButtonFilePath(jtf_logFilePath);
	private static JButtonSimple jb_resetData = new JButtonSimple("btn1");

	private static JLabelSimple jl_specFilePath = new JLabelSimple("btn2");
	protected static JTextFieldSimple jtf_specFilePath = new JTextFieldSimple(jl_specFilePath);
	private static JButtonFilePath jb_specFilepath = new JButtonFilePath(jtf_specFilePath);
	private static JButtonSimple jb_clearData = new JButtonSimple("btn2");

	private static JLabelSimple jl_logInfo_send = new JLabelSimple("btn3");
	protected static JTextFieldSimple jtf_logInfo_send = new JTextFieldSimple(jl_logInfo_send);
	private static JLabelSimple jl_logInfo_sendLen = new JLabelSimple(STR_ZERO);
	private static JButtonSimple jb_readFile = new JButtonSimple("btn3");

	private static JLabelSimple jl_logInfo_fill = new JLabelSimple("btn4");
	protected static JTextFieldSimple jtf_logInfo_fill = new JTextFieldSimple(jl_logInfo_fill);
	private static JLabelSimple jl_logInfo_fillLen = new JLabelSimple(STR_ZERO);
	private static JButtonSimple jb_analysis = new JButtonSimple("btn4");

	private static JTextAreaSimple jta_result = new JTextAreaSimple(10, true);
	private static JPanelSimple jpSub1 = new JPanelSimple();
	private static JPanelSimple jpSub2 = new JPanelSimple();

	public static JPanel createView() {
		setBegin();
		setPosition();
		setComponent();
		setListener();
		setEnd();
		return jp;
	}

	private static void setBegin() {
		jpSub1.setLayout(null);
		jpSub1.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 325));
		jpSub2.setPreferredSize(new Dimension(APP_FRAME_WIDTH, 300));
	}

	private static void setPosition() {
		/*** 上半部，第一區 ***/
		Style.resetRow();
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_logFilePath, jtf_logFilePath, jb_logFilepath, null, jb_resetData, null));
		jpSub1.add(Arrays.asList(jl_logFilePath, jtf_logFilePath, jb_logFilepath, jb_resetData));
		/*** 第二區 ***/
		Style.setBounds(Style.MODEL_JL_JTF_JB_JC_BTN, Arrays.asList(null, jl_specFilePath, jtf_specFilePath, jb_specFilepath, null, jb_clearData, null));
		jpSub1.add(Arrays.asList(jl_specFilePath, jtf_specFilePath, jb_specFilepath, jb_clearData));
		/*** 第三區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl_logInfo_send, jtf_logInfo_send, null, jl_logInfo_sendLen, jb_readFile, null));
		jpSub1.add(Arrays.asList(jl_logInfo_send, jtf_logInfo_send, jl_logInfo_sendLen, jb_readFile));
		/*** 第四區 ***/
		Style.setBounds(Style.MODEL_JL1_JTF_NULL_JL2_BTN, Arrays.asList(null, jl_logInfo_fill, jtf_logInfo_fill, null, jl_logInfo_fillLen, jb_analysis, null));
		jpSub1.add(Arrays.asList(jl_logInfo_fill, jtf_logInfo_fill, jl_logInfo_fillLen, jb_analysis));

		/*** 下半部 ***/
		jpSub2.add(Arrays.asList(new JScrollPane(jta_result)));

		jp.add(Arrays.asList(jpSub1, jpSub2));
	}

	private static void setComponent() {

	}

	private static void setListener() {
		// new Timer(100, new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent ae) {
		// currentProgress++;
		// if (currentProgress > PROGRESS_MAX) {
		// currentProgress = PROGRESS_MIN;
		// }
		// jpb.setValue(currentProgress);
		// }
		// }).start();
		//
		// jpb.addChangeListener(new ChangeListener() {
		// @Override
		// public void stateChanged(ChangeEvent ce) {
		// System.out.println(jpb.getPercentComplete());
		// }
		// });
	}

	private static void setEnd() {

	}

}
