package test;

public class Clock {
	private static final Clock C = craeteClock();

	private static Clock craeteClock() {
		return new Clock();
	}

	public static Clock getC() {
		return C;
	}
}
