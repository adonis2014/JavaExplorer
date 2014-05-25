package name.chenyuelin.ws.dto.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import name.chenyuelin.ws.WSConstant;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "findPersonRequest", namespace = WSConstant.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCommand {
	@XmlElement(namespace=WSConstant.NAMESPACE)
	private int id;
	
	@XmlElement(namespace=WSConstant.NAMESPACE)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
