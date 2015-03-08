package name.chenyuelin.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.server.standard.SpringConfigurator;

@Component
@ServerEndpoint(value = "/websocket/sample2/{type-id}", configurator = SpringConfigurator.class)
public class WebSocketAnnotationSample {

	@Autowired
	private WebApplicationContext ac;

	@OnMessage
	public String onMessage(String message, @PathParam("type-id") String typeId, Session session) throws IOException, InterruptedException {
		return session.getId() + ":" + message + ":" + typeId;
	}

	@OnMessage
	public ByteBuffer onMessage(ByteBuffer data, @PathParam("type-id") String typeId, Session session) throws IOException, InterruptedException {
		System.out.println(session.getId() + ":" + typeId);
		Adler32 adler32 = new Adler32();
		adler32.update(data.array());
		long value = adler32.getValue();
		ByteBuffer result = ByteBuffer.allocate(8);
		result.putLong(value);
		result.flip();
		return result;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		session.setMaxBinaryMessageBufferSize(1024 * 1024 * 50);
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("Connection closed");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		throwable.printStackTrace();
	}
}
