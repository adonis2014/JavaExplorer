/**
 * 
 */
package name.chenyuelin.webapp.command;

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
import name.chenyuelin.webapp.transformer.Jaxb2SqlDataAdapter;
import name.chenyuelin.webapp.transformer.Jaxb2SqlTimeAdapter;
import name.chenyuelin.webapp.transformer.JsonSqlDateDeserializer;
import name.chenyuelin.webapp.transformer.JsonSqlTimeDeserializer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author P1
 * @version 1.0 Jan 5, 2013
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCommand {
	private Integer id;
	private String name;
	private Sex sex;

	@XmlJavaTypeAdapter(Jaxb2SqlDataAdapter.class)
	private Date birthday;

	@XmlJavaTypeAdapter(Jaxb2SqlTimeAdapter.class)
	private Time breakfastTime;

	private double height;

	private Set<Language> languages;

	@XmlSchemaType(name = "dateTime")
	/*
	 * @XmlSchemaType(name="date")
	 * @XmlSchemaType(name="time")
	 */
	private XMLGregorianCalendar createTime;

	private int salary;

	private String note;

	private boolean active;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(50);
		sb.append("{name:").append(name).append(", ");
		sb.append("sex:").append(sex).append(", ");
		sb.append("height:").append(height).append(", ");
		sb.append("birthday:").append(birthday).append(", ");
		sb.append("breakfastTime:").append(breakfastTime).append(", ");
		sb.append("createTime:").append(createTime).append(", ");
		sb.append("languages:").append(languages).append(", ");
		sb.append("note:").append(note).append("}");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).append(name).append(sex).append(birthday).append(height).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj != null && PersonCommand.class.isAssignableFrom(obj.getClass())) {
			PersonCommand f = (PersonCommand) obj;
			flag = new EqualsBuilder().append(name, f.name).append(id, f.id).append(sex, f.sex).isEquals();
		}
		return flag;
	}

}
