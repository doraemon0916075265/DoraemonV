package cti.app.service;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cti.app.bean.SpecBean;
import cti.app.bean.SpecInfoBean;

public class SpecInfoService extends AppService {

	protected static List<String> genJCB4SpecID(String specPath) {
		return SpecService.readFileInfoID(specPath);
	}

	/*** 讀檔 ***/
	protected static SpecInfoBean readFileInfo(SpecInfoBean sib) throws Exception {

		///////////////////

		JSONArray jsonArrs = new JSONObject(readFileContent(sib.getSpecFilePath())).getJSONArray("Spec");
		Iterator<String> o = jsonArrs.getJSONObject(0).keys();
		while (o.hasNext()) {
			System.out.println(o.next().toString());
		}

		for (Object objJA : jsonArrs) {
			// if (getJsonValue(objJA, TAG_ID).equals(id)) {
			// sb = readFileInfo4OneJSON(new JSONObject(objJA.toString()));
			// break;
			// }
		}

		////////////////////

		StringBuffer sb = new StringBuffer("");
		SpecBean spec = SpecService.readFileInfoByID(sib.getSpecFilePath(), sib.getSpecID());

		sb.append(String.format(FORMAT_SPECINFO, "ＩＤ", spec.getId()));
		sb.append(String.format(FORMAT_SPECINFO, "切上行電文（頭）", spec.getS_cut0()));
		sb.append(String.format(FORMAT_SPECINFO, "上行電文（頭）欄位（中）", spec.getS_cname0()));
		sb.append(String.format(FORMAT_SPECINFO, "上行電文（頭）欄位（英）", spec.getS_ename0()));
		sb.append(String.format(FORMAT_SPECINFO, "切上行電文（身）", spec.getS_cut()));
		sb.append(String.format(FORMAT_SPECINFO, "上行電文（身）欄位（中）", spec.getS_cname()));
		sb.append(String.format(FORMAT_SPECINFO, "上行電文（身）欄位（英）", spec.getS_ename()));
		sb.append(String.format(FORMAT_SPECINFO, "切下行電文（頭）", spec.getF_cut0()));
		sb.append(String.format(FORMAT_SPECINFO, "下行電文（頭）欄位（中）", spec.getF_cname0()));
		sb.append(String.format(FORMAT_SPECINFO, "下行電文（頭）欄位（英）", spec.getF_ename0()));
		sb.append(String.format(FORMAT_SPECINFO, "切下行電文（身）", spec.getF_cut()));
		sb.append(String.format(FORMAT_SPECINFO, "Ｎｏｔｅ", spec.getNote()));
		sb.append(String.format(FORMAT_SPECINFO, "下行電文（身）欄位（中）", spec.getF_cname()));
		sb.append(String.format(FORMAT_SPECINFO, "下行電文（身）欄位（英）", spec.getS_ename()));
		sb.append(String.format(FORMAT_SPECINFO, "負責人", spec.getOwner()));
		sb.append(String.format(FORMAT_SPECINFO, "格式", spec.getFormat()));
		sib.setResult(sb.toString().replaceAll(" ", "　"));
		return sib;
	}

}
