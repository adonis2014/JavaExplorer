package name.chenyuelin.FormattingAndConversion;

import java.text.ParseException;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.format.Formatter;

/**
 * @author CYL
 * @version 1.0 Jan 12, 2013
 */
public class XMLGregorianCalendarFormatter implements Formatter<XMLGregorianCalendar> {
	private static final DatatypeFactory DATATYPE_FACTORY = getDatatypeFactoryInstance();

	private final XMLGregorianCalendarTypeEnum type;
		
	public XMLGregorianCalendarFormatter(XMLGregorianCalendarTypeEnum type){
		this.type=type;
	}
	
	@Override
	public XMLGregorianCalendar parse(String text, Locale locale) throws ParseException {
		XMLGregorianCalendar xmlGregorianCalendar=DATATYPE_FACTORY.newXMLGregorianCalendar(text);
		String localPart=xmlGregorianCalendar.getXMLSchemaType().getLocalPart();
		if(!localPart.equals(type.toString())){
			StringBuilder exceptionInfo=new StringBuilder();
			exceptionInfo.append(text).append(" can not conver to ").append(localPart).append(". ");
			throw new ParseException(exceptionInfo.toString(),0);
		}
		return xmlGregorianCalendar;
	}

	@Override
	public String print(XMLGregorianCalendar object, Locale locale) {
		return object.toXMLFormat();
	}

	private static DatatypeFactory getDatatypeFactoryInstance() {
		try {
			return DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
}
