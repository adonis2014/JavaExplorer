/* * @(#)
		$Id: Jaxb2SqlDataAdapter.java, peter.chen Exp $
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

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @ClassName: Jaxb2SqlDataAdapter
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-7 ÏÂÎç2:30:10
 * 
 */
public class Jaxb2SqlDataAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return new Date(format.parse(v).getTime());
    }

    @Override
    public String marshal(Date v) throws Exception {
        return v.toString();
    }

}
