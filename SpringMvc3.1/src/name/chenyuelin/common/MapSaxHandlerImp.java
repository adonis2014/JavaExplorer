/**
 * 
 */
package name.chenyuelin.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Yuelin Chen
 * @version 1.0 2014-5-16
 */
public class MapSaxHandlerImp extends DefaultHandler {
	private static final Log LOG = LogFactory.getLog(MapSaxHandlerImp.class);

	private final Map<String, Object> rootMap;

	private final LinkedList<Map<String, Object>> stack = new LinkedList<Map<String, Object>>();

	private final StringBuilder elementText = new StringBuilder();

	private int currentLevelFlag = 0;

	private Map<String, Object> bufferMap;

	public MapSaxHandlerImp(Map<String, Object> contentMap) {
		this.rootMap = contentMap;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elementText.setLength(0);

		switch (stack.size()) {
			case 0:
				stack.push(rootMap);
				break;
			default:
				Map<String, Object> parentMap = stack.peek();
				Map<String, Object> currentMap = getEmtpyMap();
				if (parentMap.containsKey(qName)) {
					Object sibling = parentMap.get(qName);
					if (sibling instanceof List) {

						@SuppressWarnings("unchecked")
						List<Object> list = (List<Object>) sibling;

						// ����б��һ��Զ��ΪMap���Ϳ���ȷ�����б�ΪMap�ļ��ϡ�Map���Ͼͱ�����Ӹ���Map�Թ��ӽڵ�ʹ�á�
						if (list.get(0) instanceof Map) {
							list.add(currentMap);
						}
					} else {
						List<Object> list = new ArrayList<Object>();
						list.add(sibling);
						if (sibling instanceof Map) {
							list.add(currentMap);
						}
						parentMap.put(qName, list);
					}
				} else {
					parentMap.put(qName, currentMap);
				}
				stack.push(currentMap);
				break;
		}

		currentLevelFlag = stack.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (currentLevelFlag == stack.size()) {
			String elementContent = elementText.toString();

			// ����Ԫ��Ϊ�ַ�Ԫ��ʱ�����е�һ��Map��Ϊ���������壬����Ҫȡ���еڶ���Ԫ����������
			Map<String, Object> container = stack.get(1);

			// container�б���һ��keyΪqName��ֵ�����ֵΪMap���Map�����壬����ֱ�ӷ�elementText���ǵ���
			Object element = container.get(qName);
			if (element instanceof Map) {
				container.put(qName, elementContent);
				bufferMap = (Map<String, Object>) element;
			} else if (element instanceof String) {
				List<Object> list = new ArrayList<Object>();
				list.add(element);
				list.add(elementContent);
				container.put(qName, list);
			} else {
				((List<Object>) element).add(elementContent);
			}
		}

		stack.pop();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		elementText.append(ch, start, length);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		LOG.warn("", e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		LOG.error("", e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		LOG.fatal("", e);
	}

	private Map<String, Object> getEmtpyMap() {
		if (bufferMap == null) {
			return new HashMap<String, Object>();
		} else {
			Map<String, Object> map = bufferMap;
			bufferMap = null;
			return map;
		}

	}

}
