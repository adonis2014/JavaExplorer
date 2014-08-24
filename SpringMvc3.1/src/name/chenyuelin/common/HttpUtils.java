package name.chenyuelin.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

	public static byte[] httpInvoke(String urlStr, String method, Map<String, String> requestProperty, byte[] request) throws IOException {
		URL url = new URL(urlStr);
		URLConnection urlConnection = url.openConnection();
		if (urlConnection instanceof HttpURLConnection) {
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

			if (requestProperty != null) {
				for (Map.Entry<String, String> entry : requestProperty.entrySet()) {
					httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			httpURLConnection.setRequestMethod(method);

			if ("POST".equals(method) || "PUT".equals(method)) {
				httpURLConnection.setDoOutput(true);
			}
			httpURLConnection.connect();

			if (httpURLConnection.getDoOutput()) {
				OutputStream outputStream = httpURLConnection.getOutputStream();
				outputStream.write(request);
				outputStream.close();
			}

			byte[] dataPart;
			int contentLength = httpURLConnection.getContentLength();
			InputStream inputStream = httpURLConnection.getInputStream();
			if (contentLength == -1) {
				dataPart = new byte[1024];
				ByteArrayOutputStream responseData = new ByteArrayOutputStream();
				int i = -1;
				while ((i = inputStream.read(dataPart)) > -1) {
					responseData.write(dataPart, 0, i);
				}
				dataPart=responseData.toByteArray();
				responseData.close();
			} else {
				dataPart = new byte[contentLength];
				inputStream.read(dataPart);
			}
			inputStream.close();
			httpURLConnection.disconnect();
			return dataPart;
		}
		return null;
	}

	public static byte[] postJSON(String urlStr, byte[] request) {
		Map<String, String> requestProperty = new HashMap<String, String>();
		requestProperty.put("Content-Type", "application/json");
		try {
			return httpInvoke(urlStr, "POST", requestProperty, request);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
