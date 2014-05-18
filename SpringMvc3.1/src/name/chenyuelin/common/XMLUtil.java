package name.chenyuelin.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Yuelin Chen
 * @version 1.0 2014-5-18
 */
public class XMLUtil {
	
	/**
	 * Parse XML to Map
	 * @param inputStream
	 * @return Map data of XML
	 */
	public static final Map<String, Object> readToMap(InputStream inputStream) {
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			Map<String, Object> contentMap = new HashMap<String, Object>();
			saxParser.parse(inputStream, new MapSaxHandlerImp(contentMap));
			return contentMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
