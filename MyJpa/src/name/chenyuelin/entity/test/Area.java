package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the areas database table.
 */
@Entity
@Table(name = "areas")
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3171636125366660238L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Boolean active;

	@Column(name = "area_code")
	private String areaCode;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "level_code")
	private String levelCode;

	@Lob()
	@Column(name = "t_comment")
	private String tComment;

	// bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Area area;

	// bi-directional many-to-one association to Area
	@OneToMany(mappedBy = "area")
	private List<Area> areas;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "area1")
	private List<CustomerAddress> customerAddresses1;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "area2")
	private List<CustomerAddress> customerAddresses2;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "area3")
	private List<CustomerAddress> customerAddresses3;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "area4")
	private List<CustomerAddress> customerAddresses4;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "area5")
	private List<CustomerAddress> customerAddresses5;

	// bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy = "area6")
	private List<CustomerAddress> customerAddresses6;

	/*// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "area1")
	private List<SimpleOrder> simpleOrders1;

	// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "area2")
	private List<SimpleOrder> simpleOrders2;

	// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "area3")
	private List<SimpleOrder> simpleOrders3;

	// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "area4")
	private List<SimpleOrder> simpleOrders4;

	// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "area5")
	private List<SimpleOrder> simpleOrders5;

	// bi-directional many-to-one association to SimpleOrder
	@OneToMany(mappedBy = "area6")
	private List<SimpleOrder> simpleOrders6;*/

	public Area() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getTComment() {
		return this.tComment;
	}

	public void setTComment(String tComment) {
		this.tComment = tComment;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<CustomerAddress> getCustomerAddresses1() {
		return this.customerAddresses1;
	}

	public void setCustomerAddresses1(List<CustomerAddress> customerAddresses1) {
		this.customerAddresses1 = customerAddresses1;
	}

	public List<CustomerAddress> getCustomerAddresses2() {
		return this.customerAddresses2;
	}

	public void setCustomerAddresses2(List<CustomerAddress> customerAddresses2) {
		this.customerAddresses2 = customerAddresses2;
	}

	public List<CustomerAddress> getCustomerAddresses3() {
		return this.customerAddresses3;
	}

	public void setCustomerAddresses3(List<CustomerAddress> customerAddresses3) {
		this.customerAddresses3 = customerAddresses3;
	}

	public List<CustomerAddress> getCustomerAddresses4() {
		return this.customerAddresses4;
	}

	public void setCustomerAddresses4(List<CustomerAddress> customerAddresses4) {
		this.customerAddresses4 = customerAddresses4;
	}

	public List<CustomerAddress> getCustomerAddresses5() {
		return this.customerAddresses5;
	}

	public void setCustomerAddresses5(List<CustomerAddress> customerAddresses5) {
		this.customerAddresses5 = customerAddresses5;
	}

	public List<CustomerAddress> getCustomerAddresses6() {
		return this.customerAddresses6;
	}

	public void setCustomerAddresses6(List<CustomerAddress> customerAddresses6) {
		this.customerAddresses6 = customerAddresses6;
	}

	/*public List<SimpleOrder> getSimpleOrders1() {
		return this.simpleOrders1;
	}

	public void setSimpleOrders1(List<SimpleOrder> simpleOrders1) {
		this.simpleOrders1 = simpleOrders1;
	}

	public List<SimpleOrder> getSimpleOrders2() {
		return this.simpleOrders2;
	}

	public void setSimpleOrders2(List<SimpleOrder> simpleOrders2) {
		this.simpleOrders2 = simpleOrders2;
	}

	public List<SimpleOrder> getSimpleOrders3() {
		return this.simpleOrders3;
	}

	public void setSimpleOrders3(List<SimpleOrder> simpleOrders3) {
		this.simpleOrders3 = simpleOrders3;
	}

	public List<SimpleOrder> getSimpleOrders4() {
		return this.simpleOrders4;
	}

	public void setSimpleOrders4(List<SimpleOrder> simpleOrders4) {
		this.simpleOrders4 = simpleOrders4;
	}

	public List<SimpleOrder> getSimpleOrders5() {
		return this.simpleOrders5;
	}

	public void setSimpleOrders5(List<SimpleOrder> simpleOrders5) {
		this.simpleOrders5 = simpleOrders5;
	}

	public List<SimpleOrder> getSimpleOrders6() {
		return this.simpleOrders6;
	}

	public void setSimpleOrders6(List<SimpleOrder> simpleOrders6) {
		this.simpleOrders6 = simpleOrders6;
	}*/

}