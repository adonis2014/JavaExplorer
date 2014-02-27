package name.chenyuelin.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
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

	private Sex sex;

	@XmlJavaTypeAdapter(Jaxb2SqlDataAdapter.class)
	private Date birthday;

	private double height;

	@XmlJavaTypeAdapter(Jaxb2SqlTimeAdapter.class)
	private Time breakfastTime;

	@XmlSchemaType(name = "dateTime")
	private XMLGregorianCalendar createTime;

	private int salary;

	private String note;

	private Collection<Language> languages;

	private byte version;

	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Time getBreakfastTime() {
		return breakfastTime;
	}

	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	public double getHeight() {
		return height;
	}

	public byte getId() {
		return id;
	}

	public Collection<Language> getLanguages() {
		return languages;
	}

	public String getName() {
		return name;
	}

	public String getNote() {
		return note;
	}

	public int getSalary() {
		return salary;
	}

	public Sex getSex() {
		return sex;
	}

	public byte getVersion() {
		return version;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setBreakfastTime(Time breakfastTime) {
		this.breakfastTime = breakfastTime;
	}

	public void setCreateTime(XMLGregorianCalendar createTime) {
		this.createTime = createTime;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public void setLanguages(Collection<Language> languages) {
		this.languages = languages;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setVersion(byte version) {
		this.version = version;
	}
}
