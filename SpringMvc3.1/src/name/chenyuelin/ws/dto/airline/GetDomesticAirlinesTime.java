package name.chenyuelin.ws.dto.airline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import name.chenyuelin.ws.WSConstant;

@XmlRootElement(name = "getDomesticAirlinesTime", namespace = WSConstant.AIR_LINE_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "startCity", "lastCity", "theDate" })
public class GetDomesticAirlinesTime {

	@XmlElement(namespace = WSConstant.AIR_LINE_NAMESPACE, required = true)
	private String startCity;

	@XmlElement(namespace = WSConstant.AIR_LINE_NAMESPACE, required = true)
	private String lastCity;

	@XmlElement(namespace = WSConstant.AIR_LINE_NAMESPACE, required = true)
	private String theDate;

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getLastCity() {
		return lastCity;
	}

	public void setLastCity(String lastCity) {
		this.lastCity = lastCity;
	}

	public String getTheDate() {
		return theDate;
	}

	public void setTheDate(String theDate) {
		this.theDate = theDate;
	}

	@Override
	public String toString() {
		return "GetDomesticAirlinesTime [startCity=" + startCity + ", lastCity=" + lastCity + ", theDate=" + theDate + "]";
	}
}
