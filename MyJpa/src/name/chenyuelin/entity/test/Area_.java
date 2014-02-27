package name.chenyuelin.entity.test;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Area.class)
public class Area_ {
	public static volatile SingularAttribute<Area, Integer> id;
	public static volatile SingularAttribute<Area, Boolean> active;
	
	public static volatile SingularAttribute<Area, String> areaCode;
	public static volatile SingularAttribute<Area, String> areaName;
	public static volatile SingularAttribute<Area, String> levelCode;
	public static volatile SingularAttribute<Area, String> tComment;
	
	public static volatile SingularAttribute<Area, Area> area;
	public static volatile ListAttribute<Area, Area> areas;
	public static volatile ListAttribute<Area, CustomerAddress> customerAddresses1;
	public static volatile ListAttribute<Area, CustomerAddress> customerAddresses2;
	public static volatile ListAttribute<Area, CustomerAddress> customerAddresses3;
	public static volatile ListAttribute<Area, CustomerAddress> customerAddresses4;
	public static volatile ListAttribute<Area, CustomerAddress> customerAddresses5;
	public static volatile ListAttribute<Area, CustomerAddress> customerAddresses6;
}
