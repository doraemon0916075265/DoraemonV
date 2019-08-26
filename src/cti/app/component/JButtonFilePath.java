package cti.app.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.JTextComponent;

import cti.app.constant.AppConstant;
import cti.app.service.AppService;

public class JButtonFilePath extends JButtonSimple {

	public JButtonFilePath() {
		super();
	}

	public JButtonFilePath(JTextComponent jtc) {
		super();
		setFileChooser(jtc);
	}

	/*** 取檔案路徑 ***/
	private void setFileChooser(JTextComponent jtc) {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				LookAndFeel laf = UIManager.getLookAndFeel();// 預設風格
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 當前系統風格
					JFileChooser jfc = new JFileChooser();
					jfc.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());// 預設桌面
					jfc.setApproveButtonText(AppConstant.BTN_CONFIRM);
					jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						jtc.setText(jfc.getSelectedFile().toString());
						AppService.showStatus(AppConstant.MSG_GET + jtc.getName() + AppConstant.SIGN_SPACE + jfc.getSelectedFile().toString());
					}
				} catch (Exception e) {

				} finally {
					try {
						UIManager.setLookAndFeel(laf);// 預設風格
					} catch (UnsupportedLookAndFeelException e) {

					}
				}
			}
		});
	}
}
