package name.chenyuelin.security.acl;

import name.chenyuelin.entity.test.Person;

import org.aspectj.lang.JoinPoint;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;

public class JpaPersonDaoAdvice {
	private MutableAclService mutableAclService;

	public void afterAddPerson(JoinPoint joinPoint, Person person) {
		ObjectIdentity oid = new ObjectIdentityImpl(Person.class, person.getId());
		MutableAcl acl = mutableAclService.createAcl(oid);
		System.out.println(acl);
		// acl.insertAce(atIndexLocation, permission, sid, granting)
	}

	public void afterDeletePerson(JoinPoint joinPoint, byte id) {
		ObjectIdentity oid = new ObjectIdentityImpl(Person.class, 1);
		mutableAclService.deleteAcl(oid, false);
	}

	public void setMutableAclService(MutableAclService mutableAclService) {
		this.mutableAclService = mutableAclService;
	}

}
