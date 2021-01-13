package cti.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cti.app.bean.FileManagerBean;
import cti.app.bean.SpecManagerBean;

public class Tab02_SpecManagerService extends AppService {

	protected static List<String> genJCB4SpecID(String specPath) {
		return SpecService.readFileInfoID(specPath);
	}

	/*** 讀檔 ***/
	protected static SpecManagerBean readJsonSpecInfo(SpecManagerBean smb) throws Exception {
		StringBuffer sb = new StringBuffer();
		FileManagerBean fmb = new FileManagerBean();
		fmb.setPath(smb.getSpecFilePath());
		FileManagerService.fileContentManager(fmb);

		JSONArray jsonArrs = new JSONObject(fmb.getResultString()).getJSONArray(SPEC);

		for (Object o : jsonArrs) {
			JSONObject jo = new JSONObject(o.toString());
			if (smb.getSpecID().equals(getJsonValue(jo, TAG_ID).toString())) {
				// Iterator<String> i = jo.keys();
				Iterator<String> i = sortedIterator(jo.keys());
				while (i.hasNext()) {
					String key = i.next();
					sb.append(String.format(FORMAT_SPECINFO, key, jo.get(key)));
				}
			}
		}

		smb.setResult(sb.toString());

		return smb;
	}

	public static Iterator<String> sortedIterator(Iterator<String> it) {
		List<String> list = new ArrayList<String>();
		while (it.hasNext()) {
			list.add(it.next());
		}

		Collections.sort(list, null);
		return list.iterator();
	}

}
