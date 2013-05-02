package name.chenyuelin.util.uploadFileTransform;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExcelParsedResult
 * @Description: Result of excel parsed.
 * @author peter.chen
 * @date 2013-4-2 PM 5:32:15 Date CR/Defect Modified By Description of change ------------ ----------- ------------
 *       ----------------------
 */
public class ExcelParsedResult {
  /**
   * @Fields parsedDataMap : parsed data. Each map key corresponds to a sheet. Every sheet has the row list. Every row
   *         list has the column list.
   */
  private Map<String, List<List<Object>>> parsedDataMap;

  /**
   * @Fields missingRows : missing row index. Each map key corresponds to a sheet. Every sheet has the missing row index
   *         of list.
   */
  private Map<String, List<Integer>> missingRows;

  /**
   * @Fields missingCells : missing cell index. Each map key corresponds to a sheet. Every sheet has the missing cell
   *         index of list.
   */
  private Map<String, List<int[]>> missingCells;

  public Map<String, List<List<Object>>> getParsedDataMap() {
    return parsedDataMap;
  }

  public void setParsedDataMap(Map<String, List<List<Object>>> parsedDataMap) {
    this.parsedDataMap = parsedDataMap;
  }

  public Map<String, List<Integer>> getMissingRows() {
    return missingRows;
  }

  public void setMissingRows(Map<String, List<Integer>> missingRows) {
    this.missingRows = missingRows;
  }

  public Map<String, List<int[]>> getMissingCells() {
    return missingCells;
  }

  public void setMissingCells(Map<String, List<int[]>> missingCells) {
    this.missingCells = missingCells;
  }
}
