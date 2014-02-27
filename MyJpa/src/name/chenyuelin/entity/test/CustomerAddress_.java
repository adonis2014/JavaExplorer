package name.chenyuelin.entity.test;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(CustomerAddress.class)
public class CustomerAddress_ {
	public static volatile SingularAttribute<CustomerAddress, Byte> subId;
	public static volatile SingularAttribute<CustomerAddress, Customer> customer;
	public static volatile SingularAttribute<CustomerAddress, Boolean> active;
	public static volatile SingularAttribute<CustomerAddress, Timestamp> createTime;
	
	public static volatile SingularAttribute<CustomerAddress, String> phone;
	public static volatile SingularAttribute<CustomerAddress, String> street;
	public static volatile SingularAttribute<CustomerAddress, Timestamp> updateTime;
	public static volatile SingularAttribute<CustomerAddress, Area> area1;
	
	public static volatile SingularAttribute<CustomerAddress, Area> area2;
	public static volatile SingularAttribute<CustomerAddress, Area> area3;
	public static volatile SingularAttribute<CustomerAddress, Area> area4;
	public static volatile SingularAttribute<CustomerAddress, Area> area5;
	
	public static volatile SingularAttribute<CustomerAddress, Area> area6;
}
