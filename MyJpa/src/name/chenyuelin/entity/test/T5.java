package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the t5 database table.
 */
@Entity
@Table(name = "t5")
public class T5 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3118634657321123566L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private String name;

	// bi-directional many-to-one association to T2
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t2key")
	private T2 t2;

	public T5() {
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

	public T2 getT2() {
		return this.t2;
	}

	public void setT2(T2 t2) {
		this.t2 = t2;
	}

}