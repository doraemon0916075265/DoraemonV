package cti.app.appService;

import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;

public class Style {
	private static int row;
	private static final int HEIGHT = 25;

	public static final int FRAME_WIDTH = 1080;
	public static final int FRAME_HEIGHT = 720;

	public static final int TEXTAREA_WIDTH = 146;
	
	// Label-TextField-null-Label-Button
	public static final List<Integer> MODEL_JL1_JTF_NULL_JL2_BTN = Arrays.asList(20, 80, 710, 5, 75, 90, 20);

	// Label-TextField-Button-Component-Button
	public static final List<Integer> MODEL_JL_JTF_JB_JC_BTN = Arrays.asList(20, 80, 698, 12, 80, 90, 20);

	// Label-TextField-null-TextField-null-Label-Button
	public static final List<Integer> MODEL_JL_JTF1_NULL_JTF2_NULL_JL_BTN = Arrays.asList(20, 80, 205, 5, 500, 5, 75, 90, 20);

	// null-Label-null-Button
	public static final List<Integer> MODEL_NULL_JL_NULL_JC_BTN = Arrays.asList(20, 355, 80, 355, 80, 90, 20);

	// Label-Component-null-Component-null-Button
	public static final List<Integer> MODEL_JL_JC1_NULL_JC2_NULL_BTN = Arrays.asList(20, 80, 350, 10, 350, 80, 90, 20);

	// Label-Component-null-Label-Component-Component-Button
	public static final List<Integer> MODEL_JL1_JC1_NULL_JL2_JC2_JC_BTN = Arrays.asList(20, 80, 310, 10, 80, 310, 80, 90, 20);

	// Label-Component-null-Component-null-Component-null-Component-Component-Button
	public static final List<Integer> MODEL_JL_JC1_NULL_JC2_NULL_JC3_NULL_JC4_JC5_BTN = Arrays.asList(20, 80, 172, 7, 172, 8, 172, 7, 172, 80, 90, 20);

	// Label-TextField-null-Label-CheckBox-Component-Button
	public static final List<Integer> MODEL_JL1_JTF_NULL_JL2_JCB_JC_BTN = Arrays.asList(20, 80, 600, 10, 80, 20, 80, 90, 20);

	public static void setBounds(List<Integer> models, List<JComponent> jcs) {
		try {
			if (models.size() == jcs.size()) {
				int modelSum = 0;
				int modelTemp = 0;
				for (Integer model : models) {
					modelSum += model;
				}

				for (int i = 0; i < models.size(); i++) {
					JComponent jc = jcs.get(i);
					if (jc != null) {
						jc.setBounds(FRAME_WIDTH * modelTemp / modelSum, row, FRAME_WIDTH * models.get(i) / modelSum, HEIGHT);
					}
					modelTemp += models.get(i);
				}
			} else {
				throw new Exception("models=" + models.size() + "與JComponents=" + jcs.size() + "數量錯誤");
			}
			nextRow();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void resetRow() {
		row = 15;
	}

	private static void nextRow() {
		row += 40;
	}

}