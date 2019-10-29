package cti.app.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

public class FileManagerBean {
	private String path = FileSystemView.getFileSystemView().getHomeDirectory().toString();
	private String tempPath;
	private String resultType; // 檔案或資料夾 FILE or DIRECTORY
	private String recordType; // 單筆或多筆 ONE or ALL
	private String resultString;
	private List<String> resultList = new ArrayList<String>();

	// 資料夾
	private String byDirName;
	private boolean isDirName_CaseSensitive = false;
	private String byDirNameFuzzy;
	private Date byDir_Modify_greaterThan;
	private Date byDir_Modify_lessThan;

	// 檔案
	private String byFileName;
	private boolean isFileName_CaseSensitive = false;
	private String byFileNameFuzzy;
	private String byFileName_Extension;
	private String byFileName_Extension_Ignore;
	private Date byFile_Modify_greaterThan;
	private Date byFile_Modify_lessThan;

	// 檔案內容
	private String byFileText;
	private boolean isFileText_CaseSensitive = false;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public List<String> getResultList() {
		return resultList;
	}

	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}

	public String getByDirName() {
		return byDirName;
	}

	public void setByDirName(String byDirName) {
		this.byDirName = byDirName;
	}

	public boolean isDirName_CaseSensitive() {
		return isDirName_CaseSensitive;
	}

	public void setDirName_CaseSensitive(boolean isDirName_CaseSensitive) {
		this.isDirName_CaseSensitive = isDirName_CaseSensitive;
	}

	public String getByDirNameFuzzy() {
		return byDirNameFuzzy;
	}

	public void setByDirNameFuzzy(String byDirNameFuzzy) {
		this.byDirNameFuzzy = byDirNameFuzzy;
	}

	public Date getByDir_Modify_greaterThan() {
		return byDir_Modify_greaterThan;
	}

	public void setByDir_Modify_greaterThan(Date byDir_Modify_greaterThan) {
		this.byDir_Modify_greaterThan = byDir_Modify_greaterThan;
	}

	public Date getByDir_Modify_lessThan() {
		return byDir_Modify_lessThan;
	}

	public void setByDir_Modify_lessThan(Date byDir_Modify_lessThan) {
		this.byDir_Modify_lessThan = byDir_Modify_lessThan;
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

	public String getByFileNameFuzzy() {
		return byFileNameFuzzy;
	}

	public void setByFileNameFuzzy(String byFileNameFuzzy) {
		this.byFileNameFuzzy = byFileNameFuzzy;
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

	public Date getByFile_Modify_greaterThan() {
		return byFile_Modify_greaterThan;
	}

	public void setByFile_Modify_greaterThan(Date byFile_Modify_greaterThan) {
		this.byFile_Modify_greaterThan = byFile_Modify_greaterThan;
	}

	public Date getByFile_Modify_lessThan() {
		return byFile_Modify_lessThan;
	}

	public void setByFile_Modify_lessThan(Date byFile_Modify_lessThan) {
		this.byFile_Modify_lessThan = byFile_Modify_lessThan;
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

}