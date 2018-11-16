package cti.app.constant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class AppConstant {
	public static JLabel jl_msg = new JLabel();// 系統訊息
	public static JTabbedPane jtp = new JTabbedPane();

	// APP設定
	public static final String APP_FRAME_TITLE = "★Hello～★";
	public final static String[] APP_TAB_NAME = { "切電文", "找檔案", "未定1", "未定2", "未定3" };
	public static final String APP_GUIDEBOOK_TITLE = "操作說明書";
	public static final int APP_FRAME_WIDTH = 1080;
	public static final int APP_FRAME_HEIGHT = 720;
	public static final String APP_FRAME_ENCODING = "UTF-8";
	public static final LineBorder APP_COLOR_COMPONENT_BORDER = new LineBorder(new Color(140, 140, 140));
	public static final Color APP_COLOR_LOG = Color.BLUE;
	public static final Color APP_COLOR_SPEC = Color.MAGENTA;
	public static final Color APP_COLOR_DEFAULT = Color.BLACK;
	public static final Color APP_COLOR_SEARCH_CRITERIA = Color.BLUE;
	public static final Color APP_COLOR_MSG = Color.RED;
	public static final SimpleDateFormat APPMSG_SDF = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat APPDATE_SDF = new SimpleDateFormat("yyyy/MM/dd");
	public static final Font APP_FONT = new Font("微軟正黑體", Font.PLAIN, 12);
	public static final Clipboard APP_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

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

	// 格式
	public static final String FORMAT_MSG = "[%s][%s %s] %s %s";
	public static final String FORMAT_MSG_TIMER_S = "(執行%.3f秒)";
	public static final String FORMAT_MSG_TIMER_MS = "(執行%d毫秒)";
	public static final String FORMAT_MSG_EXCEPTION = "%s %s";
	public static final String FORMAT_MSG_COPIED = "已複製 %s 至剪貼簿";
	public static final String FORMAT_GUIDEBOOK_SUBTITLE = "☆ %s ☆" + System.lineSeparator();
	public static final String FORMAT_GUIDEBOOK_CONTENT = "%d . %s " + System.lineSeparator();
	public static final String FORMAT_EXPORTFILE_SUBTITLE = "☆ %s ☆" + System.lineSeparator();
	public static final String FORMAT_EXPORTFILE_CONTENT = "%s" + System.lineSeparator();

	// 檔案路徑
	public static final String PATH_DISK_C = "C:" + File.separator;

	public static final List<String> DIRNAME_LIST01 = Arrays.asList("USERS");
	public static final List<String> DIRNAME_LIST02 = Arrays.asList("CATHAY");
	public static final List<String> DIRNAME_LIST03 = Arrays.asList("DESKTOP");

	public static final String DIRNAME_EXP02 = "NT[\\d]+";

	// 控制位置
	public static final int SIZE_HOR_LABEL1 = 80;// 標題項目長度
	public static final int SIZE_HOR_COL1 = 20;// 第一欄位置
	public static final int SIZE_HOR_COL2 = SIZE_HOR_COL1 + SIZE_HOR_LABEL1;// 第二欄位置
	public static final int SIZE_HOR_SPACE = 5;// 空格
	public static final int SIZE_HOR_TEXT1 = 760;// 輸入框(大)
	public static final int SIZE_HOR_TEXT2 = 180;// 輸入框(電文頭)
	public static final int SIZE_HOR_CALENDAR1 = 250;// 日期框
	public static final int SIZE_HOR_RESULT = 1030;// 結果區
	public static final int SIZE_HOR_COL3 = SIZE_HOR_COL2 + SIZE_HOR_TEXT2 + SIZE_HOR_SPACE;// 第三欄位置
	public static final int SIZE_HOR_COL4 = SIZE_HOR_COL2 + SIZE_HOR_TEXT1;// 第四欄位置
	public static final int SIZE_HOR_COL4_MSG = SIZE_HOR_COL4 + 5;
	public static final int SIZE_HOR_COLMID = (SIZE_HOR_COL1 + SIZE_HOR_LABEL1 + SIZE_HOR_TEXT1) / 2;
	public static final int SIZE_HOR_TEXT3 = SIZE_HOR_COL4 - SIZE_HOR_COL3;// 輸入框(電文主要)
	public static final int SIZE_HOR_TEXT5 = 300;
	public static final int SIZE_HOR_COL8 = 960;// 按鈕欄位置
	public static final int SIZE_HOR_COL7 = SIZE_HOR_COL8 - SIZE_HOR_COL4 - SIZE_HOR_SPACE;// 顯示電文長度位置
	public static final int SIZE_HOR_BTN = 90;// 按鈕長
	public static final int SIZE_HOR_BTNF = 15;// 檔案按鈕長
	public static final int SIZE_VER_INPUT = 25;
	public static final int SIZE_VER_SEND = 70;
	public static final int SIZE_VER_FILL = 205;

	// 按鈕
	public static final String BTN_CLEARDATA = "清除";
	public static final String BTN_RESETDATA = "重設";
	public static final String BTN_READFILE = "讀檔";
	public static final String BTN_ANALYSIS = "解析";
	public static final String BTN_EXPORTFILE = "匯出";
	public static final String BTN_GUIDEBOOK = "說明書";
	public static final String BTN_SEARCH = "查詢";

	// 正規表示法
	public static final String REGEXP_FORALL = ".*";

	// 符號
	public static final String SIGN_DBQUOTES = "\"";
	public static final String SIGN_EQUAL = "=";
	public static final String SIGN_EQUAL_DBQUOTES = "=\"";
	public static final String SIGN_DBQUOTES_COMMA = "\",";
	public static final String SIGN_VERTICAL_BAR = "|";
	public static final String SIGN_VERTICAL_BAR01 = " | ";
	public static final String SIGN_COMMA = ",";

	public static final String STR_ZERO = "0";
}
