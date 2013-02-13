/**
 * 
 */
package name.chenyuelin.converter;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import name.chenyuelin.enums.Language;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

/**
 * @author U1
 *
 */
public class SqlSetLanguageConverter implements Converter {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = -4703778093279698911L;
	
	private static final Log LOG=LogFactory.getLog(SqlSetLanguageConverter.class);
	
	private static final String COMMA_SPLIT=",";
	private static final Pattern SPLIT_PATTERN=Pattern.compile(COMMA_SPLIT);

	/* (non-Javadoc)
	 * @see org.eclipse.persistence.mappings.converters.Converter#convertObjectValueToDataValue(java.lang.Object, org.eclipse.persistence.sessions.Session)
	 */
	@Override
	public Object convertObjectValueToDataValue(Object obj, Session session) {
		if(obj instanceof Set){
			Set<?> valueSet=(Set<?>)obj;
			if(valueSet.size()==0){
				return "";
			}else{
				StringBuilder strData=new StringBuilder(30);
				for(Object value:valueSet){
					strData.append(value).append(COMMA_SPLIT);
				}
				strData.deleteCharAt(strData.length()-1);
				return strData.toString();
			}
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.persistence.mappings.converters.Converter#convertDataValueToObjectValue(java.lang.Object, org.eclipse.persistence.sessions.Session)
	 */
	@Override
	public Object convertDataValueToObjectValue(Object obj, Session session) {
		if(obj instanceof String){
			String value=(String)obj;
			if(value.equals("")){
				return null;
			}
			Set<Language> launcherSet=new HashSet<Language>();
			for(String language:SPLIT_PATTERN.split(value)){
				try {
					launcherSet.add(Language.valueOf(language));
				} catch (IllegalArgumentException e) {
					LOG.warn("", e);
				}
			}
			return launcherSet;
		}else{
			return null;
		}
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public void initialize(DatabaseMapping databasemapping, Session session) {
	}

}
