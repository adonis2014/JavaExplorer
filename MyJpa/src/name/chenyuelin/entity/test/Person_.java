package name.chenyuelin.entity.test;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import name.chenyuelin.enums.Language;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Person.class)
public class Person_ {
	public static volatile SingularAttribute<Person, Byte> id;
	public static volatile SingularAttribute<Person, Boolean> active;
	public static volatile SingularAttribute<Person, Date> birthday;
	public static volatile SingularAttribute<Person, Time> breakfastTime;
	
	public static volatile SingularAttribute<Person, Timestamp> createTime;
	public static volatile SingularAttribute<Person, byte[]> customData;
	public static volatile SingularAttribute<Person, BigDecimal> height;
	public static volatile SingularAttribute<Person, Language> languages;
	
	public static volatile SingularAttribute<Person, Language> languages2;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, String> note;
	public static volatile SingularAttribute<Person, Integer> salary;
}
