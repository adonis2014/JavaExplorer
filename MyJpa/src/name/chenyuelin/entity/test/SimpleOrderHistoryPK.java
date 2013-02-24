package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the simple_order_history database table.
 */
public class SimpleOrderHistoryPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1267963530177491760L;

	private static final int HASH = (int)((Integer.MIN_VALUE>>16)+Math.random()*(((long)Integer.MAX_VALUE-(long)Integer.MIN_VALUE)>>16));

	private static final int PRIME = 2;
	
	@Column(name = "order_id")
	private int simpleOrder;

	@Column(name = "history_sequence")
	private byte historySequence;

	public SimpleOrderHistoryPK() {
	}

	public SimpleOrderHistoryPK(int simpleOrder, byte historySequence) {
		this.simpleOrder = simpleOrder;
		this.historySequence = historySequence;
	}
	
	public int getOrderId() {
		return this.simpleOrder;
	}

	public byte getHistorySequence() {
		return this.historySequence;
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
		return (this.simpleOrder == castOther.simpleOrder) && (this.historySequence == castOther.historySequence);

	}

	@Override
	public int hashCode() {
		int hash = HASH << PRIME + (this.simpleOrder);
		hash = hash << PRIME + (this.historySequence);
		return hash;
	}
}