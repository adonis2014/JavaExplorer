/**
 * 
 */
package name.chenyuelin.dto;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author P1
 * @version 1.0 Jan 20, 2013
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AjaxBindingErrorMessages {
	private Collection<AjaxBindingErrorMessage> errorMessage;

	public Collection<AjaxBindingErrorMessage> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Collection<AjaxBindingErrorMessage> errorMessage) {
		this.errorMessage = errorMessage;
	}
}
