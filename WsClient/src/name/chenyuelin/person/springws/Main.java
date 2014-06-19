package name.chenyuelin.person.springws;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import name.chenyuelin.person.springws.dto.ObjectFactory;
import name.chenyuelin.person.springws.dto.PersonCommand;

import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		JAXBContext context=JAXBContext.newInstance(ObjectFactory.class);
		ObjectFactory objectFactory=new ObjectFactory();
		PersonCommand personCommand =objectFactory.createPersonCommand();
		personCommand.setId((byte)1);
		Marshaller marshaller=context.createMarshaller();
		Result result=new StringResult();
		marshaller.marshal(objectFactory.createFindPersonRequest(personCommand), result);
		
		Unmarshaller unmarshaller =context.createUnmarshaller();
		Object personCommandr=unmarshaller.unmarshal(new StringSource(result.toString()));
		System.out.println(((JAXBElement<?>)personCommandr).getValue());
	}

}
