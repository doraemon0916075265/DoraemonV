package cti.app.handler;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;

import cti.app.constant.AppConstant;

public class AppHandler extends AppConstant {

	public static String desktopRootPath;

	/*** 取得桌面根目錄 ***/
	public static String getDesktopRootPath() {
		desktopRootPath = "";
		findRootPath(PATH_DISK_C, false, false);
		return StringUtils.isNotBlank(desktopRootPath) ? desktopRootPath : PATH_DISK_C;
	}

	/*** 找桌面根目錄 ***/
	private static void findRootPath(String filePath, boolean isDir1, boolean isDir2) {
		File file = new File(filePath);
		try {
			if (file.isDirectory() && StringUtils.isNotBlank(desktopRootPath)) {
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

	/*** Date日期加減 ***/
	public static Date getDateCalculator(Date date, int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, day);
		return c.getTime();
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

	public static String getFuzzySearchRegexpString(String text, boolean isWholeWordSraech) {
		String result = "";
		if (isWholeWordSraech) {// 整個字組搜尋
			result = getExchangeSpecialCharacter4Regexp(text);
		} else {// 模糊搜尋
			for (int i = 0; i < text.length(); i++) {
				result += "[" + getExchangeSpecialCharacter4Regexp(text.substring(i, i + 1)) + "]";
				result += (i == (text.length() - 1) ? "" : REGEXP_FORALL);
			}
		}
		result = REGEXP_FORALL + result + REGEXP_FORALL;
		// System.out.println("正規表示法>" + result);
		return result;
	}

	public static String getExchangeSpecialCharacter4Regexp(String input) {
		input = input.replaceAll("\\\\", "\\\\\\\\");
		input = input.replaceAll("\\^", "\\\\\\^");
		input = input.replaceAll("\\$", "\\\\\\$");
		input = input.replaceAll("\\*", "\\\\\\*");
		input = input.replaceAll("\\+", "\\\\\\+");
		input = input.replaceAll("\\?", "\\\\\\?");
		input = input.replaceAll("\\{", "\\\\\\{");
		input = input.replaceAll("\\}", "\\\\\\}");
		input = input.replaceAll("\\.", "\\\\\\.");
		input = input.replaceAll("\\(", "\\\\\\(");
		input = input.replaceAll("\\)", "\\\\\\)");
		input = input.replaceAll("\\|", "\\\\\\|");
		input = input.replaceAll("\\[", "\\\\\\[");
		input = input.replaceAll("\\]", "\\\\\\]");
		return input;
	}

}
