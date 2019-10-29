package cti.app.bean;

import java.util.Date;

public class FindFileConditionBean {
	private String searchPath;
	private String byText;
	private boolean isTextCaseSensitive;
	private String byFilename;
	private String byFilenameExtension;
	private String byFilenameExtension_Ignore;
	private Date byModify_greaterThan;
	private Date byModify_lessThan;
	private String result;

	public String getSearchPath() {
		return searchPath;
	}

	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}

	public String getByText() {
		return byText;
	}

	public void setByText(String byText) {
		this.byText = byText;
	}

	public boolean isTextCaseSensitive() {
		return isTextCaseSensitive;
	}

	public void setTextCaseSensitive(boolean isTextCaseSensitive) {
		this.isTextCaseSensitive = isTextCaseSensitive;
	}

	public String getByFilename() {
		return byFilename;
	}

	public void setByFilename(String byFilename) {
		this.byFilename = byFilename;
	}

	public String getByFilenameExtension() {
		return byFilenameExtension;
	}

	public void setByFilenameExtension(String byFilenameExtension) {
		this.byFilenameExtension = byFilenameExtension;
	}

	public String getByFilenameExtension_Ignore() {
		return byFilenameExtension_Ignore;
	}

	public void setByFilenameExtension_Ignore(String byFilenameExtension_Ignore) {
		this.byFilenameExtension_Ignore = byFilenameExtension_Ignore;
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
