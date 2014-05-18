/**
 * 
 */
package test.name.chenyuelin.common;

import java.util.List;
import java.util.Map;

import name.chenyuelin.common.XMLUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yuelin Chen
 * @version 1.0 2014-5-18
 */
public class XMLUtilTestCase {

	@Test
	public void testReadToMap() throws Exception {
		Map<String, Object> data = XMLUtil.readToMap(this.getClass().getResourceAsStream("TestFile.xml"));
		Assert.assertEquals(2, data.size());

		Assert.assertTrue(data.containsKey("tichead"));
		// Test tichead
		Map<?, ?> tichead = (Map<?, ?>) data.get("tichead");
		Assert.assertEquals(10, tichead.size());

		Assert.assertTrue(tichead.containsKey("ssid"));
		List<?> ssid = (List<?>) tichead.get("ssid");
		Assert.assertEquals(3, ssid.size());
		Assert.assertEquals("Ssid", ssid.get(0));
		Assert.assertEquals("Ssid2", ssid.get(1));
		Assert.assertEquals("Ssid3", ssid.get(2));

		Assert.assertTrue(tichead.containsKey("subsid"));
		Assert.assertEquals("Subsid", tichead.get("subsid"));

		Assert.assertTrue(tichead.containsKey("pid"));
		Assert.assertEquals("1", tichead.get("pid"));

		Assert.assertTrue(tichead.containsKey("usertype"));
		List<?> usertype = (List<?>) tichead.get("usertype");
		Assert.assertEquals("0", usertype.get(0));
		Assert.assertEquals("1", usertype.get(1));

		Assert.assertTrue(tichead.containsKey("tictype"));
		Assert.assertEquals("0", tichead.get("tictype"));

		Assert.assertTrue(tichead.containsKey("prior"));
		Assert.assertEquals("1", tichead.get("prior"));

		Assert.assertTrue(tichead.containsKey("area"));
		Assert.assertEquals("Area", tichead.get("area"));

		Assert.assertTrue(tichead.containsKey("dn"));
		Assert.assertEquals("Dn", tichead.get("dn"));

		Assert.assertTrue(tichead.containsKey("officecode"));
		Assert.assertEquals("0", tichead.get("officecode"));

		Assert.assertTrue(tichead.containsKey("otime"));
		Assert.assertEquals("Otime", tichead.get("otime"));

		// Test ticbody
		Assert.assertTrue(data.containsKey("ticbody"));
		Map<?, ?> ticbody = (Map<?, ?>) data.get("ticbody");
		Assert.assertEquals(2, ticbody.size());

		Assert.assertEquals("Basesvcid", ticbody.get("basesvcid"));

		List<?> subsvcs = (List<?>) ticbody.get("subsvcs");
		Assert.assertEquals(2, subsvcs.size());

		Map<?, ?> subsvcs1 = (Map<?, ?>) subsvcs.get(0);
		Assert.assertEquals(3, subsvcs1.size());

		List<?> subsvcids = (List<?>) subsvcs1.get("subsvcid");
		Assert.assertEquals(2, subsvcids.size());
		Assert.assertEquals("Subsvcid", subsvcids.get(0));
		Assert.assertEquals("Subsvcid", subsvcids.get(1));

		Assert.assertEquals("Oper", subsvcs1.get("oper"));

		List<?> svcparas = (List<?>) subsvcs1.get("svcpara");
		Assert.assertEquals(3, svcparas.size());
		Map<?, ?> svcpara = (Map<?, ?>) svcparas.get(0);
		Assert.assertEquals("Parament", svcpara.get("parament"));
		Assert.assertEquals("Value", svcpara.get("value"));
		svcpara = (Map<?, ?>) svcparas.get(1);
		Assert.assertEquals("Paramen1t", svcpara.get("parament"));
		Assert.assertEquals("Value1", svcpara.get("value"));
		svcpara = (Map<?, ?>) svcparas.get(2);
		Assert.assertEquals("Parament2", svcpara.get("parament"));
		Assert.assertEquals("Value2", svcpara.get("value"));

		Map<?, ?> subsvcs2 = (Map<?, ?>) subsvcs.get(1);
		Assert.assertEquals(3, subsvcs1.size());
		Assert.assertEquals("Subsvcid2", subsvcs2.get("subsvcid"));
		Assert.assertEquals("Oper2", subsvcs2.get("oper"));
		Map<?, ?> svcparasMap = (Map<?, ?>) subsvcs2.get("svcpara");
		Assert.assertEquals("Parament2", svcparasMap.get("parament"));
		Assert.assertEquals("Value2", svcparasMap.get("value"));

	}
}
