/**
 * 
 */
package name.chenyuelin.entity.test;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(T2.class)
public class T2_ {
	public static volatile SingularAttribute<T2, Byte> id;
	public static volatile SingularAttribute<T2, Date> date;
	public static volatile SingularAttribute<T2, String> name;
	public static volatile ListAttribute<T2, T5> t5s;
}
