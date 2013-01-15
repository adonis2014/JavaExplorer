package name.chenyuelin.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerAddressDto {
	private String customerName;
	private String phone;
	private String street;
	private String area1;
	private String area2;
	private String area3;
	private String area4;
	private String area5;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea1() {
		return area1;
	}
	public void setArea1(String area1) {
		this.area1 = area1;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getArea3() {
		return area3;
	}
	public void setArea3(String area3) {
		this.area3 = area3;
	}
	public String getArea4() {
		return area4;
	}
	public void setArea4(String area4) {
		this.area4 = area4;
	}
	public String getArea5() {
		return area5;
	}
	public void setArea5(String area5) {
		this.area5 = area5;
	}
	
	public String toString(){
	    StringBuilder sb=new StringBuilder(50);
	    sb.append("{customerName:").append(customerName).append(", ");
	    sb.append("phone:").append(phone).append(", ");
	    sb.append("street:").append(street).append(", ");
	    sb.append("area1:").append(area1).append(", ");
	    sb.append("area2:").append(area2).append(", ");
	    sb.append("area3:").append(area3).append(", ");
	    sb.append("area4:").append(area4).append(", ");
	    sb.append("area5:").append(area5).append("}");
	    return sb.toString();
	}
}
