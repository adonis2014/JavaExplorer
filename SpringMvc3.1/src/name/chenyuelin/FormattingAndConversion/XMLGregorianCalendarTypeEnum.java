/**
 * 
 */
package name.chenyuelin.FormattingAndConversion;

/**
 * @author P1
 * @version 1.0 Jan 12, 2013
 */
public enum XMLGregorianCalendarTypeEnum {
	DATE {
		public String toString() {
			return "date";
		}
	},
	TIME {
		public String toString() {
			return "time";
		}
	},
	DATE_TIME {
		public String toString() {
			return "dateTime";
		}
	};
}
