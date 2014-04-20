/**
 * 
 */
package name.chenyuelin.webapp.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import name.chenyuelin.constants.BaseConstants;
import name.chenyuelin.entity.test.T5;
import name.chenyuelin.webapp.dto.T5Dto;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
public class TTransformer {
	
	public static T5Dto T5EntityToT5Dto(T5 entity){
		T5Dto dto=new T5Dto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setT2Name(entity.getT2().getName());
			Date date=entity.getT2().getDate();
			@SuppressWarnings("deprecation")
			XMLGregorianCalendar xmlDate=BaseConstants.DATATYPE_FACTORY.newXMLGregorianCalendarDate(date.getYear()+1900, date.getMonth()+1, date.getDate(), DatatypeConstants.FIELD_UNDEFINED);
			dto.setT2Date(xmlDate);
		}
		return dto;
	}
	
	public static Collection<T5Dto> T5EntityCollectionToT5DtoCollection(Collection<T5> entitys){
		Collection<T5Dto> collection=new ArrayList<T5Dto>();
		if(entitys!=null){
			for(T5 entity:entitys){
				collection.add(T5EntityToT5Dto(entity));
			}
		}
		return collection;
	}
}
