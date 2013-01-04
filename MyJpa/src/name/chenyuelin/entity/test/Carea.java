package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the carea database table.
 */
@Entity
@Table(name = "carea")
public class Carea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590878967245496125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "carea_type")
	private byte careaType;

	private String name;

	public Carea() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getCareaType() {
		return this.careaType;
	}

	public void setCareaType(byte careaType) {
		this.careaType = careaType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}