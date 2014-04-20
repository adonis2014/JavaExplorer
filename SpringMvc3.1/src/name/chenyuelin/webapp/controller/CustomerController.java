/* * @(#)
		$Id: CustomerController.java, peter.chen Exp $
		*****************************************************************************
		* Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
		*
		*****************************************************************************
		*/
package name.chenyuelin.webapp.controller;

import name.chenyuelin.entity.test.CustomerAddress;
import name.chenyuelin.service.UserService;
import name.chenyuelin.webapp.dto.CustomerAddressDto;
import name.chenyuelin.webapp.transformer.CustomerTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: CustomerController
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-4 ÏÂÎç5:16:42
 * 
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    public static final Log LOG=LogFactory.getLog(CustomerController.class);
    
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
	@Transactional
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public void getCustomer(@PathVariable("id")int id){
        System.out.println(userService.findCustomer(id));
    }
    
    @Transactional
    @RequestMapping(value="address/{customerId}/{subId}",method=RequestMethod.GET)
    public ModelAndView getCustomerAddress(@PathVariable("customerId")int customerId,@PathVariable("subId")int subId){
        CustomerAddress customerAddress=userService.findCustomerAddress(customerId, subId);
        System.out.println(customerAddress.getCustomer().getSimpleOrders().size());
        CustomerAddressDto dto=CustomerTransformer.transformerCustomerAddressToMap(customerAddress);
        ModelAndView mav=new ModelAndView("root","CustomerAddressDto",dto);
        return mav;
    }
}
