package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the t2 database table.
 */
@Entity
@Table(name = "t2")
public class T2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7073269390686095290L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String name;

	// bi-directional many-to-one association to T5
	@OneToMany(mappedBy = "t2")
	private List<T5> t5s;

	public T2() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T5> getT5s() {
		return this.t5s;
	}

	public void setT5s(List<T5> t5s) {
		this.t5s = t5s;
	}

}