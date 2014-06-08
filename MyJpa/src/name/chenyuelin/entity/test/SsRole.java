package name.chenyuelin.entity.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the ss_role database table.
 * 
 */
@Entity
@Table(name="ss_role")
public class SsRole implements Serializable {
	private static final long serialVersionUID = -3849678421238062859L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private byte id;

	private String description;

	private Boolean enabled;

	private String name;

    public SsRole() {
    }

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}