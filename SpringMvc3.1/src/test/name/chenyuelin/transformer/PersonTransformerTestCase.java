package test.name.chenyuelin.transformer;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import name.chenyuelin.dto.PersonDto;
import name.chenyuelin.entity.test.Person;
import name.chenyuelin.enums.Language;
import name.chenyuelin.enums.Sex;
import name.chenyuelin.transformer.PersonTransformer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test personTransformer function.
 * 
 * @author U1
 *
 */
public class PersonTransformerTestCase {

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

	@SuppressWarnings("deprecation")
	@Test
	public void testToPersonDto() {
		//initialize test data.
		long nowTime=System.currentTimeMillis();
		Date birthday=new Date(nowTime);
		Time breakfastTime=new Time(nowTime);
		Timestamp createTime=new Timestamp(nowTime);
		double height=1.78;
		Set<Language> languages=new HashSet<Language>();
		languages.add(Language.chinese);
		languages.add(Language.english);
		String name="³ÂÔÀ÷ë";
		byte id=(byte)1;
		String note="²âÊÔÊý¾Ý";
		int salary=9500;
		byte version=(byte)1;
		
		//create person entity.
		Person person=new Person();
		person.setActive(true);
		person.setBirthday(birthday);
		person.setBreakfastTime(breakfastTime);
		person.setCreateTime(createTime);
		person.setHeight(BigDecimal.valueOf(height));
		person.setId(id);
		
		person.setLanguages(languages);
		person.setName(name);
		person.setNote(note);
		person.setSalary(salary);
		person.setSex(Sex.male);
		person.setVersion(version);
		
		//run the test function
		PersonDto dto=PersonTransformer.toPersonDto(person);
		
		//assert the dto
		assertEquals(dto.getActive(), Boolean.TRUE);
		assertEquals(dto.getBirthday(), birthday);
		assertEquals(dto.getBreakfastTime(), breakfastTime);
		
		XMLGregorianCalendar xmlGregorianCalendar=dto.getCreateTime();
		assertEquals(xmlGregorianCalendar.getYear(),createTime.getYear()+1900);
		assertEquals(xmlGregorianCalendar.getMonth(),createTime.getMonth()+1);
		assertEquals(xmlGregorianCalendar.getDay(),createTime.getDate());
		assertEquals(xmlGregorianCalendar.getHour(),createTime.getHours());
		assertEquals(xmlGregorianCalendar.getMinute(),createTime.getMinutes());
		assertEquals(xmlGregorianCalendar.getSecond(),createTime.getSeconds());
		assertEquals(xmlGregorianCalendar.getMillisecond(),(int)(createTime.getTime()%1000));
		assertEquals(xmlGregorianCalendar.getTimezone(),createTime.getTimezoneOffset());
		
		assertEquals(dto.getHeight(), height);
		assertEquals(dto.getId(), id);
		assertEquals(dto.getLanguages(), languages);
		assertEquals(dto.getName(), name);
		assertEquals(dto.getNote(),note);
		assertEquals(dto.getSalary(),salary);
		assertEquals(dto.getSex(),Sex.male);
		assertEquals(dto.getVersion(),version);
	}

}
