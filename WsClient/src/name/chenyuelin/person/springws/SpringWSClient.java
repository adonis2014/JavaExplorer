package name.chenyuelin.person.springws;

import name.chenyuelin.person.springws.dto.PersonCommand;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

public class SpringWSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("name/chenyuelin/person/springws/spring-ws-client.xml");
		WebServiceTemplate webServiceTemplate=ac.getBean("defalutWebServiceTemplate", WebServiceTemplate.class);
		
		PersonCommand personCommand =new PersonCommand();
		personCommand.setId((byte)1);
		
		Object result=webServiceTemplate.marshalSendAndReceive(personCommand);
		System.out.println(result);
		
		/*Source source=new StringSource("<findPersonRequest xmlns=\"http://www.cyl.org/person/schemas/\"><id>1</id></findPersonRequest>");
		Result result=new StringResult();
		webServiceTemplate.sendSourceAndReceiveToResult(source, result);
		System.out.println(result.toString());*/
		
		/*WebServiceTemplate webServiceTemplate=ac.getBean("webServiceTemplate111", WebServiceTemplate.class);
		Source source=new StringSource("<?xml version=\"1.0\" encoding=\"UTF-8\"?><receiveOrderRequest xmlns=\"http://www.bjdv.com/bas/order\"><tichead><ssid>Ssid</ssid><subsid>Subsid</subsid><pid>1</pid><usertype>1</usertype><tictype>1</tictype><prior>1</prior><area>Area</area><dn>Dn</dn><officecode>0</officecode><otime>Otime</otime></tichead><ticbody><basesvcid>Basesvcid</basesvcid><subsvcs><subsvcid>Subsvcid</subsvcid><oper>Oper</oper><svcpara><parament>Parament</parament><value>Value</value></svcpara></subsvcs></ticbody></receiveOrderRequest>");
		Result result=new StringResult();
		webServiceTemplate.sendSourceAndReceiveToResult(source, result);
		System.out.println(result.toString());*/
	}

}
