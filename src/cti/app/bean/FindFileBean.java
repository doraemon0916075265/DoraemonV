package cti.app.bean;

public class FindFileBean {
	private String searchPath;
	private String byText;
	private String byFilename;
	private String byFilenameExtension;
	private String byFilenameExtension_Ignore;
	private String byModify_greaterThan;
	private String byModify_lessThan;
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

	public String getByModify_greaterThan() {
		return byModify_greaterThan;
	}

	public void setByModify_greaterThan(String byModify_greaterThan) {
		this.byModify_greaterThan = byModify_greaterThan;
	}

	public String getByModify_lessThan() {
		return byModify_lessThan;
	}

	public void setByModify_lessThan(String byModify_lessThan) {
		this.byModify_lessThan = byModify_lessThan;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
