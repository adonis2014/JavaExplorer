package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * The persistent class for the t1 database table.
 */
@Entity
@Table(name = "t1")
public class T1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8676518092394459001L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dt;

	@Temporal(TemporalType.DATE)
	private Date fild1;

	@Column(name = "parent_id")
	private byte parentId;

	private Time timee;

	public T1() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDt() {
		return this.dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Date getFild1() {
		return this.fild1;
	}

	public void setFild1(Date fild1) {
		this.fild1 = fild1;
	}

	public byte getParentId() {
		return this.parentId;
	}

	public void setParentId(byte parentId) {
		this.parentId = parentId;
	}

	public Time getTimee() {
		return this.timee;
	}

	public void setTimee(Time timee) {
		this.timee = timee;
	}

}