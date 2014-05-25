package name.chenyuelin.webapp.controller;

import java.util.List;

import name.chenyuelin.service.AirLineService;
import name.chenyuelin.ws.dto.airline.GetDomesticAirlinesTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("airline")
public class AirLineController {
	@Autowired
	private AirLineService service;
	
	@RequestMapping(value = "domesticcity", method = RequestMethod.GET)
	@ResponseBody
	public List<?> getDomesticCity() throws Exception {
		return service.getDomesticCity();
	}
	
	@RequestMapping(value = "domestictime", method = RequestMethod.GET)
	@ResponseBody
	public List<?> getDomesticAirlinesTime(GetDomesticAirlinesTime command) throws Exception {
		return service.getDomesticAirlinesTime(command);
	}
}
