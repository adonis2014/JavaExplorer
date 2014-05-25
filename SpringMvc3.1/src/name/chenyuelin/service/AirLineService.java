package name.chenyuelin.service;

import java.util.List;

import name.chenyuelin.ws.client.airline.AirLineClientInvoker;
import name.chenyuelin.ws.dto.airline.GetDomesticAirlinesTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class AirLineService {
	public static final Log LOG = LogFactory.getLog(AirLineService.class);

	private AirLineClientInvoker airLineClientInvoker;

	public List<?> getDomesticCity() {
		return airLineClientInvoker.getDomesticCity();
	}

	public List<?> getDomesticAirlinesTime(GetDomesticAirlinesTime command) {
		return airLineClientInvoker.getDomesticAirlinesTime(command);
	}
	
	public void setAirLineClientInvoker(AirLineClientInvoker airLineClientInvoker) {
		this.airLineClientInvoker = airLineClientInvoker;
	}
}
