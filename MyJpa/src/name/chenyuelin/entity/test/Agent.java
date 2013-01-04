package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the agent database table.
 */
@Entity
@Table(name = "agent")
public class Agent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2487982551158720096L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private String name;

	// bi-directional many-to-one association to Hotel
	@OneToMany(mappedBy = "agent")
	private List<Hotel> hotels;

	public Agent() {
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

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

}