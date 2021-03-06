package cti.app.service;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.JXDatePicker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.universalchardet.UniversalDetector;

import cti.app.constant.AppConstant;

public class AppService extends AppConstant {
	protected static final String INIT_JSONARRRAY = new JSONArray().toString();

	/*** 驗證輸入框：為非空輸入框。 ***/
	public static void validateInput_Text(JTextComponent jtc) throws Exception {
		if (StringUtils.isBlank(jtc.getText())) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_IS_BLANK));
		}
	}

	/*** 驗證輸入框：為非空陣列格式。 ***/
	public static void validateInput_Array(JTextComponent jtc) throws Exception {
		validateInput_Text(jtc);
		try {
			if (new JSONArray(jtc.getText()).isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_ARRAY_IS_EMPTY));
			}
		} catch (JSONException e) {
			throw new JSONException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_FORMAT));
		}
	}

	/*** 驗證輸入框：陣列格式。 ***/
	public static void validateInput_SimpleArray(JTextComponent jtc) throws Exception {
		try {
			new JSONArray(jtc.getText());
		} catch (Exception e) {
			throw new JSONException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_FORMAT));
		}
	}

	/*** 驗證輸入框：為已存在合法檔案路徑格式。 ***/
	public static void validateInput_FilePath(JTextComponent jtc) throws Exception {
		validateInput_Text(jtc);
		String input = jtc.getText();
		File f = new File(input);
		if (!f.exists()) {
			throw new FileNotFoundException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_FILE_NOT_EXIST));
		} else if (!f.isFile()) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_NOT_A_FILE));
		}
		// 預先判斷是否皆可正常讀檔
		boolean isFileNull = true;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), getFileEncoding(input)));) {
			if (isFileNull = (br.readLine() == null)) {
				throw new Exception();
			}
		} catch (Exception e) {
			if (isFileNull) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, "檔案內容是空值"));
			} else {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, "讀檔錯誤"));
			}
		}
	}

	/*** 驗證輸入框：為合法修改日期格式。 ***/
	public static void validateInput_byModifyDate(JXDatePicker jxdpGreaterThan, JXDatePicker jxdpLessThan) throws Exception {
		validateInput_BeinEndDate(jxdpGreaterThan, jxdpLessThan);
	}

	/*** 驗證輸入框：為合法起訖日期格式。 ***/
	public static void validateInput_BeinEndDate(JXDatePicker jxdpGreaterThan, JXDatePicker jxdpLessThan) throws Exception {
		if (jxdpGreaterThan.getDate() != null && jxdpLessThan.getDate() != null) {
			if (jxdpGreaterThan.getDate().after(jxdpLessThan.getDate())) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "起日不可大於起訖日", ""));
			}
		}
	}

	/*** 驗證輸入框：為匯出檔案路徑格式。 ***/
	public static void validateInput_ExportPath(JTextComponent jtc, List<String> extensions) throws Exception {
		try {
			validateInput_FilenameInExtensionList(new JTextField(new File(jtc.getText()).getName()), extensions);
		} catch (Exception e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + jtc.getText(), ERRMSG_ILLEGAL_FILENAME));
		}
	}

	/*** 驗證輸入框：為已存在資料夾格式。 ***/
	public static void validateInput_DirectoryPath(JTextComponent jtc) throws Exception {
		validateInput_Text(jtc);
		String input = jtc.getText();
		File f = new File(input);
		if (!f.exists()) {
			throw new FileNotFoundException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_DIRECTORY_NOT_EXIST));
		} else if (!f.isDirectory()) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_NOT_A_DIRECTORY));
		}
	}

	/*** 驗證輸入框：合法檔名並在副檔名List中。 ***/
	public static void validateInput_FilenameInExtensionList(JTextComponent jtc, List<String> extensions) throws Exception {
		validateInput_Text(jtc);
		String filenameU = jtc.getText().toUpperCase();
		// 比對一般檔名格式
		if (!filenameU.matches(REGEXP_LEGAL_FILEFULLNAME)) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + jtc.getText(), ERRMSG_ILLEGAL_FILENAME));
		}
		// 比對特殊檔名格式
		boolean inExtension = false;
		for (String extension : extensions) {
			if (filenameU.matches(String.format(FORMAT_REGEXP_LEGAL_FILENAME, extension))) {
				inExtension = true;
				break;
			}
		}
		if (!inExtension) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + jtc.getText(), ERRMSG_ILLEGAL_FILENAME_EXTENSION));
		}
	}

	/*** 轉換輸入框：驗證陣列轉字串 ***/
	public static String transInput_Array2String(JTextComponent jtc) throws Exception {
		transInput_SimpleArray2String(jtc);
		validateInput_Array(jtc);
		return new JSONArray(jtc.getText()).toString();
	}

	/*** 轉換輸入框：驗證陣列轉字串 ***/
	public static String transInput_SimpleArray2String(JTextComponent jtc) throws Exception {
		if (StringUtils.isBlank(jtc.getText())) {
			jtc.setText(INIT_JSONARRRAY);
		}
		validateInput_SimpleArray(jtc);
		return new JSONArray(jtc.getText()).toString();
	}

	/*** 匯出檔案 ***/
	public static void exportFile(String exportPath, List<String> contents) throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(exportPath), true), StandardCharsets.UTF_8))) {
			// 檔案頭設定BOM，避免亂碼
			bw.write('\ufeff');

			for (String content : contents) {
				bw.write(content);
			}

			bw.flush();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(ERRMSG_DONOT_WRITE_FILE);
		}
	}

	/*** 匯出檔案 ***/
	// public static void exportFile(String exportPath, List<String> contents)
	// throws Exception {
	// try (FileWriter fw = new FileWriter(new File(exportPath).getAbsolutePath()))
	// {
	// fw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
	// for (String content : contents) {
	// fw.write(content);
	// }
	// fw.flush();
	// } catch (FileNotFoundException e) {
	// throw new FileNotFoundException(e.getMessage());
	// } catch (Exception e) {
	// throw new Exception(ERRMSG_DONOT_WRITE_FILE);
	// }
	// }

	/*** 找編碼 ***/
	public static String getFileEncoding(String filepath) {
		try (FileInputStream fis = new FileInputStream(filepath)) {
			/* 建立分析器 */
			UniversalDetector detector = new UniversalDetector(null);
			int nread;
			byte[] buf = new byte[4096];
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				/* 分析資料 */
				detector.handleData(buf, 0, nread);
			}
			detector.dataEnd();
			if (StringUtils.isBlank(detector.getDetectedCharset())) {
				return System.getProperty("file.encoding");
			} else {
				return detector.getDetectedCharset();
			}
		} catch (Exception e) {
			return System.getProperty("file.encoding");
		}
	}

	/*** Iterator排序 ***/
	public static Iterator<String> sortedIterator(Iterator<String> it) {
		List<String> list = new ArrayList<String>();
		while (it.hasNext()) {
			list.add(it.next());
		}

		Collections.sort(list, null);
		return list.iterator();
	}

	/*** 取得預設匯出檔案目錄 ***/
	public static String getHomeDirectoryFilePath(String fileName, String fileNameExtension) {
		String result = MY_HOME_DIRECTORY + File.separator + FILENAME_RESULT + SIGN_UNDERLINE + now(SDF_YYYYMMDDHHMMSS_1);
		if (StringUtils.isNotBlank(fileName)) {
			result += (SIGN_UNDERLINE + fileName);
		}
		result += StringUtils.trimToEmpty(fileNameExtension);
		return result;
	}

	/*** 雙擊複製到剪貼簿 ***/
	public static void setDbClickForCopy(JTextComponent jtc) {
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
					MY_CLIPBOARD.setContents(data, data);
					showStatus(String.format(FORMAT_MSG_COPIED, jtc.getName()));
				}
			}
		});
	}

	/*** 取電文長度(輸入,輸出) ***/
	public static void getInputTextLength(JTextComponent input, JLabel output) {
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
	public static void getInputIntegerArraySum(List<JTextComponent> inputs, JLabel output) {
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
	protected static String getIntegerArrayLength2String(String str) {
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
	public static int getGBKLen(String str) throws UnsupportedEncodingException {
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("轉GBK長度錯誤");
		}
	}

	/*** 訊息欄Start ***/
	public static void showSatusNoTimer(String msg) {
		showStatus(MSG_SUCCESS, Arrays.asList(msg), false);
	}

	public static void showSatusNoTimer(List<String> list) {
		showStatus(MSG_SUCCESS, list, false);
	}

	public static void showStatus(String msg) {
		showStatus(MSG_SUCCESS, Arrays.asList(msg), true);
	}

	public static void showSatus(List<String> list) {
		showStatus(MSG_SUCCESS, list, true);
	}

	public static void showSatus(Exception e) {
		AppTimer.setTimerWork(false);
		showStatus(e.getClass().getSimpleName(), Arrays.asList(e.getMessage()), true);
	}

	public static void showStatus(String status, List<String> list, boolean isChecktimer) {
		List<String> al = new ArrayList<String>(list);
		if (isChecktimer && AppTimer.isTimerOn) {
			AppTimer.setTimerWork(false);
			al.add(AppTimer.timerString);
		}
		jl_status.setText(status, al);
	}

	/*** 訊息欄End ***/

	public static Object getJsonValue(Object obj, String key) {
		if (!new JSONObject(obj.toString()).isNull(key)) {
			String objClassType = new JSONObject(obj.toString()).get(key).getClass().getSimpleName();

			if ("String".equals(objClassType)) {
				return new JSONObject(obj.toString()).getString(key);
			} else if ("Integer".equals(objClassType)) {
				return new JSONObject(obj.toString()).getInt(key);
			} else if ("JSONArray".equals(objClassType)) {
				return new JSONObject(obj.toString()).getJSONArray(key);
			} else {
				return new JSONObject(obj.toString()).get(key);
			}
		} else {
			return "";
		}
	}

	/*** 樣式：一般(物件,命名,顏色) ***/
	public static void setAppStyle(JComponent jc, String name, Color fontColor) {
		if (StringUtils.isNotBlank(name)) {
			jc.setName(name);
		}
		jc.setFont(APP_FONT);
		jc.setForeground(fontColor);
	}

	/*** 重新生成下拉選單 ***/
	protected static void genPulldownMenu(JComboBox<String> jcb, List<String> items) {
		String selectString = null;
		int selectIndex = jcb.getSelectedIndex();
		// System.out.println("items=" + items);
		if (!items.isEmpty() && jcb.getSelectedItem() != null && selectIndex >= 0) {
			selectString = jcb.getSelectedItem().toString();
		}
		jcb.removeAllItems();
		for (String item : items) {
			jcb.addItem(item);
		}
		if (StringUtils.isNotBlank(selectString) && selectString.equals(items.get(selectIndex))) {
			jcb.setSelectedItem(selectString);
		}
	}

	protected static String getPulldownItem(JComboBox<String> jcb) {
		return jcb.getSelectedItem() != null ? jcb.getSelectedItem().toString() : "";
	}

	public static String now(SimpleDateFormat sdf) {
		if (sdf != null) {
			return sdf.format(System.currentTimeMillis());
		} else {
			return Long.toString(System.currentTimeMillis());
		}
	}

}