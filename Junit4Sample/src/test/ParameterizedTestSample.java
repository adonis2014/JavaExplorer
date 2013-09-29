package test;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@Category(ParameterizedSample.class)
public class ParameterizedTestSample {
	private String name;
	private int age;

	public ParameterizedTestSample(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Parameters
	public static List<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
				{ "sample1", 1 }, 
				{ "sample2", 2 }, 
				{ "sample3", 3 }, 
				{ "sample4", 4 }, 
				{ "sample5", 5 } 
				});
	}

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
		System.out.println("test\t"+name+"\t"+age);
	}

	@Test
	public void test2() {
		System.out.println("test2\t"+name+"\t"+age);
	}

}
