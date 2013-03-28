package name.chenyuelin.transformer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import name.chenyuelin.command.PersonCommand;
import name.chenyuelin.constants.BaseConstants;
import name.chenyuelin.dto.PersonDto;
import name.chenyuelin.entity.test.Person;

public final class PersonTransformer {
	
	@SuppressWarnings("deprecation")
	public static final PersonDto toPersonDto(Person entity){
		PersonDto dto=new PersonDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setActive(entity.getActive());
		dto.setBirthday(entity.getBirthday());
		dto.setBreakfastTime(entity.getBreakfastTime());
		
		Timestamp createTime=entity.getCreateTime();
		if(createTime!=null){
			XMLGregorianCalendar xmlGregorianCalendar=BaseConstants.DATATYPE_FACTORY.newXMLGregorianCalendar(createTime.getYear()+1900, createTime.getMonth()+1, createTime.getDate(), 
					createTime.getHours(), createTime.getMinutes(), createTime.getSeconds(),(int)(createTime.getTime()%1000), createTime.getTimezoneOffset());
			dto.setCreateTime(xmlGregorianCalendar);
		}
		
		BigDecimal heigth=entity.getHeight();
		dto.setHeight(heigth==null?0:heigth.doubleValue());
		dto.setLanguages(entity.getLanguages());
		dto.setNote(entity.getNote());
		dto.setSalary(entity.getSalary());
		dto.setSex(entity.getSex());
		dto.setVersion(entity.getVersion());
		return dto;
	}
	
	public static final List<PersonDto>toPersonDtoList(Collection<Person> entitys){
		List<PersonDto> dtoList=new ArrayList<PersonDto>();
		for(Person entity:entitys){
			dtoList.add(toPersonDto(entity));
		}
		return dtoList;
	}
	
	public static final void transformerCommandtoEntity(PersonCommand command,Person entity){
		entity.setActive(command.isActive());
		entity.setBirthday(command.getBirthday());
		entity.setBreakfastTime(command.getBreakfastTime());
		entity.setHeight(BigDecimal.valueOf(command.getHeight()));
		entity.setLanguages(command.getLanguages());
		entity.setName(command.getName());
		entity.setNote(command.getNote());
		entity.setSalary(command.getSalary());
		entity.setSex(command.getSex());
	}
}
