package test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.suppliers.TestedOn;
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

	@DataPoint
	public static int AGE = 18;
	
	@DataPoints
	public static String[] data() {
		return new String[] { 
				new String("Monty Burns"),
				new String("Don Geiss"), 
				new String("Arthur Jensen") };
	}
	
	@Ignore
	@Theory
	public void test(String data, String data2) {
		System.out.println("test");
		System.out.println(data + "\t" + data2);
	}

	@Ignore
	@Theory
	public void test2(String data, String data2) {
		System.out.println("test2");
		System.out.println(data + "\t" + data2);
	}
	
	@Theory
	public void test3(@TestedOn(ints={9,8}) int data, String data2) {
		System.out.println("test3");
		System.out.println(data + "\t" + data2);
	}
	
	@Theory
	public void test4() {
		System.out.println("test4");
		Assert.fail();
	}
}
