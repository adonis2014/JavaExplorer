/**
 * 
 */
package name.chenyuelin.command;

import java.sql.Date;
import java.sql.Time;

import name.chenyuelin.transformer.JsonSqlDateDeserializer;
import name.chenyuelin.transformer.JsonSqlTimeDeserializer;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * @author P1
 * @version 1.0 Jan 5, 2013
 */
public class Person {
	private String name;
	private String sex;
	private Date birthday;
	private double height;
	private Time breakfastTime;
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
}
