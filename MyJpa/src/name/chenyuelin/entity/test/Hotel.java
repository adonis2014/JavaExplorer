package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the hotel database table.
 */
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4491250559950737665L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private String address;

	@Column(name = "carea_id")
	private int careaId;

	private String name;

	private String switchboard;

	// bi-directional many-to-one association to Agent
	@ManyToOne(fetch = FetchType.LAZY)
	private Agent agent;

	public Hotel() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCareaId() {
		return this.careaId;
	}

	public void setCareaId(int careaId) {
		this.careaId = careaId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSwitchboard() {
		return this.switchboard;
	}

	public void setSwitchboard(String switchboard) {
		this.switchboard = switchboard;
	}

	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}