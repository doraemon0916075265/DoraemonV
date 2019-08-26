package cti.app.constant;

import cti.app.service.CutterService;

public class CutterConstant extends CutterService {
	public static final String TAB_NAME = "切電文";
	// 標題
	public static final String JL_LOGFILEPATH = "log檔路徑";
	public static final String JL_SPECFILEPATH = "spec檔路徑";
	public static final String JL_LOGINFO_SEND = "上行電文";
	public static final String JL_LOGINFO_FILL = "下行電文";
	public static final String JL_SPECINFO_SEND = "上行電文陣列";
	public static final String JL_SPECINFO_FILL = "下行電文陣列";
	public static final String JL_LOGINFO_ID = "電文ID/資訊";
	public static final String JL_EXPORTFILE = "匯出檔案路徑";

	public static final String NAME_RESULTS = "切上行結果區";
	public static final String NAME_RESULTF = "切下行結果區";

	// 初始化路徑
	public static final String DFT_PATH_LOG = "C:\\Users\\NT83382\\Desktop\\log.txt";
	public static final String DFT_PATH_SPEC = "C:\\Users\\NT83382\\Desktop\\spec.json";
	public static final String FILENAME_LOG = "LOG.TXT";
	public static final String FILENAME_SPEC = "SPEC.JSON";
}
