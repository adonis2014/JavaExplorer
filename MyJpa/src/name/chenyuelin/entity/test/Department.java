package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the department database table.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6360956422867987157L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private String name;

	@Lob()
	private String note;

	// bi-directional many-to-one association to Department
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Department department;

	// bi-directional many-to-one association to Department
	@OneToMany(mappedBy = "department")
	private List<Department> departments;

	// bi-directional many-to-one association to Person
	@OneToMany(mappedBy = "department")
	private List<Person> persons1;

	// bi-directional many-to-many association to Person
	@ManyToMany
	@JoinTable(name = "person_department_association", joinColumns = { @JoinColumn(name = "department_id") }, inverseJoinColumns = { @JoinColumn(name = "person_id") })
	private List<Person> persons2;

	public Department() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
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

	public List<Person> getPersons1() {
		return this.persons1;
	}

	public void setPersons1(List<Person> persons1) {
		this.persons1 = persons1;
	}

	public List<Person> getPersons2() {
		return this.persons2;
	}

	public void setPersons2(List<Person> persons2) {
		this.persons2 = persons2;
	}

}