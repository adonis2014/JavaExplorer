package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the simple_order_history database table.
 */
@Entity
@IdClass(SimpleOrderHistoryPK.class)
@Table(name = "simple_order_history")
public class SimpleOrderHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4528144913640138439L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private SimpleOrder simpleOrder;
	
	@Id
	@Column(name="history_sequence")
	private byte historySequence;
	
	@Column(name = "area_level_0")
	private int areaLevel0;

	@Column(name = "area_level_1")
	private int areaLevel1;

	@Column(name = "area_level_2")
	private int areaLevel2;

	@Column(name = "area_level_3")
	private int areaLevel3;

	@Column(name = "area_level_4")
	private int areaLevel4;

	@Column(name = "area_level_5")
	private int areaLevel5;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "customer_id")
	private byte customerId;

	@Column(name = "order_status")
	private String orderStatus;

	private String phone;

	private String street;

	@Column(name = "update_user_category")
	private byte updateUserCategory;

	@Column(name = "update_user_id")
	private byte updateUserId;

	public SimpleOrderHistory() {
	}

	public int getAreaLevel0() {
		return this.areaLevel0;
	}

	public void setAreaLevel0(int areaLevel0) {
		this.areaLevel0 = areaLevel0;
	}

	public int getAreaLevel1() {
		return this.areaLevel1;
	}

	public void setAreaLevel1(int areaLevel1) {
		this.areaLevel1 = areaLevel1;
	}

	public int getAreaLevel2() {
		return this.areaLevel2;
	}

	public void setAreaLevel2(int areaLevel2) {
		this.areaLevel2 = areaLevel2;
	}

	public int getAreaLevel3() {
		return this.areaLevel3;
	}

	public void setAreaLevel3(int areaLevel3) {
		this.areaLevel3 = areaLevel3;
	}

	public int getAreaLevel4() {
		return this.areaLevel4;
	}

	public void setAreaLevel4(int areaLevel4) {
		this.areaLevel4 = areaLevel4;
	}

	public int getAreaLevel5() {
		return this.areaLevel5;
	}

	public void setAreaLevel5(int areaLevel5) {
		this.areaLevel5 = areaLevel5;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(byte customerId) {
		this.customerId = customerId;
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

	public SimpleOrder getSimpleOrder() {
		return this.simpleOrder;
	}

	public void setSimpleOrder(SimpleOrder simpleOrder) {
		this.simpleOrder = simpleOrder;
	}

	public byte getHistorySequence() {
		return historySequence;
	}

	public void setHistorySequence(byte historySequence) {
		this.historySequence = historySequence;
	}

}