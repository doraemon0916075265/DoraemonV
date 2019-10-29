package cti.app.service;

import cti.app.bean.FileManagerBean;
import cti.app.bean.FindFileBean;

public class FindFileService extends AppService {

	public static FindFileBean findConditionFile(FindFileBean ffb) {
		FileManagerBean fmb = new FileManagerBean();
		fmb.setPath(ffb.getSearchPath());

		fmb.setResultType(FM_RESULTTYPE_LIST.get(ffb.getResultType()));

		fmb.setByFileText(ffb.getByFileText());
		fmb.setFileText_CaseSensitive(ffb.isFileText_CaseSensitive());

		fmb.setByDirNameFuzzy(ffb.getByFileName());
		fmb.setByDirName(ffb.getByFileName());
		fmb.setByFileNameFuzzy(ffb.getByFileName());
		fmb.setByFileName(ffb.getByFileName());
		fmb.setDirName_CaseSensitive(ffb.isFileName_CaseSensitive());
		fmb.setFileName_CaseSensitive(ffb.isFileName_CaseSensitive());

		fmb.setByFileName_Extension(ffb.getByFileName_Extension());
		fmb.setByFileName_Extension_Ignore(ffb.getByFileName_Extension_Ignore());

		fmb.setByDir_Modify_greaterThan(ffb.getByModify_greaterThan());
		fmb.setByDir_Modify_lessThan(ffb.getByModify_lessThan());
		fmb.setByFile_Modify_greaterThan(ffb.getByModify_greaterThan());
		fmb.setByFile_Modify_lessThan(ffb.getByModify_lessThan());

		FileManagerService.fileManager(fmb);

		ffb.setResultType(FM_RESULTTYPE_LIST.indexOf(fmb.getResultType()));
		ffb.setResult(fmb.getResultString());
		return ffb;
	}

}
