package cti.app.constant;

import java.util.HashMap;
import java.util.Map;

import cti.app.main.AppFrameMain;

public class CutterConstant extends AppFrameMain {
	// 標題
	public static final String JL_LOGFILEPATH = "log檔路徑";
	public static final String JL_SPECFILEPATH = "spec檔路徑";
	public static final String JL_LOGINFO_SEND = "上行電文";
	public static final String JL_LOGINFO_FILL = "下行電文";
	public static final String JL_SPECINFO_SEND = "上行電文陣列";
	public static final String JL_SPECINFO_FILL = "下行電文陣列";
	public static final String JL_LOGINFO_ID = "電文ID/資訊";
	public static final String JL_EXPORTFILE = "匯出檔案路徑";

	/*** key - value(start) ***/
	public static final String KEY_ROOTPATH = "rootPath";// log檔路徑

	public static final String KEY_JL_LOGFILEPATH = "jl_logFilePath";// log檔路徑
	public static final String KEY_LOGFILEPATH = "logFilePath";
	public static final String KEY_JB_LOGFILEPATH = "jb_logFilepath";
	public static final String KEY_JB_RESETDATA = "jb_resetData";

	public static final String KEY_JL_SPECFILEPATH = "jl_specFilePath";// spec檔路徑
	public static final String KEY_SPECFILEPATH = "specFilePath";
	public static final String KEY_JB_SPECFILEPATH = "jb_specFilepath";
	public static final String KEY_JB_CLEARDATA = "jb_clearData";

	public static final String KEY_JL_LOGINFO_SEND = "jl_logInfo_send";// 上行電文
	public static final String KEY_SEND = "<Key>Send";// jtf_logInfo_send
	public static final String KEY_JL_LOGINFO_SENDLEN = "jl_logInfo_sendLen";
	public static final String KEY_JB_READFILE = "jb_readFile";

	public static final String KEY_JL_LOGINFO_FILL = "jl_logInfo_fill";// 下行電文
	public static final String KEY_FILL = "<Key>Fill";// jtf_logInfo_fill
	public static final String KEY_JL_LOGINFO_FILLLEN = "jl_logInfo_fillLen";
	public static final String KEY_JB_ANALYSIS = "jb_analysis";

	public static final String KEY_JL_SPECINFO_SEND = "jl_logInfo_fill";// 上行電文陣列
	public static final String KEY_SCUT0 = "s_cut0";// jtf_specSendCut0
	public static final String KEY_SCUT = "s_cut";// jtf_specSendCut
	public static final String KEY_JL_SPECINFO_SENDLEN = "jl_specInfo_sendLen";

	public static final String KEY_JL_SPECINFO_FILL = "jl_specInfo_fill";// 下行電文陣列
	public static final String KEY_FCUT0 = "f_cut0";// jtf_specFillCut0
	public static final String KEY_FCUT = "f_cut";// jtf_specFillCut
	public static final String KEY_JL_SPECINFO_FILLLEN = "jl_specInfo_fillLen";

	public static final String KEY_JL_LOGINFO_ID = "jl_logInfo_ID";// 電文ID/資訊
	public static final String KEY_ID = "id";// jtf_logInfo_ID
	public static final String KEY_NOTE = "note";// jtf_specInfo_note
	public static final String KEY_JB_GUIDEBOOK = "jb_guideBook";

	public static final String KEY_JL_EXPORTFILE = "jl_exportFile";
	public static final String KEY_EXPORTFILEPATH = "jtf_exportFile";
	public static final String KEY_JB_EXPORTFILE = "jb_exportFile";

	public static final String KEY_RESULTS = "jta_resultS";
	public static final String KEY_RESULTF = "jta_resultF";

	public static final String KEY_SNAME = "s_name";
	public static final String KEY_FNAME = "f_name";

	public static final String T_TITLE = "<標題>";
	public static final String T_BUTTON = "<按鈕>";
	public static final String T_LENGTH = "<長度>";
	//////////////////////////////////////////////////////////////////////
	public static final String VALUE_JL_LOGFILEPATH = T_TITLE + JL_LOGFILEPATH;
	public static final String VALUE_LOGFILEPATH = "log檔路徑";
	public static final String VALUE_JB_LOGFILEPATH = T_BUTTON + "找log檔案";
	public static final String VALUE_JB_RESETDATA = T_BUTTON + BTN_RESETDATA;

	public static final String VALUE_JL_SPECFILEPATH = T_TITLE + JL_SPECFILEPATH;// spec檔路徑
	public static final String VALUE_SPECFILEPATH = "spec檔路徑";
	public static final String VALUE_JB_SPECFILEPATH = T_BUTTON + "找spec檔案";
	public static final String VALUE_JB_CLEARDATA = T_BUTTON + BTN_CLEARDATA;

	public static final String VALUE_JL_LOGINFO_SEND = T_TITLE + JL_LOGINFO_SEND;// 上行電文
	public static final String VALUE_SEND = "上行電文";
	public static final String VALUE_JL_LOGINFO_SENDLEN = T_LENGTH + JL_LOGINFO_SEND;
	public static final String VALUE_JB_READFILE = T_BUTTON + BTN_READFILE;

	public static final String VALUE_JL_LOGINFO_FILL = T_TITLE + JL_LOGINFO_FILL;// 下行電文
	public static final String VALUE_FILL = "下行電文";
	public static final String VALUE_JL_LOGINFO_FILLLEN = T_LENGTH + JL_LOGINFO_FILL;
	public static final String VALUE_JB_ANALYSIS = T_BUTTON + BTN_ANALYSIS;

	public static final String VALUE_JL_SPECINFO_SEND = T_TITLE + JL_SPECINFO_SEND;// 上行電文陣列
	public static final String VALUE_SCUT0 = "上行電文陣列(頭)";
	public static final String VALUE_SCUT = "上行電文陣列(主要)";
	public static final String VALUE_JL_SPECINFO_SENDLEN = T_LENGTH + JL_SPECINFO_SEND;

	public static final String VALUE_JL_SPECINFO_FILL = T_TITLE + JL_SPECINFO_FILL;// 下行電文陣列
	public static final String VALUE_FCUT0 = "下行電文陣列(頭)";
	public static final String VALUE_FCUT = "下行電文陣列(主要)";
	public static final String VALUE_JL_SPECINFO_FILLLEN = T_LENGTH + JL_SPECINFO_FILL;

	public static final String VALUE_JL_LOGINFO_ID = T_TITLE + JL_LOGINFO_ID;// 電文ID/資訊
	public static final String VALUE_ID = "電文ID";
	public static final String VALUE_NOTE = "備註";
	public static final String VALUE_JB_GUIDEBOOK = T_BUTTON + BTN_GUIDEBOOK;

	public static final String VALUE_JL_EXPORTFILE = T_TITLE + BTN_EXPORTFILE;
	public static final String VALUE_EXPORTFILEPATH = "匯出檔案路徑";
	public static final String VALUE_JB_EXPORTFILE = T_BUTTON + BTN_EXPORTFILE;

	public static final String VALUE_RESULTS = "切上行電文結果";
	public static final String VALUE_RESULTF = "切下行電文結果";

	public static final String VALUE_SNAME = "s_name";
	public static final String VALUE_FNAME = "f_name";
	/*** key - value(end) ***/

	// 正規表示法
	public static final String PARAM_TGID = "電文ID:";
	public static final String REGEXP_FORALL = "(.*)";
	public static final String REGEXP_TGID = "[^0-9a-zA-Z]";
	public static final String REGEXP_ID1 = "畫面名稱:" + REGEXP_FORALL;
	public static final String REGEXP_ID2 = PARAM_TGID + REGEXP_FORALL;
	public static final String REGEXP_SEND1 = "送出:" + REGEXP_FORALL;
	public static final String REGEXP_SEND2 = REGEXP_FORALL + "送出";
	public static final String REGEXP_FILL1 = "收到:" + REGEXP_FORALL;
	public static final String REGEXP_FILL2 = REGEXP_FORALL + "收到";
	public static final String REGEXP_FILEEXTEN_EXPORT[] = { "(.*).TXT", "(.*).LOG" };

	// 初始化路徑
	public static final String DFT_PATH_LOG = "C:\\Users\\NT83382\\Desktop\\log.txt";
	public static final String DFT_PATH_SPEC = "C:\\Users\\NT83382\\Desktop\\spec.json";
	public static final String FILENAME_LOG = "LOG.TXT";
	public static final String FILENAME_SPEC = "SPEC.JSON";
	public static final String FILENAME_RESULT = "result.txt";

	public static final String SIGN_DOUBLEQUOTES = "\"";
	public static final String LEN_0 = "0";

	/*** 說明書 ***/
	public static StringBuffer getGuidebookContent() {
		StringBuffer sb_guideBook = new StringBuffer();
		int i = 1;
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_SUBTITLE, "操作說明"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "預設抓取桌面路徑下，檔名為log.txt和spec.json檔"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "輸入路徑後，可以讀檔"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "讀檔後，可以解析"));
		sb_guideBook.append(System.lineSeparator());
		i = 1;
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_SUBTITLE, "功能說明"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "重設：等同於重新啟動"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "清除：清除''檔案內容區''欄位"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "讀檔：讀取''路徑欄''的檔案，顯示於''檔案內容區''"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "解析：依''檔案內容區''的資訊，分析電文，顯示於下方''結果區''"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "匯出：將下方''結果區''的資訊，按路徑匯出txt"));
		sb_guideBook.append(System.lineSeparator());
		i = 1;
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_SUBTITLE, "其他操作說明"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "藍色字型的欄位與log檔有關"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "洋紅色字型的欄位與spec檔有關"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "點兩下輸入框，可以複製內容"));
		sb_guideBook.append(String.format(FORMAT_GUIDEBOOK_CONTENT, i++, "最底部是''系統訊息顯示區''：[時間 主要訊息] 詳細訊息"));
		return sb_guideBook;
	}

	public static Map<String, String> getKeyValueMap() {
		Map<String, String> m = new HashMap<>();
		m.put(KEY_JL_LOGFILEPATH, VALUE_JL_LOGFILEPATH);
		m.put(KEY_LOGFILEPATH, VALUE_LOGFILEPATH);
		m.put(KEY_JB_LOGFILEPATH, VALUE_JB_LOGFILEPATH);
		m.put(KEY_JB_RESETDATA, VALUE_JB_RESETDATA);
		m.put(KEY_JL_SPECFILEPATH, VALUE_JL_SPECFILEPATH);
		m.put(KEY_SPECFILEPATH, VALUE_SPECFILEPATH);
		m.put(KEY_JB_SPECFILEPATH, VALUE_JB_SPECFILEPATH);
		m.put(KEY_JB_CLEARDATA, VALUE_JB_CLEARDATA);
		m.put(KEY_JL_LOGINFO_SEND, VALUE_JL_LOGINFO_SEND);
		m.put(KEY_SEND, VALUE_SEND);
		m.put(KEY_JL_LOGINFO_SENDLEN, VALUE_JL_LOGINFO_SENDLEN);
		m.put(KEY_JB_READFILE, VALUE_JB_READFILE);
		m.put(KEY_JL_LOGINFO_FILL, VALUE_JL_LOGINFO_FILL);
		m.put(KEY_FILL, VALUE_FILL);
		m.put(KEY_JL_LOGINFO_FILLLEN, VALUE_JL_LOGINFO_FILLLEN);
		m.put(KEY_JB_ANALYSIS, VALUE_JB_ANALYSIS);
		m.put(KEY_JL_SPECINFO_SEND, VALUE_JL_SPECINFO_SEND);
		m.put(KEY_SCUT0, VALUE_SCUT0);
		m.put(KEY_SCUT, VALUE_SCUT);
		m.put(KEY_JL_SPECINFO_SENDLEN, VALUE_JL_SPECINFO_SENDLEN);
		m.put(KEY_JL_SPECINFO_FILL, VALUE_JL_SPECINFO_FILL);
		m.put(KEY_FCUT0, VALUE_FCUT0);
		m.put(KEY_FCUT, VALUE_FCUT);
		m.put(KEY_JL_SPECINFO_FILLLEN, VALUE_JL_SPECINFO_FILLLEN);
		m.put(KEY_JL_LOGINFO_ID, VALUE_JL_LOGINFO_ID);
		m.put(KEY_ID, VALUE_ID);
		m.put(KEY_NOTE, VALUE_NOTE);
		m.put(KEY_JL_EXPORTFILE, VALUE_JL_EXPORTFILE);
		m.put(KEY_EXPORTFILEPATH, VALUE_EXPORTFILEPATH);
		m.put(KEY_JB_EXPORTFILE, VALUE_JB_EXPORTFILE);
		m.put(KEY_JB_GUIDEBOOK, VALUE_JB_GUIDEBOOK);
		m.put(KEY_RESULTS, VALUE_RESULTS);
		m.put(KEY_RESULTF, VALUE_RESULTF);
		m.put(KEY_SNAME, VALUE_SNAME);
		m.put(KEY_FNAME, VALUE_FNAME);
		return m;
	}
}
