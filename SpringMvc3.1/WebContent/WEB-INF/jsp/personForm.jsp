<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>Sample Form</title>
</head>
<body>
<h3 align="center">人员信息上传</h3>

<form:form method="post" action="personUpload" modelAttribute="personUploadInformation" enctype="multipart/form-data">
<form:hidden path="id"/>
<form:input path="name" />
<input type="file" name="uploadFile" accept="image/*"/>
<input type="file" name="uploadFile"/>
<input type="submit"/>
</form:form>
</body>
</html>