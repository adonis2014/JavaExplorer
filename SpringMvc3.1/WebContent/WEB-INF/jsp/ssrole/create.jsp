<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GB18030"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>�½���ɫ</title>
</head>
<body>
	<form:form modelAttribute="ssRole" method="post" action="create">
		<form:label path="name">���ƣ�</form:label>
		<form:input path="name" /><form:errors path="neTypeId" cssStyle="color:red"/><br/>
		<label for="enabled1">�ǣ�</label>
		<form:radiobutton path="enabled" value="true" />
		<label for="enabled2">��</label>
		<form:radiobutton path="enabled" value="false" /><form:errors path="neTypeId" cssStyle="color:red"/><br/>
		<form:label path="description">������</form:label>
		<form:textarea path="description" /><form:errors path="neTypeId" cssStyle="color:red"/><br/>
		<input type="submit" />
	</form:form>
</body>
</html>