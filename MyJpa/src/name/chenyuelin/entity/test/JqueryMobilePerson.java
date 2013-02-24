package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the jquery_mobile_person database table.
 */
@Entity
@Table(name = "jquery_mobile_person")
public class JqueryMobilePerson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8485287302518840126L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String country;

	private String hobby;

	@Column(name = "like_pot")
	private String likePot;

	private String name;

	@Lob()
	private String pcommit;

	private String sex;

	@Column(name = "shipping_method")
	private String shippingMethod;

	private byte slider;

	private byte status;

	public JqueryMobilePerson() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getLikePot() {
		return this.likePot;
	}

	public void setLikePot(String likePot) {
		this.likePot = likePot;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcommit() {
		return this.pcommit;
	}

	public void setPcommit(String pcommit) {
		this.pcommit = pcommit;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getShippingMethod() {
		return this.shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public byte getSlider() {
		return this.slider;
	}

	public void setSlider(byte slider) {
		this.slider = slider;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}