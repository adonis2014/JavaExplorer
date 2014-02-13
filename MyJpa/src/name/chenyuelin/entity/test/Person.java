package name.chenyuelin.entity.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import name.chenyuelin.converter.JpaSqlSetConverter;
import name.chenyuelin.converter.SqlSetLanguageConverter;
import name.chenyuelin.enums.Language;
import name.chenyuelin.enums.Sex;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

/**
 * The persistent class for the person database table.
 */
@Entity
@Table(name = "person")
@Converter(name = "languagesConverter", converterClass = SqlSetLanguageConverter.class)
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994417940672918285L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private Boolean active;

	private Date birthday;

	@Column(name = "breakfast_time")
	private Time breakfastTime;

	@Column(name = "create_time",insertable=false,updatable=false)
	private Timestamp createTime;

	@Lob()
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "custom_data")
	private byte[] customData;

	private BigDecimal height;

	@Convert("languagesConverter")
	private Collection<Language> languages;

	@javax.persistence.Convert(converter=JpaSqlSetConverter.class)
	@Column(name = "languages",insertable=false,updatable=false)
	private Collection<Language> languages2;
	
	private String name;

	@Lob()
	private String note;

	private int salary;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	private byte version;

	// bi-directional many-to-one association to Department
	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;

	// bi-directional many-to-many association to Department
	@ManyToMany(mappedBy = "persons2")
	private List<Department> departments;

	public Person() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Time getBreakfastTime() {
		return this.breakfastTime;
	}

	public void setBreakfastTime(Time breakfastTime) {
		this.breakfastTime = breakfastTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte[] getCustomData() {
		return this.customData;
	}

	public void setCustomData(byte[] customData) {
		this.customData = customData;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public Collection<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Collection<Language> languages) {
		this.languages = languages;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public byte getVersion() {
		return this.version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(50);
		sb.append("{id:").append(id).append(", ");
		sb.append("active:").append(active).append(", ");
		sb.append("birthday:").append(birthday).append(", ");
		sb.append("breakfastTime:").append(breakfastTime).append(", ");
		sb.append("createTime:").append(createTime).append(", ");
		sb.append("height:").append(height).append(", ");
		sb.append("name:").append(name).append(", ");
		sb.append("salary:").append(salary).append(", ");
		sb.append("languages:").append(languages).append(", ");
		sb.append("sex:").append(sex).append(", ");
		sb.append("note:").append(note).append("}");
		return sb.toString();
	}

	public Collection<Language> getLanguages2() {
		return languages2;
	}

	public void setLanguages2(Collection<Language> languages2) {
		this.languages2 = languages2;
	}
}