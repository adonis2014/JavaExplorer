package name.chenyuelin.entity.test;

import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, Byte> id;
	public static volatile SingularAttribute<Customer, Date> birthday;
	public static volatile SingularAttribute<Customer, Boolean> active;
	public static volatile SingularAttribute<Customer, Timestamp> createTime;
	
	public static volatile SingularAttribute<Customer, String> comment;
	public static volatile SingularAttribute<Customer, byte[]> customData;
	public static volatile SingularAttribute<Customer, Byte> defaultAddress;
	public static volatile SingularAttribute<Customer, String> email;
	
	public static volatile SingularAttribute<Customer, String> mobilePhoneNum;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SingularAttribute<Customer, String> sex;
	
	public static volatile ListAttribute<Customer, CustomerAddress> customerAddresses;
	public static volatile ListAttribute<Customer, SimpleOrder> simpleOrders;
}
