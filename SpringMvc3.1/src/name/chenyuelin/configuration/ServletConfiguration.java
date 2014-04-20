/* * @(#)
		$Id: servletConfiguration.java, peter.chen Exp $
		*****************************************************************************
		* Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
		*
		*****************************************************************************
		*/
package name.chenyuelin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: servletConfiguration
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-22 ÏÂÎç4:08:50
 * 
 */
@Component
@Configuration
public class ServletConfiguration {
    
    private Environment environment;  
  
    @Bean(name="globalObjectMapper2")
    public ObjectMapper createGlobalObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        /*objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.getSerializationConfig().setDateFormat(myDateFormat);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        objectMapper.setDateFormat(myDateFormat); // 1.8 and above
        objectMapper.getSerializationConfig().setDateFormat(myDateFormat); // for earlier versions (deprecated for 1.8+)*/
        return objectMapper;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
