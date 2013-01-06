/**
 * prototype of date format.
 * @param format pattern the pattern describing the date and time format
 * @return date format string
 */
Date.prototype.format = function(format){
	var o = {
			"M+" : this.getMonth() + 1, //month
			"d+" : this.getDate(), //day
			"h+" : this.getHours(), //hour
			"m+" : this.getMinutes(), //minute
			"s+" : this.getSeconds(), //second
			"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
			"S" : this.getMilliseconds()
		//millisecond
		};
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
};

/*utils = {
	formatDate : function(date, format) {
		var o = {
			"M+" : date.getMonth() + 1, //month
			"d+" : date.getDate(), //day
			"h+" : date.getHours(), //hour
			"m+" : date.getMinutes(), //minute
			"s+" : date.getSeconds(), //second
			"q+" : Math.floor((date.getMonth() + 3) / 3), //quarter
			"S" : date.getMilliseconds()
		//millisecond
		};

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (date.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
};*/

/**
 * XmlParserUtil object
 */
function XmlParserUtil(){
	var isMoz=(typeof document.implementation != 'undefined')&& (typeof document.implementation.createDocument != 'undefined');
	var isIe=(typeof window.ActiveXObject != 'undefined');
	
	var createXmlObject=function(){
		var dom;
		if (isMoz){
			dom = document.implementation.createDocument("", "doc", null);
		}else if(isIe){
			var xmlDomVersions = ['Microsoft.XMLDOM','MSXML.2.DOMDocument.3.0','MSXML.2.DOMDocument.6.0'];
            for(var i=0;i<xmlDomVersions.length;i++){
                try{
                	dom = new ActiveXObject(xmlDomVersions[i]);
                    break;
                }catch(e){
                }
            }
		}else{
			return null;
		}
		dom.async = false;
		return dom;
	};
	
	/**
	 * parser xml form string.
	 * @param xmlString string of xml
	 * @return dom of xml.
	 */
	this.parser=function(xmlString){
		var xmlDoc=createXmlObject();
		if (isMoz){
			var domParser = new DOMParser();
            xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
		}else if(isIe){
			xmlDoc.loadXML(xmlString);
		}
		return xmlDoc;
	};
	
	/**
	 * serializer xml form dom of xml.
	 * @param dom of xml
	 * @return string of xml
	 */
	this.serializerXml=function(xmlObject){
		if (isMoz){
			return new XMLSerializer().serializeToString(xmlObject);
		}else if(isIe){
			return xmlObject.xml;
		}else {
			return null;
		}
	};
	
	/**
	 * load xml from file path.
	 * @param file path
	 * @return dom of xml
	 */
	this.loadXmlFile=function(path){
		var xmlObject=createXmlObject();
		xmlObject.load(path);
		return xmlObject;
	};
}
