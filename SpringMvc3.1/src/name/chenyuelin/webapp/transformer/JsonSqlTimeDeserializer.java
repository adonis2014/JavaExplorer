/**
 * 
 */
package name.chenyuelin.webapp.transformer;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author P1
 * @version 1.0 Jan 5, 2013
 */
public class JsonSqlTimeDeserializer extends JsonDeserializer<Time> {
	@Override
	public Time deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String parserText=jsonparser.getText();
        try {
        	return new Time(format.parse(parserText).getTime());
		} catch (ParseException e) {
			throw new JsonParseException(e.getMessage(),new JsonLocation(parserText,jsonparser.getTextLength(),1,1),e);
		}
	}
}
