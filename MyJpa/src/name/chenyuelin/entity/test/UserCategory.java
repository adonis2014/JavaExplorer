package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the user_category database table.
 */
@Entity
@Table(name = "user_category")
public class UserCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2757244403150194262L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private byte active;

	@Column(name = "category_name")
	private String categoryName;

	@Lob()
	private String comment;

	public UserCategory() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}