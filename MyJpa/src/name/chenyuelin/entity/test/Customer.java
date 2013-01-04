package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the customer database table.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7863213895640300306L;

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

	private String sex;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "customer")
	private List<CustomerAddress> customerAddresses;

	/*// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "customer")
	private List<SimpleOrder> simpleOrders;*/

	public Customer() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte[] getCustomData() {
		return this.customData;
	}

	public void setCustomData(byte[] customData) {
		this.customData = customData;
	}

	public byte getDefaultAddress() {
		return this.defaultAddress;
	}

	public void setDefaultAddress(byte defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhoneNum() {
		return this.mobilePhoneNum;
	}

	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	/*public List<SimpleOrder> getSimpleOrders() {
		return this.simpleOrders;
	}

	public void setSimpleOrders(List<SimpleOrder> simpleOrders) {
		this.simpleOrders = simpleOrders;
	}*/

}