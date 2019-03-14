package cti.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import cti.app.bean.CutterBean;
import cti.app.bean.SpecBean;

public class CutterService extends AppService {
	// 標誌
	private static final String FLAG_LOG = "LOG";
	private static final String FLAG_SPEC = "SPEC";

	// 正規表示法
	private static final String PARAM_TGID = "電文ID:";
	private static final String REGEXP_ID1 = "畫面名稱:" + REGEXP_FORALL;
	private static final String REGEXP_ID2 = PARAM_TGID + REGEXP_FORALL;
	private static final String REGEXP_SEND1 = "送出:" + REGEXP_FORALL;
	private static final String REGEXP_SEND2 = REGEXP_FORALL + "送出";
	private static final String REGEXP_FILL1 = "收到:" + REGEXP_FORALL;
	private static final String REGEXP_FILL2 = REGEXP_FORALL + "收到";

	private static final String FILENAME_RESULT = "result";

	/*** 取得預設匯出檔案目錄 ***/
	public static String getExportFilePath(String name) {
		return getDesktopRootPath() + File.separator + FILENAME_RESULT + (StringUtils.isBlank(name) ? "" : "_" + name)
				+ FILENAME_EXTENSION_CSV;
	}

	/*** 讀檔 ***/
	public static CutterBean readFileInfo(CutterBean cb) throws Exception {
		String pathLog = cb.getLogFilePath();
		String pathSpec = cb.getSpecFilePath();
		/*** 讀log檔 ***/
		try (BufferedReader brLog = new BufferedReader(
				new InputStreamReader(new FileInputStream(pathLog), getFileEncoding(pathLog)));) {
			String line;
			boolean isSend = false;
			boolean isFill = false;
			while ((line = brLog.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				String lineU = line.toUpperCase();
				if (lineU.matches(REGEXP_ID1)) {
					cb.setLogInfo_ID(Pattern.compile("[\\W]").matcher(lineU).replaceAll(""));
				} else if (lineU.matches(REGEXP_ID2)) {
					cb.setLogInfo_ID(lineU.replace(PARAM_TGID, ""));
				}
				if (isSend) {
					cb.setLogInfo_send(lineU);
				}
				if (isFill) {
					cb.setLogInfo_fill(lineU);
				}
				isSend = false;
				isFill = false;
				if (lineU.matches(REGEXP_SEND1) || lineU.matches(REGEXP_SEND2)) {
					isSend = true;
				}
				if (lineU.matches(REGEXP_FILL1) || lineU.matches(REGEXP_FILL2)) {
					isFill = true;
				}
			}

			validFileContent(cb, FLAG_LOG);
		}

		/*** 讀spec檔 ***/
		SpecBean spec = SpecService.readFileInfoByID(pathSpec, cb.getLogInfo_ID());
		if (StringUtils.isBlank(spec.getId())) {
			throw new Exception(String.format(FORMAT_MSG_EXCEPTION, "Spec檔無id對應", "log檔id" + cb.getLogInfo_ID()));
		} else {
			cb.setSpecSendCut0(spec.getS_cut0());
			cb.setSpecSendCut(spec.getS_cut());
			cb.setSpecFillCut0(spec.getF_cut0());
			cb.setSpecFillCut(spec.getF_cut());
			validFileContent(cb, FLAG_SPEC);
			cb.setHidden_scname0(spec.getS_cname0());
			cb.setHidden_sename0(spec.getS_ename0());
			cb.setHidden_scname(spec.getS_cname());
			cb.setHidden_sename(spec.getS_ename());
			cb.setHidden_fcname0(spec.getF_cname0());
			cb.setHidden_fename0(spec.getF_ename0());
			cb.setHidden_fcname(spec.getF_cname());
			cb.setHidden_fename(spec.getF_ename());
			// note最後塞
			List<String> subNote = new ArrayList<>();
			if (StringUtils.isNotBlank(spec.getCname())) {
				subNote.add("電文名稱：" + spec.getCname());
			}
			if (StringUtils.isNotBlank(spec.getOwner())) {
				subNote.add("負責人：" + spec.getOwner());
			}
			if (StringUtils.isNotBlank(spec.getFormat())) {
				subNote.add("格式：" + spec.getFormat());
			}
			if (StringUtils.isNotBlank(spec.getNote())) {
				subNote.add("note：" + spec.getNote());
			}
			cb.setSpecInfo_note(String.join(SIGN_COMMA, subNote));
			cb.setExportFilePath(getExportFilePath(cb.getLogInfo_ID()));
		}

		return cb;
	}

	/*** 主要切電文 ***/
	public static String cutterPro(String telegram, JSONArray cut0, JSONArray cut, List<JSONArray> head0,
			List<JSONArray> head) throws Exception {
		int cutIndex = 0;
		StringBuffer sb = new StringBuffer();
		int gbkLen = getGBKLen(telegram);

		for (JSONArray jarr0 : head0) {
			sb.append(addTgHeader(jarr0));// 上行表頭
		}

		for (Object obj : cut0) {
			Integer cutSize = Integer.parseInt(obj.toString());
			sb.append(String.format(FORMAT_CSV_CELL,
					telegram.substring(subStrLen(telegram, cutIndex), subStrLen(telegram, cutIndex += cutSize))));
			gbkLen -= cutSize;
		}

		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		for (JSONArray jarr : head) {
			sb.append(addTgHeader(jarr));// 下行表頭
		}

		try {
			while (gbkLen > cut.length()) {
				for (Object obj : cut) {
					Integer cutSize = Integer.parseInt(obj.toString());
					sb.append(String.format(FORMAT_CSV_CELL, telegram.substring(subStrLen(telegram, cutIndex),
							subStrLen(telegram, cutIndex += cutSize))));
					gbkLen -= cutSize;
				}
				sb.append(System.lineSeparator());
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return sb.toString();
	}

	// 組表頭顯示
	private static String addTgHeader(JSONArray arr) {
		StringBuffer sb = new StringBuffer();
		if (!arr.isEmpty()) {
			for (Object obj : arr) {
				sb.append(String.format(FORMAT_CSV_CELLHEADER, obj.toString()));
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	/*** 切電文(舊方法) ***/
	public String cutterPro(String text, boolean isAddEqual, JSONArray arr_cut0, JSONArray arr_cut)
			throws UnsupportedEncodingException {
		int cutIndex = 0;
		StringBuffer sb = new StringBuffer();
		int gbkLen = getGBKLen(text);
		for (int i = 0; i < arr_cut0.length(); i++) {
			Integer cutSize = Integer.parseInt(arr_cut0.get(i).toString());
			String row;
			if (isAddEqual) {
				row = SIGN_EQUAL + SIGN_DBQUOTES;
			} else {
				row = SIGN_DBQUOTES;
			}
			row += text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DBQUOTES;
			row = (i < arr_cut0.length() - 1) ? row += SIGN_COMMA : row;
			sb.append(row);
			gbkLen -= cutSize;
		}
		while (gbkLen > arr_cut.length()) {
			sb.append(System.lineSeparator());
			for (int i = 0; i < arr_cut.length(); i++) {
				Integer cutSize = Integer.parseInt(arr_cut.get(i).toString());
				String row;
				if (isAddEqual) {
					row = SIGN_EQUAL + SIGN_DBQUOTES;
				} else {
					row = SIGN_DBQUOTES;
				}
				row += text.substring(subStrLen(text, cutIndex), subStrLen(text, cutIndex += cutSize)) + SIGN_DBQUOTES;
				row = (i < arr_cut.length() - 1) ? row += SIGN_COMMA : row;
				sb.append(row);
				gbkLen -= cutSize;
			}
		}
		return sb.toString();
	}

	private static int subStrLen(String str, int index) throws UnsupportedEncodingException {
		if (str == null) {
			return -1;
		} else if (getGBKLen(str) >= index) {
			int i;
			for (i = 0; i < str.length(); i++) {
				if (getGBKLen(str.substring(0, i)) >= index) {
					break;
				}
			}
			return i;
		} else {
			return -1;
		}
	}

	/*** 匯出檔案 ***/
	public static void exportFile(CutterBean cb) throws Exception {
		List<String> contents = new ArrayList<>();
		contents.add(String.format(FORMAT_EXPORTFILE_SUBTITLE, "Send or Up"));
		contents.add(String.format(FORMAT_EXPORTFILE_CONTENT, cb.getResultS()));
		contents.add(System.lineSeparator());
		contents.add(String.format(FORMAT_EXPORTFILE_SUBTITLE, "Fill or Down"));
		contents.add(String.format(FORMAT_EXPORTFILE_CONTENT, cb.getResultF()));
		exportFile(cb.getExportFilePath(), contents);
	}

	/*** 驗證檔案內容 ***/
	private static void validFileContent(CutterBean cb, String type) throws Exception {
		String msg;
		if (FLAG_LOG.equals(type)) {
			msg = "log檔無";
			if (StringUtils.isBlank(cb.getLogInfo_ID())) {
				throw new Exception(msg + "電文id");
			} else if (StringUtils.isBlank(cb.getLogInfo_send())) {
				throw new Exception(msg + "上行電文");
			} else if (StringUtils.isBlank(cb.getLogInfo_fill())) {
				throw new Exception(msg + "下行電文");
			}
		} else if (FLAG_SPEC.equals(type)) {
			msg = "spec檔無";
			if (StringUtils.isBlank(cb.getSpecSendCut0())) {
				throw new Exception(msg + "上行電文陣列(頭)");
			} else if (StringUtils.isBlank(cb.getSpecSendCut())) {
				throw new Exception(msg + "上行電文陣列(主要)");
			} else if (StringUtils.isBlank(cb.getSpecFillCut0())) {
				throw new Exception(msg + "下行電文陣列(頭)");
			} else if (StringUtils.isBlank(cb.getSpecFillCut())) {
				throw new Exception(msg + "下行電文陣列(主要)");
			}
		}
	}

}
