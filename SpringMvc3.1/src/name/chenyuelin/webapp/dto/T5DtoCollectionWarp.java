/**
 * 
 */
package name.chenyuelin.webapp.dto;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
@XmlRootElement(name="t5DtoCollection")
@XmlAccessorType(XmlAccessType.FIELD)
public class T5DtoCollectionWarp {
	@XmlElementWrapper(name="t5Dtos")
	private Collection<T5Dto> t5Dto;

	public Collection<T5Dto> getT5Dtos() {
		return t5Dto;
	}

	public void setT5Dtos(Collection<T5Dto> t5Dtos) {
		this.t5Dto = t5Dtos;
	}
}
