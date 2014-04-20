package name.chenyuelin.webapp.dto;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionStatus {
	private Map<String, Object> returnData;
	private boolean processSuccessfully;
	
	public boolean isProcessSuccessfully() {
		return processSuccessfully;
	}
	public void setProcessSuccessfully(boolean processSuccessfully) {
		this.processSuccessfully = processSuccessfully;
	}
	public Map<String, Object> getReturnData() {
		return returnData;
	}
	public void setReturnData(Map<String, Object> returnData) {
		this.returnData = returnData;
	}
	
	
}
