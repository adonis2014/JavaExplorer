package name.chenyuelin.entity.test;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import name.chenyuelin.enums.Sex;

/**
 * The persistent class for the customer database table.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -4848794052636860089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	private boolean active;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Lob()
	private String comment;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Lob()
	@Column(name = "custom_data")
	private byte[] customData;

	@Column(name = "default_address")
	private byte defaultAddress;

	private String email;

	@Column(name = "mobile_phone_num")
	private String mobilePhoneNum;

	private String name;

	private String password;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "customer")
	private List<CustomerAddress> customerAddresses;

	// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "customer")
	private List<SimpleOrder> simpleOrders;

	public Customer() {
	}

	public boolean getActive() {
		return this.active;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String getComment() {
		return this.comment;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public byte[] getCustomData() {
		return this.customData;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public byte getDefaultAddress() {
		return this.defaultAddress;
	}

	public String getEmail() {
		return this.email;
	}

	public byte getId() {
		return this.id;
	}

	public String getMobilePhoneNum() {
		return this.mobilePhoneNum;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public Sex getSex() {
		return sex;
	}

	public List<SimpleOrder> getSimpleOrders() {
		return this.simpleOrders;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCustomData(byte[] customData) {
		this.customData = customData;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public void setDefaultAddress(byte defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setSimpleOrders(List<SimpleOrder> simpleOrders) {
		this.simpleOrders = simpleOrders;
	}

}