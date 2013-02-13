package name.chenyuelin.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import name.chenyuelin.enums.Language;
import name.chenyuelin.enums.Sex;
import name.chenyuelin.transformer.Jaxb2SqlDataAdapter;
import name.chenyuelin.transformer.Jaxb2SqlTimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {
	private byte id;
	
	private String name;
	
	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

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

	public void setBreakfastTime(Time breakfastTime) {
		this.breakfastTime = breakfastTime;
	}

	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(XMLGregorianCalendar createTime) {
		this.createTime = createTime;
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

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	private Sex sex;
	
	@XmlJavaTypeAdapter(Jaxb2SqlDataAdapter.class)
	private Date birthday;
	private double height;
	
	@XmlJavaTypeAdapter(Jaxb2SqlTimeAdapter.class)
	private Time breakfastTime;
	
	@XmlSchemaType(name="dateTime")
    private XMLGregorianCalendar createTime;
	
	private int salary;
	
	private String note;
	
	private Set<Language> languages;
	
	private byte version;
	
	private Boolean active;
}
