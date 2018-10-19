package cti.app.constant;

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
	public static final String JCB_ISADDEQUAL_1 = "一般格式";
	public static final String JCB_ISADDEQUAL_2 = "csv格式";

	public static final String SPEC_ID = "id";
	public static final String SPEC_SCUT0 = "s_cut0";
	public static final String SPEC_SCUT = "s_cut";
	public static final String SPEC_FCUT0 = "f_cut0";
	public static final String SPEC_FCUT = "f_cut";
	public static final String SPEC_FNAME0 = "f_name0";
	public static final String SPEC_FNAME = "f_name";
	public static final String SPEC_NOTE = "note";

	public static final String NAME_LOGFILEPATH = "log檔路徑";
	public static final String NAME_SPECFILEPATH = "spec檔路徑";
	public static final String NAME_LOGINFO_SEND = "log檔的上行電文";
	public static final String NAME_LOGINFO_FILL = "log檔的下行電文";
	public static final String NAME_SPECSENDCUT0 = "spec檔的上行電文(頭)";
	public static final String NAME_SPECSENDCUT = "spec檔的上行電文(身)";
	public static final String NAME_SPECFILLCUT0 = "spec檔的下行電文(頭)";
	public static final String NAME_SPECFILLCUT = "spec檔的下行電文(身)";
	public static final String NAME_LOGINFO_ID = "電文ID";
	public static final String NAME_SPECINFO_NOTE = "電文資訊";
	public static final String NAME_EXPORTFILEPATH = "匯出檔案路徑";
	public static final String NAME_RESULTS = "切上行結果區";
	public static final String NAME_RESULTF = "切下行結果區";

	// 正規表示法
	public static final String PARAM_TGID = "電文ID:";
	public static final String REGEXP_TGID = "[^0-9a-zA-Z]";
	public static final String REGEXP_ID1 = "畫面名稱:" + REGEXP_FORALL;
	public static final String REGEXP_ID2 = PARAM_TGID + REGEXP_FORALL;
	public static final String REGEXP_SEND1 = "送出:" + REGEXP_FORALL;
	public static final String REGEXP_SEND2 = REGEXP_FORALL + "送出";
	public static final String REGEXP_FILL1 = "收到:" + REGEXP_FORALL;
	public static final String REGEXP_FILL2 = REGEXP_FORALL + "收到";
	public static final String REGEXP_FILEEXTEN_EXPORT[] = { REGEXP_FORALL + ".TXT", REGEXP_FORALL + ".LOG", REGEXP_FORALL + ".CSV" };

	// 初始化路徑
	public static final String DFT_PATH_LOG = "C:\\Users\\NT83382\\Desktop\\log.txt";
	public static final String DFT_PATH_SPEC = "C:\\Users\\NT83382\\Desktop\\spec.json";
	public static final String FILENAME_LOG = "LOG.TXT";
	public static final String FILENAME_SPEC = "SPEC.JSON";
	public static final String FILENAME_RESULT = "result.csv";

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

}
