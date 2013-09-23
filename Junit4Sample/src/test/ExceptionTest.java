package test;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

public class ExceptionTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @Rule
 	public ErrorCollector collector= new ErrorCollector();
    
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
	
    @Test(expected=RuntimeException.class)
    public void test() {
        throw new RuntimeException();
    }

    @Test
    public void test1() {
        throw new RuntimeException();
    }
    
    @Test
    public void test2() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("required");
        throw new RuntimeException("required");
    }
    
    @Test
    public void test3()throws Exception{
        expectedEx.expect(IOException.class);
        expectedEx.expectMessage("IOException");
        throw new IOException("IOException");
    }
    
    @Test
    public void test4() throws Exception{
    	collector.addError(new Throwable("first thing went wrong"));
        collector.addError(new Throwable("second thing went wrong"));
        collector.checkThat("", CoreMatchers.not(CoreMatchers.equalTo("ERROR!")));

    }
}
