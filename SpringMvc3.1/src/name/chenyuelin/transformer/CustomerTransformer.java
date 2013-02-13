/* * @(#)
		$Id: CustomerTransformer.java, peter.chen Exp $
		*****************************************************************************
		* Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
		*
		*****************************************************************************
		*/
package name.chenyuelin.transformer;

import name.chenyuelin.dto.CustomerAddressDto;
import name.chenyuelin.entity.test.Area;
import name.chenyuelin.entity.test.CustomerAddress;

/**
 * @ClassName: CustomerTransformer
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-4 ÏÂÎç6:21:34
 * 
 */
public final class CustomerTransformer {
    public static final CustomerAddressDto transformerCustomerAddressToMap(CustomerAddress customerAddress){
        CustomerAddressDto dto=new CustomerAddressDto();
        dto.setCustomerName(customerAddress.getCustomer().getName());
        dto.setPhone(customerAddress.getPhone());
        dto.setStreet(customerAddress.getStreet());
        Area area=customerAddress.getArea1();
        if(area!=null){
        	dto.setArea1(area.getAreaName());
        }
        area=customerAddress.getArea2();
        if(area!=null){
        	dto.setArea2(area.getAreaName());
        }
        area=customerAddress.getArea3();
        if(area!=null){
        	dto.setArea3(area.getAreaName());
        }
        area=customerAddress.getArea4();
        if(area!=null){
        	dto.setArea4(area.getAreaName());
        }
        area=customerAddress.getArea5();
        if(area!=null){
        	dto.setArea5(area.getAreaName());
        }
        return dto;
    }
}
