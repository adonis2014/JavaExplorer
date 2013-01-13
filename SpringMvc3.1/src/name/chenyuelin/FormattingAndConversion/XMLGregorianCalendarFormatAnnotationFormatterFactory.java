package name.chenyuelin.FormattingAndConversion;

import static name.chenyuelin.FormattingAndConversion.XMLGregorianCalendarTypeEnum.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 * @author P1
 * @version 1.0 Jan 12, 2013
 */
public class XMLGregorianCalendarFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<XmlSchemaType> {
	private Set<Class<?>> fieldTypes=new HashSet<Class<?>>();
	private Map<String, Formatter<XMLGregorianCalendar>> formatMap=new HashMap<String, Formatter<XMLGregorianCalendar>>();
	
	public XMLGregorianCalendarFormatAnnotationFormatterFactory(){
		fieldTypes.add(XMLGregorianCalendar.class);
		formatMap.put(DATE.toString(), new XMLGregorianCalendarFormatter(DATE));
		formatMap.put(TIME.toString(), new XMLGregorianCalendarFormatter(TIME));
		formatMap.put(DATE_TIME.toString(), new XMLGregorianCalendarFormatter(DATE_TIME));
	}
	
	@Override
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}

	@Override
	public Printer<?> getPrinter(XmlSchemaType annotation, Class<?> fieldType) {
		return formatMap.get(annotation.name());
	}

	@Override
	public Parser<?> getParser(XmlSchemaType annotation, Class<?> fieldType) {
		return formatMap.get(annotation.name());
	}



}
