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

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import name.chenyuelin.entity.test.Area;
import name.chenyuelin.entity.test.CustomerAddress;

/**
 * @ClassName: CustomerTransformer
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-4 ÏÂÎç6:21:34
 * 
 */
@Component
public class CustomerTransformer {
    public Map<String, Object> transformerCustomerAddressToMap(CustomerAddress customerAddress){
        Map<String, Object> dto=new HashMap<String, Object>();
        dto.put("customerName", customerAddress.getCustomer().getName());
        dto.put("phone", customerAddress.getPhone());
        dto.put("street", customerAddress.getStreet());
        Area area=customerAddress.getArea1();
        dto.put("area1", area==null?area:area.getAreaName());
        area=customerAddress.getArea2();
        dto.put("area2", area==null?area:area.getAreaName());
        area=customerAddress.getArea3();
        dto.put("area3", area==null?area:area.getAreaName());
        area=customerAddress.getArea4();
        dto.put("area4", area==null?area:area.getAreaName());
        area=customerAddress.getArea5();
        dto.put("area5", area==null?area:area.getAreaName());
        return dto;
    }
}
