package csv.google;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class DefaultRead {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException {
		Reader reader = new FileReader("src/csv/google/IntegrationTest-Review-20130701.csv");
		CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(reader);
		List<String[]> data = csvParser.readAll();
		System.out.println(data);
	}

}
