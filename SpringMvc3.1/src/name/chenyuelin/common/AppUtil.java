/**
 * 
 */
package name.chenyuelin.common;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import name.chenyuelin.constants.BaseConstants;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
public class AppUtil {

	public static String decryptBase64ToString(String base64Ciphertext) {
		try {
			return new String(AesEncryptionUtil.decrypt(DatatypeConverter.parseBase64Binary(base64Ciphertext), BaseConstants.ASE_ALGORITHM, BaseConstants.ASE_KEY,
					BaseConstants.ASE_SALT), BaseConstants.STRING_ENCODING_UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String encryptTextToBase64(String text) {
		try {
			return DatatypeConverter.printBase64Binary(AesEncryptionUtil.encrypt(text.getBytes(BaseConstants.STRING_ENCODING_UTF8), BaseConstants.ASE_ALGORITHM,
					BaseConstants.ASE_KEY, BaseConstants.ASE_SALT));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static long calculateDailyMillisecondDifference(int hour) {
		Calendar calendar = Calendar.getInstance();
		return calculateDailyMillisecondDifference(calendar, hour);
	}

	private static long calculateDailyMillisecondDifference(Calendar calendar, int hour) {
		int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour > currentHour) {
			calendar.set(Calendar.HOUR_OF_DAY, hour);
		} else {
			calendar.add(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
		}
		return calendar.getTimeInMillis() - System.currentTimeMillis();
	}

	public static long calculateWeeklyMillisecondDifference(int weekDay, int hour) {
		Calendar calendar = Calendar.getInstance();
		int currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK);

		if (currentWeekDay > weekDay) {
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			calendar.set(Calendar.DAY_OF_WEEK, weekDay);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			return calendar.getTimeInMillis() - System.currentTimeMillis();
		} else if (currentWeekDay == weekDay) {
			return calculateDailyMillisecondDifference(calendar, hour);
		} else {
			calendar.set(Calendar.DAY_OF_WEEK, weekDay);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			return calendar.getTimeInMillis() - System.currentTimeMillis();
		}
	}
}
