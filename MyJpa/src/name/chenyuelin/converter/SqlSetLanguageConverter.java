/**
 * 
 */
package name.chenyuelin.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import name.chenyuelin.enums.Language;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.Session;

/**
 * @author U1
 */
public class SqlSetLanguageConverter implements Converter {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = -4703778093279698911L;

	private static final String COMMA_SPLIT = ",";
	private static final Pattern SPLIT_PATTERN = Pattern.compile(COMMA_SPLIT);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.persistence.mappings.converters.Converter#convertObjectValueToDataValue(java.lang.Object,
	 * org.eclipse.persistence.sessions.Session)
	 */
	@Override
	public Object convertObjectValueToDataValue(Object obj, Session session) {
		SessionLog log = session.getSessionLog();
		if (log.shouldLog(SessionLog.FINEST)) {
			log.log(SessionLog.FINEST, "jpa", "convertObjectValueToDataValue start.", null);
		}
		if (obj instanceof Set) {
			Set<?> valueSet = (Set<?>) obj;
			if (valueSet.size() == 0) {
				return "";
			} else {
				StringBuilder strData = new StringBuilder(30);
				for (Object value : valueSet) {
					strData.append(value).append(COMMA_SPLIT);
				}
				strData.deleteCharAt(strData.length() - 1);
				return strData.toString();
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.persistence.mappings.converters.Converter#convertDataValueToObjectValue(java.lang.Object,
	 * org.eclipse.persistence.sessions.Session)
	 */
	@Override
	public Object convertDataValueToObjectValue(Object obj, Session session) {
		//AbstractSessionLog.getLog();
		SessionLog log = session.getSessionLog();
		if (log.shouldLog(SessionLog.FINEST)) {
			log.log(SessionLog.FINEST, "jpa", "convertDataValueToObjectValue start.", null);
		}
		if (obj instanceof String) {
			String value = (String) obj;
			if (value.equals("")) {
				return null;
			}
			Set<Language> launcherSet = new HashSet<Language>();
			try {
				for (String language : SPLIT_PATTERN.split(value)) {
					launcherSet.add(Language.valueOf(language));
				}
			} catch (IllegalArgumentException e) {
				log.logThrowable(SessionLog.WARNING, e);
			}
			return launcherSet;
		} else {
			return null;
		}
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public void initialize(DatabaseMapping databasemapping, Session session) {
		SessionLog sessionLog = AbstractSessionLog.getLog();
		sessionLog.log(SessionLog.INFO, "123");
		System.out.println();
		List<DatabaseQuery> dqs = session.getJPAQueries();
		System.out.println(dqs.size());
		DatabaseField databaseField = databasemapping.getField();
		String columnDefinition = databaseField.getName();
		databaseField.setUseDelimiters(false);
		databaseField.setName("LANGUAGES", "password(", ")");
		System.out.println(columnDefinition);
		databaseField.setColumnDefinition("password()");
	}

}
