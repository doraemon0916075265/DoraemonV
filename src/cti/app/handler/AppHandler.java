package cti.app.handler;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;

import cti.app.constant.AppConstant;

public class AppHandler extends AppConstant {

	/*** 找桌面根目錄 ***/
	public static String getDesktopRootPath() {
		Map<String, String> m = new HashMap<>();
		findRootPath(m, PATH_DISK_C, false, false);
		if (StringUtils.isNotBlank(m.get(KEY_TARGET_PATH))) {
			return m.get(KEY_TARGET_PATH);
		} else {
			return PATH_DISK_C;
		}
	}

	/*** 找桌面根目錄 ***/
	private static void findRootPath(Map<String, String> m, String filePath, boolean isDir1, boolean isDir2) {
		File file = new File(filePath);
		try {
			if (file.isDirectory()) {
				for (String fileName : file.list()) {
					String fileNameU = fileName.toUpperCase();
					String newFilePath = file.getAbsolutePath() + File.separator + fileName;
					if (!isDir1 && DIRECTORYNAME_USERS.equals(fileNameU)) {
						findRootPath(m, newFilePath, true, false);
					}
					if (isDir1 && !isDir2 && fileNameU.matches(DIRECTORYNAME_REGEXP_NT)) {
						findRootPath(m, newFilePath, true, true);
					}
					if (isDir1 && isDir2 && DIRECTORYNAME_DESKTOP.equals(fileNameU)) {
						m.put(KEY_TARGET_PATH, newFilePath);
					}
				}
			}
		} catch (Exception e) {

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

}
