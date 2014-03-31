importClass(java.util.Arrays);
importClass(java.util.regex.Pattern);
importClass(java.util.LinkedHashMap);
importClass(java.util.ArrayList);
importClass(java.util.Map);
importClass(java.util.Map.Entry);
importClass(java.lang.StringBuilder);
importClass(java.io.File);
importClass(org.apache.tools.ant.types.FileList);
importClass(org.apache.tools.ant.ComponentHelper);

prop={};
prop.pathName=project.getProperty("compressEngine.jsPathName");
prop.urlSeparator=project.getProperty("compressEngine.urlSeparator");
prop.webContent=project.getProperty("path.src.webContent");
prop.proDir=project.getProperty("project-js-directory");
prop.excludeDirs=project.getProperty("compressEngine.directory.excludes").split(",");
prop.mergeDirs=project.getProperty("compressEngine.directory.merge").split(",");

content = self.getToken();
content = content.replaceAll("(?s)<!--.*?-->", "");
resultContent = new StringBuilder(50);
pattern = Pattern.compile("<script(?:\\s+type=[\"']text/javascript[\"'])?\\s+src=[\"']([\\w-\\./\\\\]+)[\"'](?:\\s+charset=[\"'][\\w-]+[\"'])?></script>");
matcher = pattern.matcher(content);

categoryMap = new LinkedHashMap();
resultMap = new LinkedHashMap();

scriptStartIndex = -1;
scriptEndIndex = -1;
while (matcher.find()) {
	if (scriptStartIndex < 0) {
		scriptStartIndex = matcher.start();
	}
	scriptEndIndex = matcher.end();
	var groupString = matcher.group(1);
	generateTreeMap(categoryMap, groupString);
}
if (scriptStartIndex > 0) {
	// println(categoryMap);
	fillResultMap(categoryMap, new ArrayList(), resultMap);
	// println("resultMap" + resultMap);

	resultContent.append(content.substring(0, scriptStartIndex));
	resultContent.append(mergeFile(resultMap));
	resultContent.append(content.substring(scriptEndIndex + 1));
	self.setToken(resultContent.toString());
}

function generateTreeMap(treeMap, srcString) {
	var deep = 1;
	var pathArray = srcString.split("[\\/]");
	var hasJsFlag = false;
	var isRigthPath = pathArray.length > deep;
	var parentPathPunctuation = "..";
	if (isRigthPath) {
		for ( var i = 0; i < pathArray.length; i += 1) {
			if (prop.pathName.equals(pathArray[i])) {
				if (pathArray.length - 1 - i < deep) {
					isRigthPath = false;
					break;
				} else {
					hasJsFlag = true;
				}
			}
			if (hasJsFlag) {
				if (parentPathPunctuation.equals(pathArray[i])) {
					isRigthPath = false;
					break;
				}
			} else {
				if (!parentPathPunctuation.equals(pathArray[i])) {
					isRigthPath = false;
					break;
				}
			}
		}
	}

	if (isRigthPath) {
		var parent = treeMap;
		var tree;
		for ( var i = 0; i < pathArray.length; i += 1) {
			if (i != pathArray.length - 1) {
				tree = parent.get(pathArray[i].toString());
				if (tree == null) {
					tree = new LinkedHashMap();
					parent.put(pathArray[i].toString(), tree);
				}
				parent = tree;
			} else {
				parent.put(pathArray[i], pathArray[i]);
			}
		}
	}
}

/*
 * resultMap={ mineFile:"../js/lib/sample.min.js", fileListDir:"WebContent/js/lib", destfile:"lib.js", files:["jquery-2.1.0.min.js",
 * "jquery-ui-1.10.4.custom.min.js", "underscore-min.js", "backbone-min.js"] }
 */
function fillResultMap(treeMap, depthList, resultMap) {
	var entrySet = treeMap.entrySet();
	var iterator = entrySet.iterator();
	while (iterator.hasNext()) {
		var entry = iterator.next();
		var key = entry.getKey();
		var value = entry.getValue();
		if (value instanceof Map) {
			depthList.add(key);
			fillResultMap(value, depthList, resultMap);
		} else {
			var keyName = "";//key name is js directory name or base
			var mineFile = new StringBuilder();
			var filePath = new StringBuilder();
			var destfile = "";
			var fileListDir = new StringBuilder(prop.webContent);
			fileListDir.append(prop.urlSeparator);
			var jsPathIndex = -1;
			for ( var i = 0; i < depthList.size(); i += 1) {
				var path = depthList.get(i);
				if (prop.pathName.equals(path)) {
					mineFile.append(path).append(prop.urlSeparator);
					fileListDir.append(path).append(prop.urlSeparator);
					jsPathIndex = i;
				}
				if (jsPathIndex < 0) {
					mineFile.append(path).append(prop.urlSeparator);
				} else {
					if (jsPathIndex == depthList.size() - 1) {
						destfile = "base.js";
						keyName = "base";
						mineFile.append("base.min.js");
					} else {
						if (i - jsPathIndex == 1) {
							keyName = path;
							mineFile.append(path).append(".min.js");
							destfile = path + ".js";
							fileListDir.append(path);
						} else if (i - jsPathIndex > 1) {
							filePath.append(path).append(prop.urlSeparator);
						}
					}
				}
			}
			filePath.append(value);

			if (jsPathIndex > -1) {
				var fileMap = resultMap.get(keyName);
				if (fileMap == null) {
					fileMap = new LinkedHashMap();
					resultMap.put(keyName, fileMap);
				}
				fileMap.put("destfile", destfile);
				fileMap.put("mineFile", mineFile.toString());
				fileMap.put("fileListDir", fileListDir.toString());
				var fileList = fileMap.get("fileList");
				if (fileList == null) {
					fileList = new ArrayList();
					fileMap.put("fileList", fileList);
				}
				fileList.add(filePath.toString());
			}
		}
	}
	if (depthList.size() > 0) {
		depthList.remove(depthList.size() - 1);
	}
}

function mergeFile(resultMap) {
	var scripts = new StringBuilder(50);
	var entrySet = resultMap.entrySet();
	var iterator = entrySet.iterator();
	var mergeKeys = new ArrayList();
	while (iterator.hasNext()) {
		var entry = iterator.next();
		var key = entry.getKey();
		var value = entry.getValue();

		// mergeFile
		if (arrayContains(prop.mergeDirs,key)>-1) {
			mergeKeys.add(key);
			continue;
		}
		
		var mineFile = value.get("mineFile");
		var fileListDir = value.get("fileListDir");
		var fileList = value.get("fileList");
		var destfile = value.get("destfile");

		scripts.append("<script src=\"").append(mineFile).append("\"></script>");

		// libFile
		if (arrayContains(prop.excludeDirs,key)>-1) {
			continue;
		}
		
		var compressFileKey="compression.file.flags";
		var antRefTable=project.getReferences();
		var fileCollection=antRefTable.get(compressFileKey);
		if(fileCollection==null){
			fileCollection=new ArrayList();
			antRefTable.put(compressFileKey,fileCollection);
		}
		if(fileCollection.contains(key)){
			continue;
		}
		fileCollection.add(key);
		
		var filelistDataType = project.createDataType("filelist");
		filelistDataType.setDir(new File(project.getBaseDir(), fileListDir));

		//merge the Files
		if(mergeKeys.size()>0){
			var mergeKeysListIterator = mergeKeys.iterator();
			while (mergeKeysListIterator.hasNext()) {
				var mergeKey=mergeKeysListIterator.next();
				var mergeValue=resultMap.get(mergeKey);
				var mergeFileListDir=mergeValue.get("fileListDir");
				var mergeFileList=mergeValue.get("fileList");
				
				var parentPath="";
				
				var parentFileListDirArray=fileListDir.split(prop.urlSeparator);
				for(var i=2;i<parentFileListDirArray.length;i+=1){
					parentPath+=".."+prop.urlSeparator;
				}

				var mergeFileListDirArray=mergeFileListDir.split(prop.urlSeparator);
				for(var i=2;i<mergeFileListDirArray.length;i+=1){
					parentPath+=mergeFileListDirArray[i]+prop.urlSeparator;
				}

				var fileListIterator = mergeFileList.iterator();
				while (fileListIterator.hasNext()) {
					var fileName = new FileList.FileName();
					fileName.setName(parentPath+fileListIterator.next());
					filelistDataType.addConfiguredFile(fileName);
				}
			}
			mergeKeys.clear();
		}
		
		var fileListIterator = fileList.iterator();
		while (fileListIterator.hasNext()) {
			var fileName = new FileList.FileName();
			fileName.setName(fileListIterator.next());
			filelistDataType.addConfiguredFile(fileName);
		}
		project.addReference("presetdef.script.filelist.refid", filelistDataType);
		var componentHelper = ComponentHelper.getComponentHelper(project);
		// project.setProperty();

		var destfileObject = new File(prop.proDir + prop.urlSeparator + destfile);
		var unknownElement = componentHelper.getDefinition("chenyuelin.name:mergeJs").getPreSets();
		var runtimeConfigurable = unknownElement.getWrapper();
		runtimeConfigurable.setAttribute("destfile", destfileObject.toString());
		unknownElement.perform();
		
		if (destfileObject.exists()) {
			var javaYuiCompressor = project.createTask("java");
			javaYuiCompressor.setJar(new File(project.getProperty("artifact.path.yuicompressor")));
			javaYuiCompressor.setFork(true);
			javaYuiCompressor.setFailonerror(true);
			var argumentLine = destfileObject.toString();
			argumentLine += " -o " + prop.proDir + prop.urlSeparator + destfile.substring(0, destfile.lastIndexOf("."));
			argumentLine += ".min" + destfile.substring(destfile.lastIndexOf("."));
			argumentLine += " --type "+prop.pathName;
			javaYuiCompressor.setArgs(argumentLine);
			// var argument=javaYuiCompressor.createJvmarg();
			// argument.setLine(argumentLine);
			javaYuiCompressor.perform();
			
			var deleteTask = project.createTask("delete"); deleteTask.setFile(destfileObject); 
			deleteTask.perform();
			 
		}
	}
	return scripts.toString();
}

function arrayContains(array,searchObject){
	var result=-1;
	for(var i=0;i<array.length;i+=1){
		if(array[i].equals(searchObject)){
			result=i;
			break;
		}
	}
	return result;
}