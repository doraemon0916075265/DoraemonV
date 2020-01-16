package cti.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cti.app.bean.SpecBean;

public class SpecService extends AppService {

	/*** 從Json格式取出所有id ***/
	protected static List<String> readFileInfoID(String pathSpec) {
		List<String> list = new ArrayList<String>();
		try {
			JSONArray jsonArrs = new JSONObject(FileManagerService.readFileContent(pathSpec)).getJSONArray(SPEC);
			for (Object objJA : jsonArrs) {
				String itemID = getJsonValue(objJA, TAG_ID).toString();
				if (StringUtils.isNotBlank(itemID)) {
					list.add(itemID);
				}
			}
		} catch (Exception e) {

		}
		return list;
	}

	/*** 從Json格式取出by id ***/
	protected static SpecBean readFileInfoByID(String pathSpec, String id) throws Exception {
		SpecBean sb = new SpecBean();
		JSONArray jsonArrs = new JSONObject(FileManagerService.readFileContent(pathSpec)).getJSONArray(SPEC);
		for (Object objJA : jsonArrs) {
			if (getJsonValue(objJA, TAG_ID).equals(id)) {
				sb = readFileInfo4OneJSON(new JSONObject(objJA.toString()));
				break;
			}
		}
		return sb;
	}

	/*** 從單一個Json格式取出所有tag ***/
	private static SpecBean readFileInfo4OneJSON(JSONObject jsonObj) throws Exception {
		SpecBean sb = new SpecBean();
		try {
			// 必要欄位，不塞try-catch
			sb.setId(jsonObj.get(TAG_ID).toString());
			sb.setS_cut0(jsonObj.get(TAG_SCUT0).toString());
			sb.setS_cut0(jsonObj.get(TAG_SCUT0).toString());
			sb.setS_cut(jsonObj.get(TAG_SCUT).toString());
			sb.setF_cut0(jsonObj.get(TAG_FCUT0).toString());
			sb.setF_cut(jsonObj.get(TAG_FCUT).toString());

			// 隱藏欄位or非必要欄位，塞try-catch
			try {
				sb.setNote(jsonObj.get(TAG_NOTE).toString());
			} catch (Exception e) {
				sb.setNote("");
			}
			try {
				sb.setOwner(jsonObj.get(TAG_OWNER).toString());
			} catch (Exception e) {
				sb.setOwner("");
			}
			try {
				sb.setCname(jsonObj.get(TAG_CNAME).toString());
			} catch (Exception e) {
				sb.setCname("");
			}
			try {
				sb.setS_cname0(vaildObject_Array2String(jsonObj.get(TAG_SCNAME0)));
			} catch (Exception e) {
				sb.setS_cname0(INIT_JSONARRRAY);
			}
			try {
				sb.setS_ename0(vaildObject_Array2String(jsonObj.get(TAG_SENAME0)));
			} catch (Exception e) {
				sb.setS_ename0(INIT_JSONARRRAY);
			}
			try {
				sb.setS_cname(vaildObject_Array2String(jsonObj.get(TAG_SCNAME)));
			} catch (Exception e) {
				sb.setS_cname(INIT_JSONARRRAY);
			}
			try {
				sb.setS_ename(vaildObject_Array2String(jsonObj.get(TAG_SENAME)));
			} catch (Exception e) {
				sb.setS_ename(INIT_JSONARRRAY);
			}
			try {
				sb.setF_cname0(vaildObject_Array2String(jsonObj.get(TAG_FCNAME0)));
			} catch (Exception e) {
				sb.setF_cname0(INIT_JSONARRRAY);
			}
			try {
				sb.setF_ename0(vaildObject_Array2String(jsonObj.get(TAG_FENAME0)));
			} catch (Exception e) {
				sb.setF_ename0(INIT_JSONARRRAY);
			}
			try {
				sb.setF_cname(vaildObject_Array2String(jsonObj.get(TAG_FCNAME)));
			} catch (Exception e) {
				sb.setF_cname(INIT_JSONARRRAY);
			}
			try {
				sb.setF_ename(vaildObject_Array2String(jsonObj.get(TAG_FENAME)));
			} catch (Exception e) {
				sb.setF_ename(INIT_JSONARRRAY);
			}
			try {
				sb.setFormat(jsonObj.get(TAG_FORMAT).toString());
			} catch (Exception e) {
				sb.setFormat("");
			}

		} catch (Exception e) {
			throw new JSONException(e);
		}
		return sb;
	}

	// 驗證物件為陣列並轉換成字串
	private static String vaildObject_Array2String(Object object) {
		return new JSONArray(object.toString()).toString();
	}
}
