package cti.app.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cti.app.constant.FindFileConstant;

public class FindFileHandler extends FindFileConstant {

	/*** FindFile：找目錄下符合條件的所有檔案 ***/
	public static Map<String, String> findConditionFile(Map<String, String> m) throws Exception {
		String jtf_searchPath = m.get(FindFileConstant.KEY_SEARCHPATH);
		if (StringUtils.isBlank(jtf_searchPath)) {
			throw new NullPointerException(String.format(FORMAT_MSG_EXCEPTION, FindFileConstant.VALUE_SEARCHPATH, ERRMSG_ISBLANK));
		}
		List<String> list = new ArrayList<>();
		String resultStr = "";
		AppHandler.findAllFile(list, jtf_searchPath);

		for (String str : list) {
			resultStr += (str + System.lineSeparator());
		}
		m.put(FindFileConstant.KEY_RESULT, resultStr);
		return m;
	}

}
