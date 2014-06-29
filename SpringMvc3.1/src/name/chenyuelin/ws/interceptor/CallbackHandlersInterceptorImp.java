package name.chenyuelin.ws.interceptor;

import javax.security.auth.callback.CallbackHandler;

import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class CallbackHandlersInterceptorImp implements EndpointInterceptor {
	private CallbackHandler keyStoreCallbackHandler;
	

	public void setKeyStoreCallbackHandler(CallbackHandler keyStoreCallbackHandler) {
		this.keyStoreCallbackHandler = keyStoreCallbackHandler;
	}


	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		messageContext.setProperty(WSHandlerConstants.ENC_CALLBACK_REF, keyStoreCallbackHandler);
		return true;
	}


	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}


	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}


	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
	}

}
