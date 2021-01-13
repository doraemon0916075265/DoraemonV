package cti.app.constant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

import cti.app.appService.Style;
import cti.app.component.JLabelStatus;
import cti.app.component.JTabbedPaneSimple;
import cti.app.component.JframeSimple;

public class AppConstant {
	public static final String APP_FRAME_TITLE = "★小工具★";
	public static final JframeSimple jf = new JframeSimple(APP_FRAME_TITLE);
	public static final JLabelStatus jl_status = new JLabelStatus();// 系統訊息
	public static final JProgressBar jpb = new JProgressBar();
	public static final JTabbedPaneSimple jtp = new JTabbedPaneSimple();

	// APP設定
	public static final String APP_GUIDEBOOK_TITLE = "操作說明書";
	public static final int APP_FRAME_WIDTH = Style.FRAME_WIDTH;
	public static final int APP_FRAME_HEIGHT = Style.FRAME_HEIGHT;
	public static final String APP_FRAME_ENCODING = "UTF-8";
	public static final LineBorder APP_COLOR_COMPONENT_BORDER = new LineBorder(new Color(140, 140, 140));
	public static final Color APP_COLOR_LOG = Color.BLUE;
	public static final Color APP_COLOR_SPEC = Color.MAGENTA;
	public static final Color APP_COLOR_DEFAULT = Color.BLACK;
	public static final Color APP_COLOR_SEARCH_CRITERIA = Color.BLUE;
	public static final Color APP_COLOR_MSG = Color.BLUE; // 成功訊息
	public static final Color APP_COLOR_ERRMSG = Color.RED; // 錯誤訊息
	public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS_1 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS_2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat SDF_YYYYMMDD_1 = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat SDF_HHMMSS_1 = new SimpleDateFormat("HH:mm:ss");
	public static final Font APP_FONT = Style.APP_FONT;
	public static final String APP_TAB = "頁籤";
	public static final String APP_MSG = "系統訊息";
	public static final int PROGRESS_MIN = 0;
	public static final int PROGRESS_MAX = 100;
	public static int currentProgress = PROGRESS_MIN;

	/** 剪貼簿 **/
	public static final Clipboard MY_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
	/** 桌面路徑 **/
	public static final String MY_HOME_DIRECTORY = FileSystemView.getFileSystemView().getHomeDirectory().toString();

	// 訊息
	public static final String MSG_ = "";
	public static final String MSG_SUCCESS = "完成";
	public static final String MSG_INITIAL = "歡迎使用本程式！系統訊息顯示區塊～";
	public static final String MSG_READFILE = "已讀檔";
	public static final String MSG_READFILEPATH = "已取得檔案路徑";
	public static final String MSG_GET = "已取得";
	public static final String MSG_CLEARDATA = "已清除";
	public static final String MSG_RESETDATA = "已重設";
	public static final String MSG_ANALYSIS = "已解析";
	public static final String MSG_EXPORTFILE = "已匯出";
	public static final String MSG_SEARCH = "已查詢";
	public static final String MSG_RUNNING = "執行中";
	public static final String MSG_OPEN_GUIDEBOOK = "開啟說明書";
	public static final String MSG_CLOSE_GUIDEBOOK = "關閉說明書";
	public static final String MSG_WELCOME = "歡迎進入";

	// 錯誤訊息
	public static final String ERRMSG_JSON_TRANSFER = "轉換JSON格式發生錯誤";
	public static final String ERRMSG_NUMBERFORMAT = "轉換數字發生錯誤";
	public static final String ERRMSG_FORMAT = "格式錯誤";
	public static final String ERRMSG_LACKVALUE = "缺少";
	public static final String ERRMSG_IS_BLANK = "是空值";
	public static final String ERRMSG_NOT_A_FILE = "不是檔案";
	public static final String ERRMSG_NOT_A_DIRECTORY = "不是資料夾";
	public static final String ERRMSG_DIRECTORY_NOT_EXIST = "資料夾不存在";
	public static final String ERRMSG_ILLEGAL_FILENAME_EXTENSION = "非法副檔名";
	public static final String ERRMSG_FILE_NOT_EXIST = "檔案不存在";
	public static final String ERRMSG_HASBLANK = "有空值";
	public static final String ERRMSG_ARRAY_IS_EMPTY = "是空陣列";
	public static final String ERRMSG_DONOT_WRITE_FILE = "無法寫檔";
	public static final String ERRMSG_ILLEGAL_FILENAME = "非法檔名";

	// 格式
	public static final String FORMAT_MSG_HEADER = "[%s][%s %s]";
	public static final String FORMAT_MSG_BODY = "%s %s %s";
	public static final String FORMAT_MSG = "[%s][%s %s] %s";
	public static final String FORMAT_MSG_TIMER_S = "(執行%.3f秒)";
	public static final String FORMAT_MSG_TIMER_MS = "(執行%d毫秒)";
	public static final String FORMAT_MSG_EXCEPTION = "%s %s";
	public static final String FORMAT_MSG_COPIED = "已複製 %s 至剪貼簿";
	public static final String FORMAT_SPECINFO = "%10s：%s" + System.lineSeparator();
	public static final String FORMAT_GUIDEBOOK_SUBTITLE = "☆ %s ☆" + System.lineSeparator();
	public static final String FORMAT_GUIDEBOOK_CONTENT = "%d . %s " + System.lineSeparator();
	public static final String FORMAT_EXPORTFILE_SUBTITLE = "☆ %s ☆" + System.lineSeparator();
	public static final String FORMAT_EXPORTFILE_CONTENT = "%s" + System.lineSeparator();

	public static final String FORMAT_CSV_CELL = "=\"%s\",";
	public static final String FORMAT_CSV_CELL_FIX = "\"%s\",";
	public static final String FORMAT_CSV_CELLHEADER = "=\"[%s]\",";

	// 按鈕
	public static final String BTN_CLEARDATA = "清除";
	public static final String BTN_CONFIRM = "確定";
	public static final String BTN_RESETDATA = "重設";
	public static final String BTN_READFILE = "讀檔";
	public static final String BTN_ANALYSIS = "解析";
	public static final String BTN_EXPORTFILE = "匯出";
	public static final String BTN_GUIDEBOOK = "說明書";
	public static final String BTN_SEARCH = "查詢";
	public static final String BTN_EXECUTE = "執行";

	// 正規表示法
	public static final String REGEXP_FORALL = ".*";
	public static final String REGEXP_LEGAL_FILEFULLNAME = "[\u4e00-\u9fa5\\w_]+\\.[\\w_]*";
	public static final String FORMAT_REGEXP_LEGAL_FILENAME = "[\u4e00-\u9fa5\\w_]+\\.%s{1}";

	// 符號
	public static final String SIGN_SPACE = " ";
	public static final String SIGN_DBQUOTES = "\"";
	public static final String SIGN_EQUAL = "=";
	public static final String SIGN_EQUAL_DBQUOTES = "=\"";
	public static final String SIGN_DBQUOTES_COMMA = "\",";
	public static final String SIGN_VERTICAL_BAR = "|";
	public static final String SIGN_VERTICAL_BAR01 = " | ";
	public static final String SIGN_COMMA = ",";
	public static final String SIGN_UNDERLINE = "_";

	public static final String STR_ZERO = "0";

	// FileManager專用
	public static final String FM_RESULTTYPE_ALL = "全部";
	public static final String FM_RESULTTYPE_FILE = "檔案";
	public static final String FM_RESULTTYPE_DIR = "資料夾";
	public static final List<String> FM_RESULTTYPE_LIST = Arrays.asList(FM_RESULTTYPE_ALL, FM_RESULTTYPE_FILE, FM_RESULTTYPE_DIR);

	public static final String FM_FILENAME_LOG = "LOG.TXT";
	public static final String FM_FILENAME_SPEC = "SPEC.JSON";

	// 副檔名
	public static final String FILENAME_EXTENSION_CSV = ".csv";

	// spec檔標籤
	protected static final String SPEC = "Spec";
	protected static final String TAG_ID = "id";
	protected static final String TAG_SCUT0 = "s_cut0";
	protected static final String TAG_SCUT = "s_cut";
	protected static final String TAG_FCUT0 = "f_cut0";
	protected static final String TAG_FCUT = "f_cut";
	protected static final String TAG_OCCURS = "occurs";
	protected static final String TAG_NOTE = "note";
	protected static final String TAG_OWNER = "owner";
	protected static final String TAG_CNAME = "cname";
	protected static final String TAG_SCNAME0 = "s_cname0";
	protected static final String TAG_SENAME0 = "s_ename0";
	protected static final String TAG_SCNAME = "s_cname";
	protected static final String TAG_SENAME = "s_ename";
	protected static final String TAG_FCNAME0 = "f_cname0";
	protected static final String TAG_FENAME0 = "f_ename0";
	protected static final String TAG_FCNAME = "f_cname";
	protected static final String TAG_FENAME = "f_ename";
	protected static final String TAG_FORMAT = "format";

	// clearData型態
	protected static final String CLEAR_ALL = "ALL";
}
