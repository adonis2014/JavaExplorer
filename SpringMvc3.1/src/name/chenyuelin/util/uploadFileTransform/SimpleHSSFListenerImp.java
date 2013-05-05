package name.chenyuelin.util.uploadFileTransform;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder;
import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingRowDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @ClassName: SimpleHSSFListenerImp
 * @Description: HSSFListener implement. Use parse excel 2003.
 * @author peter.chen
 * @date 2013-4-2 PM 4:57:40 Date CR/Defect Modified By Description of change ------------ ----------- ------------ ----------------------
 */
class SimpleHSSFListenerImp implements HSSFListener {

	private static final HSSFEventFactory HSSF_EVENT_FACTORY = new HSSFEventFactory();

	private static final Log LOG = LogFactory.getLog(SimpleHSSFListenerImp.class);

	private static final byte BOOLEAN_CELL_TRUE_FLAG = (byte) 1;

	/**
	 * @Fields sheetIndex : current sheet index.
	 */
	private int sheetIndex = -1;

	/**
	 * @Fields sheetName : current sheet name.
	 */
	private String sheetName;

	/**
	 * @Fields originalRowIndex : original row index
	 */
	private int originalRowIndex;

	/**
	 * @Fields maxColumnlength : max column length of this sheet.
	 */
	private int maxColumnlength = -1;

	/**
	 * @Fields parsedDataMap : parsed data. Each map key corresponds to a sheet. Every sheet has the row list. Every row list has the column
	 *         list.
	 */
	private Map<String, List<List<Object>>> parsedDataMap = new LinkedHashMap<String, List<List<Object>>>();

	/**
	 * @Fields boundSheetRecords : Java sheet object of list.
	 */
	private ArrayList<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

	/**
	 * @Fields missingRows : missing row index. Each map key corresponds to a sheet. Every sheet has the missing row index of list.
	 */
	private Map<String, List<Integer>> missingRows = new LinkedHashMap<String, List<Integer>>();

	/**
	 * @Fields missingCells : missing cell index. Each map key corresponds to a sheet. Every sheet has the missing cell index of list.
	 */
	private Map<String, List<int[]>> missingCells = new LinkedHashMap<String, List<int[]>>();

	/**
	 * @Fields currentSheet : Current sheet content.
	 */
	private List<List<Object>> currentSheet;

	/**
	 * @Fields currentRow : Current row content
	 */
	private List<Object> currentRow;

	private int bofRecordType = 0;

	private FormatTrackingHSSFListener formatTrackingHSSFListener;

	private SheetRecordCollectingListener sheetRecordCollectingListener;

	/**
	 * @Title: processRecord
	 * @Description: process parse excel.
	 * @param inputStream
	 * @throws IOException
	 * @throws
	 */
	public void processRecord(InputStream inputStream) throws IOException {
		MissingRecordAwareHSSFListener missingRecordAwarelistener = new MissingRecordAwareHSSFListener(this);
		formatTrackingHSSFListener = new FormatTrackingHSSFListener(missingRecordAwarelistener);
		sheetRecordCollectingListener = new EventWorkbookBuilder.SheetRecordCollectingListener(formatTrackingHSSFListener);

		HSSFRequest request = new HSSFRequest();

		request.addListenerForAllRecords(sheetRecordCollectingListener);

		HSSF_EVENT_FACTORY.processWorkbookEvents(request, new POIFSFileSystem(inputStream));
	}

	@Override
	public void processRecord(Record record) {
		switch (record.getSid()) {
			case BoundSheetRecord.sid:
				BoundSheetRecord boundSheetRecord = (BoundSheetRecord) record;
				parsedDataMap.put(boundSheetRecord.getSheetname(), new ArrayList<List<Object>>());
				boundSheetRecords.add(boundSheetRecord);

				missingRows.put(boundSheetRecord.getSheetname(), new ArrayList<Integer>());
				missingCells.put(boundSheetRecord.getSheetname(), new ArrayList<int[]>());
				break;
			case BOFRecord.sid:
				BOFRecord bofRecord = (BOFRecord) record;
				bofRecordType = bofRecord.getType();
				switch (bofRecordType) {
					case BOFRecord.TYPE_WORKBOOK:
						LOG.trace("loading excel data information.");
						break;
					case BOFRecord.TYPE_WORKSHEET:
						sheetIndex += 1;
						sheetName = boundSheetRecords.get(sheetIndex).getSheetname();
						currentSheet = parsedDataMap.get(sheetName);
						originalRowIndex = 0;
						maxColumnlength = -1;

						currentRow = new ArrayList<Object>();
						break;
				}
				break;
			case RowRecord.sid:
				RowRecord rowRecord = (RowRecord) record;
				int firstColumn = rowRecord.getFirstCol();
				int lastColumn = rowRecord.getLastCol();
				int length = lastColumn - firstColumn;
				if (length > maxColumnlength) {
					maxColumnlength = length;
				}
				break;
			case BlankRecord.sid:
				BlankRecord blankRecord = (BlankRecord) record;
				missingCells.get(sheetName).add(new int[] { blankRecord.getColumn(), blankRecord.getRow() });
				currentRow.add(null);
				break;
			case BoolErrRecord.sid:
				BoolErrRecord boolErrRecord = (BoolErrRecord) record;
				if (boolErrRecord.isBoolean()) {
					currentRow.add(boolErrRecord.getErrorValue() == BOOLEAN_CELL_TRUE_FLAG);
				} else if (boolErrRecord.isError()) {
					LOG.warn(boolErrRecord);
				}
				break;
			case FormulaRecord.sid:
				FormulaRecord formulaRecord = (FormulaRecord) record;
				LOG.trace("formulaRecord:[" + formulaRecord.getColumn() + "," + formulaRecord.getRow() + "]");
				break;
			case StringRecord.sid:
				StringRecord stringRecord = (StringRecord) record;
				currentRow.add(stringRecord.getString());
				break;
			case LabelRecord.sid:
				LabelRecord labelRecord = (LabelRecord) record;
				currentRow.add(labelRecord.getValue());
				break;
			case LabelSSTRecord.sid:
				LabelSSTRecord labelSSTRecord = (LabelSSTRecord) record;
				currentRow.add(sheetRecordCollectingListener.getSSTRecord().getString(labelSSTRecord.getSSTIndex()).getString());
				break;
			case NoteRecord.sid:
				NoteRecord notegRecord = (NoteRecord) record;
				LOG.trace("formulaRecord:[" + notegRecord.getColumn() + "," + notegRecord.getRow() + "]");
				break;
			case NumberRecord.sid:
				NumberRecord numberRecord = (NumberRecord) record;
				double numberValue = numberRecord.getValue();
				int formatIndex = formatTrackingHSSFListener.getFormatIndex(numberRecord);
				if (HSSFDateUtil.isInternalDateFormat(formatIndex)) {
					currentRow.add(HSSFDateUtil.getJavaDate(numberValue));
				} else {
					currentRow.add(numberValue);
				}
				break;
			case RKRecord.sid:
				RKRecord pkRecord = (RKRecord) record;
				currentRow.add(pkRecord.getRKNumber());
				break;
			case EOFRecord.sid:
				switch (bofRecordType) {
					case BOFRecord.TYPE_WORKBOOK:
						LOG.trace("loading excel data information complete.");
						break;
					case BOFRecord.TYPE_VB_MODULE:
						break;
					case BOFRecord.TYPE_WORKSHEET:
						int size = currentSheet.size();
						if (size > 0) {
							if (currentSheet.get(size - 1).isEmpty()) {
								currentSheet.remove(size - 1);
							}
						}

						break;
					case BOFRecord.TYPE_CHART:
						break;
					case BOFRecord.TYPE_EXCEL_4_MACRO:
						break;
					case BOFRecord.TYPE_WORKSPACE_FILE:
						break;
				}
				break;
			default:
				if (record instanceof LastCellOfRowDummyRecord) {
					// remove empty row.
					if (!currentRow.isEmpty()) {
						int nullSize = 0;
						for (Object obj : currentRow) {
							if (obj == null) {
								nullSize += 1;
							}
						}
						// remove the row of all member is null
						if (nullSize == currentRow.size()) {
							currentRow.clear();
							missingRows.get(sheetName).add(originalRowIndex);
						} else {
							LastCellOfRowDummyRecord lastCellOfRowDummyRecord = (LastCellOfRowDummyRecord) record;
							if (lastCellOfRowDummyRecord.getLastColumnNumber() > -1) {
								for (int i = currentRow.size(); i < maxColumnlength; i += 1) {
									missingCells.get(sheetName).add(new int[] { i, lastCellOfRowDummyRecord.getRow() });
									currentRow.add(null);
								}
							}

							currentSheet.add(currentRow);
							currentRow = new ArrayList<Object>();
						}
					}
					originalRowIndex += 1;
				} else if (record instanceof MissingCellDummyRecord) {
					MissingCellDummyRecord missingCellDummyRecord = (MissingCellDummyRecord) record;
					currentRow.add(null);
					missingCells.get(sheetName).add(new int[] { missingCellDummyRecord.getColumn(), missingCellDummyRecord.getRow() });
				} else if (record instanceof MissingRowDummyRecord) {
					MissingRowDummyRecord missingRowDummyRecord = (MissingRowDummyRecord) record;
					missingRows.get(sheetName).add(missingRowDummyRecord.getRowNumber());
				}
				break;
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
