/**
 * 
 */
package name.chenyuelin.command;

import java.sql.Date;
import java.sql.Time;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import name.chenyuelin.transformer.Jaxb2SqlDataAdapter;
import name.chenyuelin.transformer.Jaxb2SqlTimeAdapter;
import name.chenyuelin.transformer.JsonSqlDateDeserializer;
import name.chenyuelin.transformer.JsonSqlTimeDeserializer;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * @author P1
 * @version 1.0 Jan 5, 2013
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	private String name;
	private String sex;
	
	@XmlJavaTypeAdapter(Jaxb2SqlDataAdapter.class)
	private Date birthday;
	private double height;
	
	@XmlJavaTypeAdapter(Jaxb2SqlTimeAdapter.class)
	private Time breakfastTime;
	
	@XmlSchemaType(name="dateTime")
    private XMLGregorianCalendar createTime;
	@XmlSchemaType(name="date")
    private XMLGregorianCalendar createTime1;
	@XmlSchemaType(name="time")
    private XMLGregorianCalendar createTime2;
	
	private int salary;
	private String note;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	
	@JsonDeserialize(using = JsonSqlDateDeserializer.class)
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Time getBreakfastTime() {
		return breakfastTime;
	}
	
	@JsonDeserialize(using = JsonSqlTimeDeserializer.class)
	public void setBreakfastTime(Time breakfastTime) {
		this.breakfastTime = breakfastTime;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }
    public void setCreateTime(XMLGregorianCalendar createTime) {
        this.createTime = createTime;
    }
    public XMLGregorianCalendar getCreateTime1() {
        return createTime1;
    }
    public void setCreateTime1(XMLGregorianCalendar createTime1) {
        this.createTime1 = createTime1;
    }
    public XMLGregorianCalendar getCreateTime2() {
        return createTime2;
    }
    public void setCreateTime2(XMLGregorianCalendar createTime2) {
        this.createTime2 = createTime2;
    }
}
