package test.name.chenyuelin.controller;

import junit.framework.Assert;
import name.chenyuelin.entity.test.Person;
import name.chenyuelin.service.UserService;
import name.chenyuelin.webapp.controller.PersonController;
import name.chenyuelin.webapp.dto.PersonDto;
import name.chenyuelin.webapp.transformer.PersonTransformer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PersonTransformer.class})
public class PersonControllerTestCase {
	@InjectMocks
	private PersonController personController = new PersonController();

	@Mock
	private UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPerson() throws Exception {
		byte id=(byte)1;
		
		Person person=new Person();
		person.setId(id);
		
		PersonDto dto=new PersonDto();
		
		Mockito.when(userService.findPerson(id)).thenReturn(person);
		
		
		
		PowerMockito.mockStatic(PersonTransformer.class,Mockito.withSettings().name("getPerson"));
		PowerMockito.when(PersonTransformer.toPersonDto(person)).thenReturn(dto);
		
		ModelAndView mav=personController.getPerson(id);
		
		Mockito.verify(userService).findPerson(id);
		
		PowerMockito.verifyStatic();
		PersonTransformer.toPersonDto(person);
		Assert.assertEquals(mav.getModel().get("person"), dto);
	}
}
