package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the simple_order_history database table.
 */
@Embeddable
public class SimpleOrderHistoryPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1267963530177491760L;

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "history_sequence")
	private byte historySequence;

	public SimpleOrderHistoryPK() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public byte getHistorySequence() {
		return this.historySequence;
	}

	public void setHistorySequence(byte historySequence) {
		this.historySequence = historySequence;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SimpleOrderHistoryPK)) {
			return false;
		}
		SimpleOrderHistoryPK castOther = (SimpleOrderHistoryPK) other;
		return (this.orderId == castOther.orderId) && (this.historySequence == castOther.historySequence);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId;
		hash = hash * prime + (this.historySequence);

		return hash;
	}
}