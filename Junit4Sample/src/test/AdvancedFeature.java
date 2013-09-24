package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.results.PrintableResult;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class AdvancedFeature {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		Result result=JUnitCore.runClasses(TheoryTestSampleTestCase.class);
//		System.out.println(new PrintableResult(result.getFailures()));
		PrintableResult.testResult(TheoryTestSampleTestCase.class);
	}

}
