package test;

public class TestFailureException extends Exception {

	TestFailureException() {}
	TestFailureException(String info) {
		super(info);
	}
}
