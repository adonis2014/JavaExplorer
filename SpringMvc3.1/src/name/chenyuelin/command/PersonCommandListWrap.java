/**
 * 
 */
package name.chenyuelin.command;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author U1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCommandListWrap {
	private Collection<PersonCommand> personCommands;

	public Collection<PersonCommand> getPersonCommands() {
		return personCommands;
	}

	public void setPersonCommands(Collection<PersonCommand> personCommands) {
		this.personCommands = personCommands;
	}
	
	public String toString(){
        return personCommands.toString();
    }
}
