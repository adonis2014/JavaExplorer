package name.chenyuelin.common.uploadFileTransform;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @ClassName: SimpleParseSheetHandler
 * @Description: Simple parse handler for excel 2007.
 * @author peter.chen
 * @date 2013-4-3 AM 10:28:21 Date CR/Defect Modified By Description of change ------------ ----------- ------------ ----------------------
 */
class SimpleSaxParseSheetHandler extends DefaultHandler {
	private static final Log LOG = LogFactory.getLog(SimpleSaxParseSheetHandler.class);

	private static final String ELEMENT_NAME_DIMENSION = "dimension";

	private static final String ELEMENT_NAME_SHEET_DATA = "sheetData";

	private static final String ELEMENT_NAME_ROW = "row";

	private static final String ELEMENT_NAME_CELL = "c";

	private static final String ELEMENT_NAME_VALUE = "v";

	private static final String ATTRIBUTE_CELL_STYLE = "s";

	private static final String ATTRIBUTE_CELL_INDEX = "r";

	private static final String ATTRIBUTE_CELL_TYPE = "t";

	private static final String STRING_TYPE_FLAG = "s";

	private static final String BOOLEAN_TYPE_FLAG = "b";

	private static final String DIMENSION_REF_ATTRIBUTE = "ref";

	private static final String BOOLEAN_CELL_TRUE_FLAG = "1";

	/**
	 * @Fields sheetData : Data of this sheet.
	 */
	private List<List<Object>> sheetData;

	/**
	 * @Fields missingRowsIndex : Row index of miss row.
	 */
	private List<Integer> missingRowsIndex;

	/**
	 * @Fields missingCellsIndex : Cell index of miss cell.
	 */
	private List<int[]> missingCellsIndex;

	/**
	 * @Fields currentRow : Current row data.
	 */
	private List<Object> currentRow;

	/**
	 * @Fields locator : @see org.xml.sax.Locator
	 */
	private Locator locator;

	/**
	 * @Fields sst : @see org.apache.poi.xssf.model.SharedStringsTable
	 */
	private SharedStringsTable sst;

	/**
	 * @Fields stylesTable : org.apache.poi.xssf.model.StylesTable
	 */
	private StylesTable stylesTable;

	/**
	 * @Fields cellValue : Cell value.
	 */
	private StringBuilder cellValue = new StringBuilder();

	/**
	 * @Fields startColumnIndex : Start column index of this sheet.
	 */
	private int startColumnIndex;

	/**
	 * @Fields columnLength : Column length index of this sheet.
	 */
	private int columnLength;

	/**
	 * @Fields rowIndex : Current row index.
	 */
	private int rowIndex;

	/**
	 * @Fields cellType : Current cell type.
	 */
	private String cellType;

	/**
	 * @Fields cellIndex : Current cell index.
	 */
	private String cellIndex;

	/**
	 * @Fields cellStyle : Current cell style.
	 */
	private String cellStyle;

	public SimpleSaxParseSheetHandler(SharedStringsTable sst, StylesTable stylesTable) {
		this.sst = sst;
		this.stylesTable = stylesTable;
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if (name.equals(ELEMENT_NAME_DIMENSION)) {
			String ref = attributes.getValue(DIMENSION_REF_ATTRIBUTE);
			startColumnIndex = ref.charAt(0) - 1;
			if (ref.length() > 2) {
				columnLength = ref.charAt(3) - ref.charAt(0) + 1;
			} else {
				columnLength = 0;
			}
		} else if (name.equals(ELEMENT_NAME_SHEET_DATA)) {
			LOG.debug("Parse sheet start.");
		} else if (name.equals(ELEMENT_NAME_ROW)) {
			currentRow = new ArrayList<Object>();
			sheetData.add(currentRow);

			cellIndex = String.valueOf((char) startColumnIndex);

			int currentRowIndex = Integer.parseInt(attributes.getValue(ATTRIBUTE_CELL_INDEX));

			for (int i = rowIndex + 1; i < currentRowIndex; i += 1) {
				missingRowsIndex.add(i);
			}
			rowIndex = currentRowIndex;
		} else if (name.equals(ELEMENT_NAME_CELL)) {
			String currentCellIndex = attributes.getValue(ATTRIBUTE_CELL_INDEX);
			cellType = attributes.getValue(ATTRIBUTE_CELL_TYPE);

			cellStyle = attributes.getValue(ATTRIBUTE_CELL_STYLE);

			int i = cellIndex.charAt(0) + 1;
			int end = currentCellIndex.charAt(0);
			for (; i < end; i += 1) {
				missingCellsIndex.add(new int[] { i - startColumnIndex, rowIndex });
				currentRow.add(null);
			}
			cellIndex = currentCellIndex;
		}
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		if (name.equals(ELEMENT_NAME_SHEET_DATA)) {
			LOG.debug("Parse sheet end.");
		} else if (name.equals(ELEMENT_NAME_ROW)) {
			// remove empty row
			if (currentRow.isEmpty()) {
				missingRowsIndex.add(rowIndex);
				sheetData.remove(currentRow);
			} else {
				int nullSize = 0;
				for (Object obj : currentRow) {
					if (obj == null) {
						nullSize += 1;
					}
				}
				// remove the row of all member is null
				if (nullSize == currentRow.size()) {
					missingRowsIndex.add(rowIndex);
					sheetData.remove(currentRow);
				} else {
					for (int i = cellIndex.charAt(0) - startColumnIndex; i < columnLength; i += 1) {
						missingCellsIndex.add(new int[] { i + 1, rowIndex });
						currentRow.add(null);
					}
				}
			}
		} else if (name.equals(ELEMENT_NAME_CELL)) {
			if (cellType == null) {// number or last special row.
				if (cellValue.length() == 0) {
					missingCellsIndex.add(new int[] { cellIndex.charAt(0) - startColumnIndex, rowIndex });
					currentRow.add(null);
				} else {
					Double value = Double.valueOf(cellValue.toString());
					if (cellStyle == null) {
						currentRow.add(value);
					} else {
						XSSFCellStyle xssfCellStyle = stylesTable.getStyleAt(Integer.parseInt(cellStyle));
						short formatIndex = xssfCellStyle.getDataFormat();
						String formatString = xssfCellStyle.getDataFormatString();
						if (formatString == null) {
							formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
						}
						if (DateUtil.isADateFormat(formatIndex, formatString)) {
							currentRow.add(DateUtil.getJavaDate(value));
						} else {
							currentRow.add(value);
						}
					}
				}
			} else {
				if (cellType.equals(STRING_TYPE_FLAG)) {
					CTRst ctRst = sst.getEntryAt(Integer.parseInt(cellValue.toString()));
					RichTextString richTextString = new XSSFRichTextString(ctRst);
					currentRow.add(richTextString.getString());
				} else if (cellType.equals(BOOLEAN_TYPE_FLAG)) {
					currentRow.add(cellValue.toString().equals(BOOLEAN_CELL_TRUE_FLAG));
				} else {

				}
			}
			// clean cell value.
			cellValue.setLength(0);
		} else if (name.equals(ELEMENT_NAME_VALUE)) {
			if (LOG.isTraceEnabled()) {
				LOG.trace(cellValue);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		cellValue.append(new String(ch, start, length));
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
		LOG.debug(this.locator);
	}

	@Override
	public void startDocument() throws SAXException {
		sheetData = new ArrayList<List<Object>>();
		missingRowsIndex = new ArrayList<Integer>();
		missingCellsIndex = new ArrayList<int[]>();
		rowIndex = 0;
	}

	@Override
	public void endDocument() throws SAXException {
		cellValue.setLength(0);
		currentRow = null;
		startColumnIndex = -1;
		columnLength = -1;
		cellType = null;
		cellIndex = null;
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		LOG.warn(e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		LOG.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		LOG.error(e);
	}

	public List<List<Object>> getSheetData() {
		return sheetData;
	}

	public List<Integer> getMissingRowsIndex() {
		return missingRowsIndex;
	}

	public List<int[]> getMissingCellsIndex() {
		return missingCellsIndex;
	}

}
