package name.chenyuelin.constants;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public final class BaseConstants {
	private BaseConstants() {
	}

	public static final DatatypeFactory DATATYPE_FACTORY = createDatatypeFactory();

	private static DatatypeFactory createDatatypeFactory() {
		try {
			return DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
}
