package csv;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class JacksonCsv {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		CsvSchema bootstrap = CsvSchema.emptySchema().withColumnSeparator(',').withLineSeparator("\n");
		CsvSchema schema = CsvSchema.builder()
				   .addColumn("a") 
				   .addColumn("b")
				   .addColumn("c")
				   .addColumn("d")
				   .addColumn("e") 
				   .addColumn("f")
				   .addColumn("g")
				   .addColumn("h")
				   .addColumn("i")
				   .addColumn("j")
				   .addColumn("k")
				   .addColumn("l")
				   .addColumn("m")
				   .addColumn("n")
				   .build();
		CsvMapper cm=new CsvMapper();
		cm.schemaFor(SimpleBean.class);
//		MappingIterator<SimpleBean> obj=cm.reader(SimpleBean.class).with(schema).readValues(new File("d:/BUGList_20120802.csv"));
//		List<SimpleBean> objj=obj.readAll();
//		System.out.println(objj);
		
		CsvParser csvParser=cm.getFactory().createParser(new File("d:/BUGList_20120802.csv"));
//		csvParser.setSchema(schema);
		MappingIterator<SimpleBean> b=cm.readValues(csvParser, SimpleBean.class);
		
		System.out.println(b.readAll());
		
		cm.enable(CsvParser.Feature.WRAP_AS_ARRAY);
		List<?> objs=cm.readValue(new File("d:/BUGList_20120802.csv"), List.class);
//		System.out.println(objs);
		
		CsvGenerator csvGenerator=cm.getFactory().createGenerator(new FileOutputStream(new File("d:/aa.csv")));
//		csvGenerator.setSchema(schema);
		cm.writeValue(csvGenerator, objs);
//		cm.writer(schema).writeValue(new File("d:/aa.csv"), objj);
		
//		SimpleBean objj=cm.readValue(new File("d:/BUGList_20120802.csv"), SimpleBean.class);
//		for(Object m:objj){
//			System.out.println(m);
//		}
		
//		Builder schema = CsvSchema.builder().addColumn("name").addColumn("age");
	}

}
