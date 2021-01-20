package cti.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import cti.app.bean.FileManagerBean;
import cti.app.bean.FormatBean;

public class Tab04_FormatService extends AppService {

	protected static List<String> genJCB4SpecID_Format(String specPath) {
		return SpecService.readFileInfoID_Format(specPath);
	}

	/*** 讀spec.json檔，ID=Format ***/
	protected static FormatBean readSpecJson_ID_Format(FormatBean tb) {
		FileManagerBean fmb = new FileManagerBean();
		fmb.setPath(tb.getSpecFilePath());
		FileManagerService.fileContentManager(fmb);

		JSONArray jsonArrs = new JSONObject(fmb.getResultString()).getJSONArray(SPEC);
		for (Object o : jsonArrs) {
			JSONObject jo = new JSONObject(o.toString());
			if (TAG_FORMAT.equals(getJsonValue(jo, TAG_ID).toString())) {
				tb.setFormat(jo.get(tb.getSpecID_Format()).toString());
				break;
			}
		}
		return tb;
	}

	/*** 讀format.csv檔 ***/
	protected static FormatBean readFormatCsv(FormatBean tb) {
		FileManagerBean fmb = new FileManagerBean();
		fmb.setPath(tb.getFormatFilePath());
		FileManagerService.fileContentManager(fmb);
		tb.setInput(fmb.getResultString());
		return tb;
	}

	/*** 套用格式 ***/
	public static FormatBean MyStringFormats(FormatBean tb) {
		List<String> list = new ArrayList<>();

		for (String sInputLine : tb.getInput().split("\\r?\\n")) {
			if (StringUtils.isBlank(sInputLine)) {
				continue; // 忽略空白行
			}

			String sInputItems[] = sInputLine.split(",");
			if (sInputItems.length <= 0) {
				continue; // 忽略空陣列
			}
			list.add(String.format(tb.getFormat(), sInputItems));
		}
		tb.setOutput(String.join(System.lineSeparator(), list));
		return tb;
	}

}
