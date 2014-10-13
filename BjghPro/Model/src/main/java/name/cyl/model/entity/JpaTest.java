package name.cyl.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jpa_test database table.
 * 
 */
@Entity
@Table(name="jpa_test")
public class JpaTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public JpaTest() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}