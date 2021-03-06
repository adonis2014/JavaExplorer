package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the simple_order database table.
 */
@Entity
@Table(name = "simple_order")
public class SimpleOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8317266297035968760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean active;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "order_num")
	private String orderNum;

	@Column(name = "order_status")
	private String orderStatus;

	private String phone;

	private String street;

	@Column(name = "update_time")
	private Timestamp updateTime;

	@Column(name = "update_user_category")
	private byte updateUserCategory;

	@Column(name = "update_user_id")
	private byte updateUserId;

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

	// bi-directional many-to-one association to SimpleOrderHistory
	@OneToMany(mappedBy = "simpleOrder")
	private List<SimpleOrderHistory> simpleOrderHistories;

	public SimpleOrder() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public byte getUpdateUserCategory() {
		return this.updateUserCategory;
	}

	public void setUpdateUserCategory(byte updateUserCategory) {
		this.updateUserCategory = updateUserCategory;
	}

	public byte getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(byte updateUserId) {
		this.updateUserId = updateUserId;
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

	public List<SimpleOrderHistory> getSimpleOrderHistories() {
		return this.simpleOrderHistories;
	}

	public void setSimpleOrderHistories(List<SimpleOrderHistory> simpleOrderHistories) {
		this.simpleOrderHistories = simpleOrderHistories;
	}

}