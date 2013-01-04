package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the customer_address database table.
 */
@Entity
@Table(name = "customer_address")
public class CustomerAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8017841601982883303L;

	@EmbeddedId
	private CustomerAddressPK id;

	private byte active;

	@Column(name = "create_time")
	private Timestamp createTime;

	private String phone;

	private String street;

	@Column(name = "update_time")
	private Timestamp updateTime;

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_level_0")
	private Area area1;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_level_1")
	private Area area2;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_level_2")
	private Area area3;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_level_3")
	private Area area4;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_level_4")
	private Area area5;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_level_5")
	private Area area6;

	public CustomerAddress() {
	}

	public CustomerAddressPK getId() {
		return this.id;
	}

	public void setId(CustomerAddressPK id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Area getArea1() {
		return this.area1;
	}

	public void setArea1(Area area1) {
		this.area1 = area1;
	}

	public Area getArea2() {
		return this.area2;
	}

	public void setArea2(Area area2) {
		this.area2 = area2;
	}

	public Area getArea3() {
		return this.area3;
	}

	public void setArea3(Area area3) {
		this.area3 = area3;
	}

	public Area getArea4() {
		return this.area4;
	}

	public void setArea4(Area area4) {
		this.area4 = area4;
	}

	public Area getArea5() {
		return this.area5;
	}

	public void setArea5(Area area5) {
		this.area5 = area5;
	}

	public Area getArea6() {
		return this.area6;
	}

	public void setArea6(Area area6) {
		this.area6 = area6;
	}

}