package name.chenyuelin.constants;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public final class BaseConstants {
	private BaseConstants() {
	}

	public static final int MILLISECOND_OF_HOUR = 60 * 60 * 1000;
	public static final int MILLISECOND_OF_DAY = MILLISECOND_OF_HOUR * 24;
	public static final int MILLISECOND_OF_WEEK = MILLISECOND_OF_DAY * 7;

	public static final String ASE_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final byte[] ASE_KEY = DatatypeConverter.parseBase64Binary("IxQMvR8Ob3PnO1FK6R/LYw==");
	public static final AlgorithmParameterSpec ASE_SALT = new IvParameterSpec(DatatypeConverter.parseBase64Binary("yaZv3N8D9PnoSHuqc7o2rA=="));

	public static final DatatypeFactory DATATYPE_FACTORY = createDatatypeFactory();

	public static final String STRING_ENCODING_ISO88591 = "ISO-8859-1";

	public static final String STRING_ENCODING_UTF8 = "UTF-8";

	public static final String STRING_ENCODING_GB18030 = "GB18030";

	private static DatatypeFactory createDatatypeFactory() {
		try {
			return DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
}
