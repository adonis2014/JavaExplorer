package name.chenyuelin.util.uploadFileTransform;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: TransformExcelUtil
 * @Description: Utility class for transforming excel file
 */
public final class TransformExcelUtil {
	private TransformExcelUtil() {
	}

	/**
	 * @Title: parseExcel2003
	 * @Description: Parse the excel 2003
	 * @param inputStream
	 * @return ExcelParsedResult
	 * @throws IOException
	 */
	public static ExcelParsedResult parseExcel2003(InputStream inputStream) throws IOException {
		ExcelParsedResult result = new ExcelParsedResult();
		SimpleHSSFListenerImp hssfListenerImp = new SimpleHSSFListenerImp();
		hssfListenerImp.processRecord(inputStream);
		result.setParsedDataMap(hssfListenerImp.getParsedDataMap());
		result.setMissingRows(hssfListenerImp.getMissingRows());
		result.setMissingCells(hssfListenerImp.getMissingCells());
		return result;
	}

	/**
	 * @Title: parseExcel2007
	 * @Description: Parse the excel 2007
	 * @param inputStream
	 * @return ExcelParsedResult
	 * @throws Exception
	 */
	public static ExcelParsedResult parseExcel2007(InputStream inputStream) throws Exception {
		ExcelParsedResult result = new ExcelParsedResult();
		SimpleXSSFParser simpleXSSFParser = new SimpleXSSFParser();
		simpleXSSFParser.process(inputStream);
		result.setParsedDataMap(simpleXSSFParser.getParsedDataMap());
		result.setMissingRows(simpleXSSFParser.getMissingRows());
		result.setMissingCells(simpleXSSFParser.getMissingCells());
		return result;
	}
}
