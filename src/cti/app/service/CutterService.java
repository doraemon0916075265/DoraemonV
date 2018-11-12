package cti.app.service;

import java.io.File;

public class CutterService extends AppService {
	public static final String FILENAME_RESULT = "result.csv";

	/*** 取得預設匯出檔案目錄 ***/
	public String getExportFilePath() {
		return getDesktopRootPath() + File.separator + FILENAME_RESULT;
	}

}
