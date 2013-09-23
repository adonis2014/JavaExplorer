package test;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import name.cyl.jersey.services.Person;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest()
public class MockitoSampleTestCase {
	
	@Mock
	private Person person;
	
	@Mock
	private Set<Object> setMock;
	
	@Spy
	private List<String> list=new ArrayList<String>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	    PowerMockito.mockStatic(Math.class);  
        Mockito.when(Math.random()).thenReturn(123.0);
	}
	
	@Test
    public void testPowerMock()throws Exception {
	    Assert.assertEquals(Math.random(), 123.0);
	}
	
	@Test
	public void test1()throws Exception {
		Mockito.when(person.getName()).thenReturn("test string");
		Mockito.when(setMock.add(Mockito.any())).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                Object[] args=invocation.getArguments();
                System.out.println(args.length);
                return true;
            }
        });
		Assert.assertEquals("test string", person.getName());
		Mockito.verify(person).getName();
		Mockito.verify(person,Mockito.only()).getName();
		
		Assert.assertTrue(setMock.add("ff"));
		Mockito.verify(setMock).add("ff");
	}
	
	@Test
	public void test2()throws Exception {
		Assert.assertNull(person.getName());
	}
	
	@Test
    public void test3()throws Exception {
        Assert.assertEquals(list.size(),0);
        
        String firstString="hello world!";
        list.add(firstString);
        Assert.assertEquals(list.get(0),firstString);
        Mockito.verify(list).add(firstString);
        Mockito.verify(list).get(0);
    }
}
