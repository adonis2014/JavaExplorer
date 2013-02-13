package name.chenyuelin.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionStatus {
	private int id;
	private boolean processSuccessfully;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isProcessSuccessfully() {
		return processSuccessfully;
	}
	public void setProcessSuccessfully(boolean processSuccessfully) {
		this.processSuccessfully = processSuccessfully;
	}
}
