package csv.google;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class EntryParserRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		Reader csvFile = new FileReader("src/csv/google/IntegrationTest-Review-20130701.csv");

		CSVReader<FileBean> personReader = new CSVReaderBuilder<FileBean>(csvFile).entryParser(new EntryParserImp()).strategy(CSVStrategy.UK_DEFAULT).build();
		personReader.readHeader();
		List<FileBean> persons = personReader.readAll();
		System.out.println(persons);
	}
	
}

class EntryParserImp implements CSVEntryParser<FileBean>{

	@Override
	public FileBean parseEntry(String... data) {
		FileBean bean=new FileBean();
		bean.setProjectModule(data[0]);
		bean.setDeveloper(data[1]);
		bean.setIssues(data[3]);
		bean.setAreas(data[2]);
		return bean;
	}
}

class FileBean{
	private String projectModule;
	private String developer;
	private String areas;
	private String issues;
	
	public String getProjectModule() {
		return projectModule;
	}
	public void setProjectModule(String projectModule) {
		this.projectModule = projectModule;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getIssues() {
		return issues;
	}
	public void setIssues(String issues) {
		this.issues = issues;
	}
}