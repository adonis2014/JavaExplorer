/**
 * 
 */
package name.chenyuelin.service;

import java.util.List;

import name.chenyuelin.dao.T5Dao;
import name.chenyuelin.entity.test.T5;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
@Service
public class TService {
	public static final Log LOG=LogFactory.getLog(TService.class);
	
	private T5Dao t5Dao;

	@Autowired
	public void setT5Dao(T5Dao t5Dao) {
		this.t5Dao = t5Dao;
	}
	
	public List<T5> listT5byT2Name(String t2Name){
		return t5Dao.listT5byT2Name(t2Name);
	}
}
