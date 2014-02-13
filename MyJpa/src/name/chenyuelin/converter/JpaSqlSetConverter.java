package name.chenyuelin.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import name.chenyuelin.enums.Language;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;

@Converter(autoApply=false)
public class JpaSqlSetConverter implements AttributeConverter<Collection<Language>, String> {

	private static final String COMMA_SPLIT = ",";
	private static final Pattern SPLIT_PATTERN = Pattern.compile(COMMA_SPLIT);

	@Override
	public String convertToDatabaseColumn(Collection<Language> attribute) {
		SessionLog log = AbstractSessionLog.getLog();
		if (log.shouldLog(SessionLog.FINEST)) {
			log.log(SessionLog.FINEST, "jpa", "convertToDatabaseColumn start.", null);
		}
		if (attribute.size() == 0) {
			return "";
		} else {
			StringBuilder strData = new StringBuilder(30);
			for (Object value : attribute) {
				strData.append(value).append(COMMA_SPLIT);
			}
			strData.deleteCharAt(strData.length() - 1);
			return strData.toString();
		}
	}

	@Override
	public Collection<Language> convertToEntityAttribute(String dbData) {
		SessionLog log = AbstractSessionLog.getLog();
		if (log.shouldLog(SessionLog.FINEST)) {
			log.log(SessionLog.FINEST, "jpa", "convertToEntityAttribute start.", null);
		}
		if (dbData.equals("")) {
			return null;
		}
		Collection<Language> launcherSet = new ArrayList<Language>();
		try {
			for (String language : SPLIT_PATTERN.split(dbData)) {
				launcherSet.add(Language.valueOf(language));
			}
		} catch (IllegalArgumentException e) {
			log.logThrowable(SessionLog.WARNING, e);
		}
		return launcherSet;
	}

}
