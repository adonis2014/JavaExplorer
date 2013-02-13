package name.chenyuelin.dto;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDtoListWrap {
	@XmlElementWrapper(name="persons")
	@JsonProperty("persons")
	private Collection<PersonDto> person;

	public Collection<PersonDto> getPerson() {
		return person;
	}

	public void setPerson(Collection<PersonDto> person) {
		this.person = person;
	}
}
