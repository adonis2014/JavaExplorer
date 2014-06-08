package name.chenyuelin.service;

import java.util.List;

import name.chenyuelin.dao.SsRoleDao;
import name.chenyuelin.entity.test.SsRole;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SsRoleService {
	public static final Log LOG = LogFactory.getLog(SsRoleService.class);

	@Autowired
	private SsRoleDao dao;

	public List<SsRole> getAllSsRole() {
		return dao.getAllSsRole();
	}

}
