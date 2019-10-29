package cti.app.bean;

import java.util.Date;

public class FindFileBean {
	private String searchPath;
	private int resultType;

	private String byFileText;
	private boolean isFileText_CaseSensitive = false;

	private String byFileName;
	private boolean isFileName_CaseSensitive = false;

	private String byFileName_Extension;
	private String byFileName_Extension_Ignore;

	private Date byModify_greaterThan;
	private Date byModify_lessThan;

	private String result;

	public String getSearchPath() {
		return searchPath;
	}

	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public String getByFileText() {
		return byFileText;
	}

	public void setByFileText(String byFileText) {
		this.byFileText = byFileText;
	}

	public boolean isFileText_CaseSensitive() {
		return isFileText_CaseSensitive;
	}

	public void setFileText_CaseSensitive(boolean isFileText_CaseSensitive) {
		this.isFileText_CaseSensitive = isFileText_CaseSensitive;
	}

	public String getByFileName() {
		return byFileName;
	}

	public void setByFileName(String byFileName) {
		this.byFileName = byFileName;
	}

	public boolean isFileName_CaseSensitive() {
		return isFileName_CaseSensitive;
	}

	public void setFileName_CaseSensitive(boolean isFileName_CaseSensitive) {
		this.isFileName_CaseSensitive = isFileName_CaseSensitive;
	}

	public String getByFileName_Extension() {
		return byFileName_Extension;
	}

	public void setByFileName_Extension(String byFileName_Extension) {
		this.byFileName_Extension = byFileName_Extension;
	}

	public String getByFileName_Extension_Ignore() {
		return byFileName_Extension_Ignore;
	}

	public void setByFileName_Extension_Ignore(String byFileName_Extension_Ignore) {
		this.byFileName_Extension_Ignore = byFileName_Extension_Ignore;
	}

	public Date getByModify_greaterThan() {
		return byModify_greaterThan;
	}

	public void setByModify_greaterThan(Date byModify_greaterThan) {
		this.byModify_greaterThan = byModify_greaterThan;
	}

	public Date getByModify_lessThan() {
		return byModify_lessThan;
	}

	public void setByModify_lessThan(Date byModify_lessThan) {
		this.byModify_lessThan = byModify_lessThan;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
