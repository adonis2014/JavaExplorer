package test;

import org.junit.experimental.categories.Category;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
@Category(TheorySamples.class)
public class TheoryTestSampleTestCase {
	@DataPoint
	public static String YEAR_2007 = "2007";
	@DataPoint
	public static String YEAR_2008 = "2008";
	@DataPoint
	public static String NAME1 = "developer";
	@DataPoint
	public static String NAME2 = "Works";
	@DataPoint
	public static String NAME3 = "developerWorks";

	@DataPoints
	public static String[] data() {
		return new String[] { 
				new String("Monty Burns"),
				new String("Don Geiss"), 
				new String("Arthur Jensen") };
	}
	
	@Theory
	public void test(String data, String data2) {
		System.out.println("test");
		System.out.println(data + "\t" + data2);
	}

	@Theory
	public void test2(String data, String data2) {
		System.out.println("test2");
		System.out.println(data + "\t" + data2);
	}
}
