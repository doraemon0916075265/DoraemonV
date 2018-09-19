package cti.app.constant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.LineBorder;

public class AppConstant {
	// APP設定
	public static final String APP_FRAME_TITLE = "★Hello～★";
	public static final String APP_TAB01_TITLE = "切電文";
	public static final String APP_TAB02_TITLE = "找檔案";
	public static final String APP_TAB03_TITLE = "未定1";
	public static final String APP_TAB04_TITLE = "未定2";
	public static final String[] APP_TABS_TITLE = { APP_TAB01_TITLE, APP_TAB02_TITLE, APP_TAB03_TITLE, APP_TAB04_TITLE };
	public static final String APP_GUIDEBOOK_TITLE = "操作說明書";
	public static final int APP_FRAME_WIDTH = 1080;
	public static final int APP_FRAME_HEIGHT = 720;
	public static final String APP_FRAME_ENCODING = "UTF-8";
	public static final LineBorder APP_COLOR_COMPONENT_BORDER = new LineBorder(new Color(140, 140, 140));
	public static final Color APP_COLOR_LOG = Color.BLUE;
	public static final Color APP_COLOR_SPEC = Color.MAGENTA;
	public static final Color APP_COLOR_DEFAULT = Color.BLACK;
	public static final Color APP_COLOR_MSG = Color.RED;
	public static final SimpleDateFormat APPMSG_SDF = new SimpleDateFormat("HH:mm:ss");
	public static final Font APP_FONT = new Font("微軟正黑體", Font.PLAIN, 12);
	public static final Clipboard APP_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

	public static boolean app_boolean;
	public static String app_String;
	public static long app_long;
	public static int app_int;
	public static Integer app_Integer;
	public static Map<String, String> app_Map_SS = new HashMap<>();
	public static List<String> app_List_S = new ArrayList<>();

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
	public static final String ERRMSG_ISBLANK = "是空值";
	public static final String ERRMSG_HASBLANK = "有空值";
	public static final String ERRMSG_ARRAYISEMPTY = "是空陣列";

	// 格式
	public static final String FORMAT_MSG = "[%s][%s %s] %s %s";
	public static final String FORMAT_MSG_TIMER_S = "(執行%.3f秒)";
	public static final String FORMAT_MSG_TIMER_MS = "(執行%d毫秒)";
	public static final String FORMAT_MSG_TGLEN = "%s | %s";
	public static final String FORMAT_MSG_EXCEPTION = "%s %s";
	public static final String FORMAT_MSG_COPIED = "已複製 %s 至剪貼簿";
	public static final String FORMAT_GUIDEBOOK_SUBTITLE = "☆ %s ☆" + System.lineSeparator();
	public static final String FORMAT_GUIDEBOOK_CONTENT = "%d . %s " + System.lineSeparator();
	public static final String FORMAT_EXPORTFILE_SUBTITLE = "  ☆ %s ☆" + System.lineSeparator();
	public static final String FORMAT_EXPORTFILE_CONTENT = "%s" + System.lineSeparator();

	// 檔案路徑
	public static final String PATH_DISK_C = "C:" + File.separator;
	public static final String DIRECTORYNAME_USERS = "USERS";
	public static final String DIRECTORYNAME_DESKTOP = "DESKTOP";
	public static final String DIRECTORYNAME_REGEXP_NT = "NT[0-9](.*)";
	public static final String KEY_TARGET_PATH = "key_target_path";

	// 控制位置
	public static final int SIZE_HOR_LABEL1 = 80;// 標題項目長度
	public static final int SIZE_HOR_COL1 = 20;// 第一欄位置
	public static final int SIZE_HOR_COL2 = SIZE_HOR_COL1 + SIZE_HOR_LABEL1;// 第二欄位置
	public static final int SIZE_HOR_SPACE = 5;// 空格
	public static final int SIZE_HOR_TEXT1 = 760;// 輸入框(大)
	public static final int SIZE_HOR_TEXT2 = 180;// 輸入框(電文頭)
	public static final int SIZE_HOR_RESULT = 1030;// 結果區
	public static final int SIZE_HOR_COL3 = SIZE_HOR_COL2 + SIZE_HOR_TEXT2 + SIZE_HOR_SPACE;// 第三欄位置
	public static final int SIZE_HOR_COL4 = SIZE_HOR_COL2 + SIZE_HOR_TEXT1;// 第四欄位置
	public static final int SIZE_HOR_TEXT3 = SIZE_HOR_COL4 - SIZE_HOR_COL3;// 輸入框(電文主要)
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
}
