package name.chenyuelin.dto;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionStatusListWarp {
	private Collection<ActionStatus> actionStatus;

	public Collection<ActionStatus> getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(Collection<ActionStatus> actionStatus) {
		this.actionStatus = actionStatus;
	}
}
