package notes;

public abstract class 戶口名簿業務 {
	protected abstract void 檢查證件();

	protected abstract void 表格填寫();

	protected abstract void 繳交規費();

	public final void 辦理業務() {
		this.檢查證件();
		this.表格填寫();
		this.繳交規費();
	}

}
