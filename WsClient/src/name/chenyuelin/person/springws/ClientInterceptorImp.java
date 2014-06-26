package name.chenyuelin.person.springws;

import javax.security.auth.callback.CallbackHandler;

import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

public class ClientInterceptorImp implements ClientInterceptor {
	private CallbackHandler keyStoreCallbackHandler;
	
	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		messageContext.setProperty(WSHandlerConstants.ENC_CALLBACK_REF, keyStoreCallbackHandler);
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
	}

	public void setKeyStoreCallbackHandler(CallbackHandler keyStoreCallbackHandler) {
		this.keyStoreCallbackHandler = keyStoreCallbackHandler;
	}

}
