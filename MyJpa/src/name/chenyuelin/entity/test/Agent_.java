package name.chenyuelin.entity.test;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink JPA 2.0 Canonical Model Generation")
@StaticMetamodel(Agent.class)
public class Agent_ {
	public static volatile SingularAttribute<Agent, Byte> id;
	public static volatile SingularAttribute<Agent, String> name;
	public static volatile ListAttribute<Agent, Hotel> hotels;
}
