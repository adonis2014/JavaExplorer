/* * @(#)
		$Id: JpaCustomerAddressDao.java, peter.chen Exp $
		*****************************************************************************
		* Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
		*
		*****************************************************************************
		*/
package name.chenyuelin.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.chenyuelin.entity.test.CustomerAddress;
import name.chenyuelin.entity.test.CustomerAddressPK;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: JpaCustomerAddressDao
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-4 ÏÂÎç5:20:57
 * 
 */
@Repository
public class JpaCustomerAddressDao {
    public static final Log LOG=LogFactory.getLog(JpaCustomerAddressDao.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public CustomerAddress findCustomerAddressById(CustomerAddressPK pk){
    	System.out.println(pk.hashCode());
        return entityManager.find(CustomerAddress.class, pk);
    }
}
