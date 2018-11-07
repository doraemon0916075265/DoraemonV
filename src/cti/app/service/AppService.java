package cti.app.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.JSONArray;

import cti.app.constant.AppConstant;

public class AppService extends AppConstant {

	/*** 取電文長度(輸入,輸出) ***/
	public void getInputTextLength(JTextField input, JLabel output) {
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
	public void getInputIntegerArraySum(List<JTextField> inputs, JLabel output) {
		for (JTextField input : inputs) {
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
						for (JTextField item : inputs) {
							list.add(getIntegerArrayLength2String(item.getText()));
						}
						output.setText(String.join(" | ", list));
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
	public int getGBKLen(String str) throws UnsupportedEncodingException, Exception {
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("轉GBK長度錯誤");
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
