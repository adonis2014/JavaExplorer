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
<h3 align="center">������</h3>
<form:form modelAttribute="person" method="post" action="">
������<form:input path="name" /><br/>
�Ա�<form:select path="sex">
<form:option value="male"/>
<form:option value="female"/>
</form:select><br/>
���գ�<form:input path="birthday" /><br/>
��ߣ�<form:input path="height" /><br/>
���ʱ�䣺<form:input path="breakfastTime" /><br/>
����ʱ�䣺<form:input path="createTime" /><br/>
нˮ��<form:input path="salary" /><br/>
��ע��<form:input path="note" /><br/>
<form:button>�ύ</form:button>
</form:form>

<form:form modelAttribute="person" method="post" action="form/2.htm" >
������<form:input path="name" /><br/>
�Ա�<form:select path="sex">
<form:option value="male"/>
<form:option value="female"/>
</form:select><br/>
���գ�<form:input path="birthday" /><br/>
��ߣ�<form:input path="height" /><br/>
���ʱ�䣺<form:input path="breakfastTime" /><br/>
����ʱ�䣺<form:input path="createTime" /><br/>
нˮ��<form:input path="salary" /><br/>
��ע��<form:input path="note" /><br/>
<form:button>�ύ</form:button>
</form:form>
</body>
</html>