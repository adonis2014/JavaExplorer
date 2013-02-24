/**
 * 
 */
package name.chenyuelin.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class T5Dto {
	private byte id;
	private String name;
	private String t2Name;
	
	@XmlSchemaType(name="date")
    private XMLGregorianCalendar t2Date;
	
	public byte getId() {
		return id;
	}
	public void setId(byte id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getT2Name() {
		return t2Name;
	}
	public void setT2Name(String t2Name) {
		this.t2Name = t2Name;
	}
	public XMLGregorianCalendar getT2Date() {
		return t2Date;
	}
	public void setT2Date(XMLGregorianCalendar t2Date) {
		this.t2Date = t2Date;
	}
}
