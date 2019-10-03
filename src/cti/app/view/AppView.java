package cti.app.view;

import java.awt.BorderLayout;

import cti.app.service.AppService;

public class AppView extends AppService {

	public static void executeApp() {
		jtp.setComponent();
		jf.add(jtp);

		jf.add(jl_status, BorderLayout.SOUTH);

		jf.setVisible(true);// 視窗顯示
	}
}