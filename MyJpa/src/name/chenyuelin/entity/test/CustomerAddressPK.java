package name.chenyuelin.entity.test;

import java.io.Serializable;

/**
 * The primary key class for the customer_address database table.
 */
public class CustomerAddressPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4263510130147539350L;

	private static final int HASH = (int)((Integer.MIN_VALUE>>16)+Math.random()*(((long)Integer.MAX_VALUE-(long)Integer.MIN_VALUE)>>16));

	private static final int PRIME = 2;
	
	private byte customer;

	private byte subId;

	public CustomerAddressPK() {
	}

	public CustomerAddressPK(byte customerId,byte subId){
	    customer=customerId;
	    this.subId=subId;
	}
	
	public byte getCustomerId() {
		return this.customer;
	}

	public void setCustomerId(byte customerId) {
		this.customer = customerId;
	}

	public byte getSubId() {
		return this.subId;
	}

	public void setSubId(byte subId) {
		this.subId = subId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomerAddressPK)) {
			return false;
		}
		CustomerAddressPK castOther = (CustomerAddressPK) other;
		return (this.customer == castOther.customer) && (this.subId == castOther.subId);

	}

	@Override
	public int hashCode() {
		int hash = HASH << PRIME + (this.customer);
		hash = hash << PRIME + (this.subId);
		return hash;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		sb.append("{customerId:").append(customer);
		sb.append(",subId:").append(subId).append("}");
		return sb.toString();
	}
	
}