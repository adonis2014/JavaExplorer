package name.chenyuelin.common.uploadFileTransform;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @ClassName: SimpleXssfParser
 * @Description: Parser for excel 2007
 * @author peter.chen
 * @date 2013-4-3 AM 10:10:01 Date CR/Defect Modified By Description of change ------------ ----------- ------------ ----------------------
 */
class SimpleXSSFParser {
	private static final Log LOG = LogFactory.getLog(SimpleXSSFParser.class);

	/**
	 * @Fields parsedDataMap : Map for all sheet data
	 */
	private Map<String, List<List<Object>>> parsedDataMap = new LinkedHashMap<String, List<List<Object>>>();

	/**
	 * @Fields missingRows : Map for all sheet missing rows.
	 */
	private Map<String, List<Integer>> missingRows = new LinkedHashMap<String, List<Integer>>();

	/**
	 * @Fields missingCells : Map for all sheet missing cells.
	 */
	private Map<String, List<int[]>> missingCells = new LinkedHashMap<String, List<int[]>>();

	/**
	 * @Title: process
	 * @Description: parser excel action.
	 * @param inputStream
	 * @throws IOException
	 * @throws SAXException
	 * @throws InvalidFormatException
	 * @throws OpenXML4JException
	 * @throws
	 */
	public void process(InputStream inputStream) throws IOException, SAXException, InvalidFormatException, OpenXML4JException {
		OPCPackage pkg = OPCPackage.open(inputStream);

		XSSFReader xssfReader = new XSSFReader(pkg);
		StylesTable stylesTable = xssfReader.getStylesTable();
		SharedStringsTable sst = xssfReader.getSharedStringsTable();

		XMLReader parser = XMLReaderFactory.createXMLReader();
		SimpleSaxParseSheetHandler handler = new SimpleSaxParseSheetHandler(sst, stylesTable);
		parser.setContentHandler(handler);

		XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
		while (sheets.hasNext()) {
			InputStream sheet = sheets.next();
			String sheetName = sheets.getSheetName();

			if (LOG.isTraceEnabled()) {
				LOG.trace("Processing " + sheetName + " sheet.");
			}

			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);

			parsedDataMap.put(sheetName, handler.getSheetData());
			missingRows.put(sheetName, handler.getMissingRowsIndex());
			missingCells.put(sheetName, handler.getMissingCellsIndex());
			sheet.close();
		}
	}

	public Map<String, List<List<Object>>> getParsedDataMap() {
		return parsedDataMap;
	}

	public Map<String, List<Integer>> getMissingRows() {
		return missingRows;
	}

	public Map<String, List<int[]>> getMissingCells() {
		return missingCells;
	}

}
