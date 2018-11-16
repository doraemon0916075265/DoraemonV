package cti.app.service;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
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
import org.json.JSONException;
import org.mozilla.universalchardet.UniversalDetector;

import cti.app.constant.AppConstant;

public class AppService extends AppConstant {
	private static long now;
	private static boolean isTimerOn;
	private static long timer;
	private static String desktopRootPath;
	private static String filePathByRootPath;

	/*** 取得桌面根目錄 ***/
	public String getDesktopRootPath() {
		desktopRootPath = "";
		findRootPath(PATH_DISK_C, false, false);
		return StringUtils.isNotBlank(desktopRootPath) ? desktopRootPath : PATH_DISK_C;
	}

	/*** 遞迴：取得桌面根目錄錄 ***/
	private static void findRootPath(String filePath, boolean isDir1, boolean isDir2) {
		File file = new File(filePath);
		try {
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					String fileNameU = fileName.toUpperCase();
					String newFilePath = file.getAbsolutePath() + File.separator + fileName;
					if (!isDir1 && DIRNAME_LIST01.contains(fileNameU)) {
						findRootPath(newFilePath, true, false);
					}
					if (isDir1 && !isDir2 && (fileNameU.matches(DIRNAME_EXP02) || DIRNAME_LIST02.contains(fileNameU))) {
						findRootPath(newFilePath, true, true);
					}
					if (isDir1 && isDir2 && DIRNAME_LIST03.contains(fileNameU)) {
						desktopRootPath = newFilePath;
					}
				}
			}
		} catch (Exception e) {

		}
	}

	/*** 找目錄下的檔案路徑 ***/
	public String findFilePathByRootPath(String path, String name) {
		filePathByRootPath = "";
		recursive4FilePathByRootPath(path, name);
		return StringUtils.isBlank(filePathByRootPath) ? path : filePathByRootPath;
	}

	/*** 遞迴：找目錄下的檔案路徑 ***/
	private static void recursive4FilePathByRootPath(String path, String name) {
		try {
			File file = new File(path);
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					recursive4FilePathByRootPath(path + File.separator + fileName, name);
				}
			} else {
				String fileNameU = file.getName().toUpperCase();
				if (name.equals(fileNameU)) {
					filePathByRootPath = path;
				}
			}
		} catch (Exception e) {

		}
	}

	/*** 驗證輸入框：為非空輸入框。 ***/
	public void validateInput_Text(JTextComponent jtc) throws Exception {
		if (StringUtils.isBlank(jtc.getText())) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_IS_BLANK));
		}
	}

	/*** 驗證輸入框：為非空陣列格式。 ***/
	public void validateInput_Array(JTextComponent jtc) throws Exception {
		validateInput_Text(jtc);
		try {
			if (new JSONArray(jtc.getText()).isEmpty()) {
				throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_ARRAY_IS_EMPTY));
			}
		} catch (JSONException e) {
			throw new JSONException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName(), ERRMSG_FORMAT));
		}
	}

	/*** 驗證輸入框：為合法檔案路徑格式。 ***/
	public void validateInput_Filepath(JTextComponent jtc) throws Exception {
		validateInput_Text(jtc);
		String input = jtc.getText();
		File f = new File(input);
		if (!f.exists()) {
			throw new FileNotFoundException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_FILE_NOT_EXIST));
		} else if (!f.isFile()) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_NOT_A_FILE));
		}
		// 預先判斷是否皆可正常讀檔
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), getFileEncoding(input)));) {

		} catch (Exception e) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, "讀檔錯誤"));
		}
	}

	/*** 驗證輸入框：為合法資料夾格式+合法檔名+合法副檔名。 ***/
	public void validateInput_Directory(JTextComponent jtc, List<String> extensions) throws Exception {
		validateInput_DirectoryPath(new JTextField(new File(jtc.getText()).getParent()));// 上一層是否為資料夾
		validateInput_FilenameInExtensionList(jtc, extensions);
	}

	/*** 驗證輸入框：為合法資料夾格式。 ***/
	public void validateInput_DirectoryPath(JTextComponent jtc) throws Exception {
		validateInput_Text(jtc);
		String input = jtc.getText();
		File f = new File(input);
		if (!f.exists()) {
			throw new FileNotFoundException(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_DIRECTORY_NOT_EXIST));
		} else if (!f.isDirectory()) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, jtc.getName() + input, ERRMSG_NOT_A_DIRECTORY));
		}
	}

	/*** 判斷輸入框：是否檔名在副檔名List中。 ***/
	public void validateInput_FilenameInExtensionList(JTextComponent jtc, List<String> extensions) throws Exception {
		validateInput_Text(jtc);
		String filenameU = jtc.getText().toUpperCase();
		// 未完成
	}

	/*** 轉換輸入框：驗證陣列轉字串 ***/
	public String transInput_Array2String(JTextComponent jtc) throws Exception {
		validateInput_Array(jtc);
		return new JSONArray(jtc.getText()).toString();
	}

	/*** 匯出檔案 ***/
	public void exportFile(String exportPath, List<String> contents) throws Exception {
		try (FileWriter fw = new FileWriter(new File(exportPath).getAbsolutePath())) {
			fw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			for (String content : contents) {
				fw.write(content);
			}
			fw.flush();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(ERRMSG_DONOT_WRITE_FILE);
		}
	}

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
