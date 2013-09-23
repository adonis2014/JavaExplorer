package test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.matchers.StringContains;

public class HamcrestSampleTestCase {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Ignore
	@Test
	public void sampleTest()throws Exception {
		org.junit.Assert.assertThat(new String("123"), CoreMatchers.is(new String("123")));
		org.junit.Assert.assertThat("123", CoreMatchers.sameInstance("123"));
		org.junit.Assert.assertThat("³ÂÔÀ÷ë", StringContains.containsString("÷ë"));
		org.junit.Assert.assertThat("³ÂÔÀ÷ë", CoreMatchers.not(StringContains.containsString("ÕÅ")));
	}
	
	@Ignore
	@Test
	public void assumptionTest()throws Exception {
		Assume.assumeTrue(true);
		System.out.println("true");
	}
	
	@Ignore
	@Test
	public void assumptionTest2()throws Exception {
		Assume.assumeTrue(false);
		System.out.println("false");
	}
	
	@Test
	public void test() throws Exception {
	    Set<String> set=new HashSet<String>();
	    set.add(new String("1"));
	    set.add(new String("2"));
	    
	    Set<String> set2=new HashSet<String>();
        set2.add(new String("1"));
        set2.add(new String("2"));
        
        Assert.assertEquals(set, set2);
	}
}
