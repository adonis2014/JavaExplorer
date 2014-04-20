/* * @(#)
		$Id: Jaxb2SqlTimeAdapter.java, peter.chen Exp $
		*****************************************************************************
		* Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
		*
		*****************************************************************************
		*/
package name.chenyuelin.webapp.transformer;

import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @ClassName: Jaxb2SqlTimeAdapter
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-7 ÏÂÎç3:13:16
 * 
 */
public class Jaxb2SqlTimeAdapter extends XmlAdapter<String, Time> {

    @Override
    public Time unmarshal(String v) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return new Time(format.parse(v).getTime());
    }

    @Override
    public String marshal(Time v) throws Exception {
        return v.toString();
    }

}
