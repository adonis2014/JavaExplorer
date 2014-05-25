package name.chenyuelin.ws.client.airline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXResult;

import name.chenyuelin.common.BaseUtil;
import name.chenyuelin.common.MapSaxHandlerImp;
import name.chenyuelin.service.AirLineService;
import name.chenyuelin.ws.dto.airline.GetDomesticAirlinesTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.support.MarshallingSource;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.xml.transform.StringSource;

@Component
public class AirLineClientInvoker {
	private static String url = "http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx";

	@Autowired
	private AirLineService airLineService;

	@Autowired
	@Qualifier("webServiceTemplate11")
	private WebServiceTemplate webServiceTemplate11;

	@Autowired
	@Qualifier("webServiceTemplate12")
	private WebServiceTemplate webServiceTemplate12;

	@Autowired
	@Qualifier("wsJaxb2Marshaller")
	private Jaxb2Marshaller wsJaxb2Marshaller;

	@PostConstruct
	public void init() {
		airLineService.setAirLineClientInvoker(this);
	}

	public List<?> getDomesticAirlinesTime(GetDomesticAirlinesTime command) {
		Map<String, Object> contentMap = new HashMap<String, Object>();
		Result result = new SAXResult(new MapSaxHandlerImp(contentMap));
		Source source = new MarshallingSource(wsJaxb2Marshaller, command);
		webServiceTemplate11.sendSourceAndReceiveToResult(url, source, new SoapActionCallback("http://WebXml.com.cn/getDomesticAirlinesTime"), result);

		Object returnData = BaseUtil.getMapData(contentMap, "getDomesticAirlinesTimeResult", "diffgr:diffgram", "Airlines", "AirlinesTime");
		if (returnData instanceof Map) {
			List<Object> list = new ArrayList<Object>();
			list.add(returnData);
			returnData = list;
		}
		return (List<?>) returnData;
	}

	public List<?> getDomesticCity() {
		String content = "<getDomesticCity xmlns=\"http://WebXml.com.cn/\"></getDomesticCity>";
		Source source = new StringSource(content);
		Map<String, Object> contentMap = new HashMap<String, Object>();
		Result result = new SAXResult(new MapSaxHandlerImp(contentMap));
		webServiceTemplate11.sendSourceAndReceiveToResult(url, source, new SoapActionCallback("http://WebXml.com.cn/getDomesticCity"), result);
		Object returnData = BaseUtil.getMapData(contentMap, "getDomesticCityResult", "diffgr:diffgram", "Airline1", "Address");
		if (returnData instanceof Map) {
			List<Object> list = new ArrayList<Object>();
			list.add(returnData);
			returnData = list;
		}
		return (List<?>) returnData;
	}
}
