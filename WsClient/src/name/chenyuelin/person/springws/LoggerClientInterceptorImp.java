package name.chenyuelin.person.springws;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import name.chenyuelin.util.TransformerUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringResult;

public class LoggerClientInterceptorImp implements ClientInterceptor {
	private static final Log LOG = LogFactory.getLog(LoggerClientInterceptorImp.class);

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		/*
		 * System.out.println("HandleResponse................................."); Result result = new StringResult(); Source source =
		 * ((SoapMessage) messageContext.getRequest()).getEnvelope().getSource(); TransformerUtil.transform(source, result);
		 * System.out.println(result); System.out.println(); source = ((SoapMessage)
		 * messageContext.getResponse()).getEnvelope().getSource(); TransformerUtil.transform(source, result); System.out.println(result);
		 */
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("HandleFault.................................");
			Result result = new StringResult();
			Source source = ((SoapMessage) messageContext.getRequest()).getEnvelope().getSource();
			TransformerUtil.transform(source, result);
			LOG.debug(result);
		}
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
		if (ex != null) {
			LOG.warn("", ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("AfterCompletion.................................");
			Result result = new StringResult();
			Source source = ((SoapMessage) messageContext.getRequest()).getEnvelope().getSource();
			TransformerUtil.transform(source, result);
			LOG.debug("Request:\n" + result);

			source = ((SoapMessage) messageContext.getResponse()).getEnvelope().getSource();
			TransformerUtil.transform(source, result);
			LOG.debug("Response:\n" + result);
		}
	}

}
