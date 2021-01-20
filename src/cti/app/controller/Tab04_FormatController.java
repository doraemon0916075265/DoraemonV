package cti.app.controller;

import java.util.Arrays;

import cti.app.bean.FormatBean;
import cti.app.service.FileManagerService;
import cti.app.view.Tab04_FormatView;

public class Tab04_FormatController extends Tab04_FormatView {

	private static FormatBean fb = new FormatBean();

	/*** 初始化欄位 ***/
	public static void doFormShow() {
		getAllProperties();
		fb.setSpecFilePath(FileManagerService.findFileOnDesktopByFileName(FM_FILENAME_SPEC));
		fb.setFormatFilePath(FileManagerService.findFileOnDesktopByFileName(FM_FILENAME_FORMAT));
		fb.setExportFilePath(getHomeDirectoryFilePath("", FILENAME_EXTENSION_TXT));
		fb.setInput("");
		fb.setOutput("");
		setProperties(); // 避免觸發讀檔
		fb.setSpecID_Format(genJCB4SpecID_Format(fb.getSpecFilePath()).get(0)); // 觸發讀檔
		setAllProperties();
	}

	/*** 讀檔 ***/
	public static void doReadFile() throws Exception {
		getAllProperties();
		validateInput_FilePath(jtf_specFilePath);
		validateInput_FilePath(jtf_formatFilePath);
		fb = readSpecJson_ID_Format(fb);
		fb = readFormatCsv(fb);
		setProperties(); // 避免觸發讀檔
	}

	/*** 執行套用格式 ***/
	public static void doStringFormat() {
		getAllProperties();
		fb = MyStringFormats(fb);
		setProperties(); // 避免觸發讀檔
	}

	/*** 匯出 ***/
	public static void doExport() throws Exception {
		getAllProperties();
		validateInput_ExportPath(jtf_exportFilePath, Arrays.asList("TXT"));
		validateInput_Text(jta_output);
		exportFile(fb.getExportFilePath(), Arrays.asList(fb.getOutput()));
	}

	/*** 從欄位中取出所有值塞入bean ***/
	private static void getAllProperties() {
		fb.setSpecFilePath(jtf_specFilePath.getText());
		fb.setFormatFilePath(jtf_formatFilePath.getText());
		fb.setSpecID_Format(getPulldownItem(jcb_specID_Format));
		fb.setFormat(jtf_format.getText());
		fb.setExportFilePath(jtf_exportFilePath.getText());
		fb.setInput(jta_input.getText());
		fb.setOutput(jta_output.getText());
	}

	/*** 從bean中取出所有值塞入欄位 ***/
	private static void setAllProperties() {
		jcb_specID_Format.setSelectedItem(fb.getSpecID_Format());
		setProperties();
	}

	/*** 從bean中取出部分值塞入欄位 ***/
	private static void setProperties() {
		jtf_specFilePath.setText(fb.getSpecFilePath());
		jtf_formatFilePath.setText(fb.getFormatFilePath());
		jtf_format.setText(fb.getFormat());
		jtf_exportFilePath.setText(fb.getExportFilePath());
		jta_input.setText(fb.getInput());
		jta_output.setText(fb.getOutput());
	}

}
