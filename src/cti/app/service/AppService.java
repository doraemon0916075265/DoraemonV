package cti.app.service;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import cti.app.constant.AppConstant;

public class AppService extends AppConstant {
	private static long now;
	private static boolean isTimerOn;
	private static long timer;

	/*** 雙擊複製到剪貼簿 ***/
	public void dbClickOnCopy(JTextComponent jtc) {
		jtc.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent me) {
			}

			@Override
			public void mousePressed(MouseEvent me) {
			}

			@Override
			public void mouseExited(MouseEvent me) {
			}

			@Override
			public void mouseEntered(MouseEvent me) {
			}

			@Override
			public void mouseClicked(MouseEvent me) {
				mousedbClicked(me);
			}

			public void mousedbClicked(MouseEvent me) {
				if (me.getClickCount() >= 2 && !me.isConsumed() && !jtc.getText().isEmpty()) {
					me.isConsumed();
					StringSelection data = new StringSelection(jtc.getText());
					APP_CLIPBOARD.setContents(data, data);
					showMsg(MSG_SUCCESS, String.format(FORMAT_MSG_COPIED, jtc.getName()));
				}
			}
		});
	}

	/*** 取電文長度(輸入,輸出) ***/
	public void getInputTextLength(JTextComponent input, JLabel output) {
		input.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent de) {
				getIntputLen();
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
				getIntputLen();
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				getIntputLen();
			}

			private void getIntputLen() {
				try {
					output.setText(Integer.toString(getGBKLen(input.getText())));
				} catch (Exception e) {
					output.setText(STR_ZERO);
				}
			}
		});
	}

	/*** 取電文長度(輸入,輸出) ***/
	public void getInputIntegerArraySum(List<JTextComponent> inputs, JLabel output) {
		for (JTextComponent input : inputs) {
			input.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void removeUpdate(DocumentEvent de) {
					getIntputLen();
				}

				@Override
				public void insertUpdate(DocumentEvent de) {
					getIntputLen();
				}

				@Override
				public void changedUpdate(DocumentEvent de) {
					getIntputLen();
				}

				private void getIntputLen() {
					List<String> list = new ArrayList<>();
					try {
						for (JTextComponent item : inputs) {
							list.add(getIntegerArrayLength2String(item.getText()));
						}
						output.setText(String.join(SIGN_VERTICAL_BAR01, list));
					} catch (Exception e) {
						output.setText(STR_ZERO);
					}
				}
			});
		}
	}

	/*** 數字陣列 的 數字和 轉字串 ***/
	private static String getIntegerArrayLength2String(String str) {
		Integer cutSize = Integer.valueOf(0);
		try {
			JSONArray ja = new JSONArray(str);
			for (Object obj : ja) {
				cutSize += Integer.parseInt(obj.toString());
			}
			return cutSize.toString();
		} catch (Exception e) {
			return STR_ZERO;
		}
	}

	/*** 取GBK長度 ***/
	public int getGBKLen(String str) throws UnsupportedEncodingException {
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("轉GBK長度錯誤");
		}
	}

	/*** 計時器 ***/
	public static void isTimerWork(boolean isOn) {
		timer = 0;
		isTimerOn = isOn;
		if (isOn) {
			now = System.currentTimeMillis();
		} else {
			timer = System.currentTimeMillis() - now;
		}
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
			jl_msg.setText(String.format(FORMAT_MSG, getSelectedTabName(), APPMSG_SDF.format(System.currentTimeMillis()), MSG_SUCCESS, msg, timeMsg));
		} else {
			jl_msg.setText(String.format(FORMAT_MSG, getSelectedTabName(), APPMSG_SDF.format(System.currentTimeMillis()), MSG_SUCCESS, msg, ""));
		}
	}

	/*** 訊息欄 ***/
	public static void showMsg(String msgType, String msg) {
		if (isTimerOn) {
			isTimerWork(false);
		}
		jl_msg.setText(String.format(FORMAT_MSG, getSelectedTabName(), APPMSG_SDF.format(System.currentTimeMillis()), msgType, msg, ""));
	}

	/*** 訊息欄 ***/
	public static void showMsg(String msgType, String msg1, String msg2) {
		if (isTimerOn) {
			isTimerWork(false);
		}
		jl_msg.setText(String.format(FORMAT_MSG, getSelectedTabName(), APPMSG_SDF.format(System.currentTimeMillis()), msgType, msg1, msg2));
	}

	/*** 取得Tab名 ***/
	public static String getSelectedTabName() {
		return jtp.getSelectedIndex() >= 0 ? APP_TAB_NAME[jtp.getSelectedIndex()] : "";
	}

	/*** 取得檔案路徑 ***/
	public void btnGetPath(JButton jb, JTextField jtf) {
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					jtf.setText(jfc.getSelectedFile().toString());
					showMsg(MSG_SUCCESS, MSG_GET + jtf.getName(), "(" + jfc.getSelectedFile().toString() + ")");
				}
			}
		});
	}

	/*** 樣式：一般(物件,命名,顏色) ***/
	public void setAppStyle(JComponent jc, String name, Color fontColor) {
		if (StringUtils.isNotBlank(name)) {
			jc.setName(name);
		}
		jc.setFont(APP_FONT);
		jc.setForeground(fontColor);
	}

	/*** 樣式：純顯示訊息(物件,命名,顏色) ***/
	public void setAppStyle4Info(JComponent jc, String name, Color fontColor) {
		setAppStyle(jc, name, fontColor);
		jc.setBackground(null);
		jc.setBorder(null);
		((JTextComponent) jc).setEditable(false);
	}

	/*** 樣式：一般TextArea(物件,命名,顏色,可編輯) ***/
	public void setAppStyle4TextArea(JComponent jc, String name, Color fontColor, boolean isEditable) {
		setAppStyle(jc, name, fontColor);
		((JTextComponent) jc).setEditable(isEditable);
	}
}
