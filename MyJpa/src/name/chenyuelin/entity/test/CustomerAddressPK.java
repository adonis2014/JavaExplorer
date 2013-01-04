package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the customer_address database table.
 */
@Embeddable
public class CustomerAddressPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4263510130147539350L;

	@Column(name = "customer_id")
	private byte customerId;

	@Column(name = "sub_id")
	private byte subId;

	public CustomerAddressPK() {
	}

	public byte getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(byte customerId) {
		this.customerId = customerId;
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
		return (this.customerId == castOther.customerId) && (this.subId == castOther.subId);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + (this.customerId);
		hash = hash * prime + (this.subId);

		return hash;
	}
}