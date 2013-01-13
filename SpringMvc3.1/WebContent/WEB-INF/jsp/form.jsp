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
<h3 align="center">表单测试</h3>
<form:form modelAttribute="person" method="post" action="">
姓名：<form:input path="name" /><br/>
性别：<form:select path="sex">
<form:option value="male"/>
<form:option value="female"/>
</form:select><br/>
生日：<form:input path="birthday" /><br/>
身高：<form:input path="height" /><br/>
早餐时间：<form:input path="breakfastTime" /><br/>
创建时间：<form:input path="createTime" /><br/>
薪水：<form:input path="salary" /><br/>
备注：<form:input path="note" /><br/>
<form:button>提交</form:button>
</form:form>

<form:form modelAttribute="person" method="post" action="form/2.htm" >
姓名：<form:input path="name" /><br/>
性别：<form:select path="sex">
<form:option value="male"/>
<form:option value="female"/>
</form:select><br/>
生日：<form:input path="birthday" /><br/>
身高：<form:input path="height" /><br/>
早餐时间：<form:input path="breakfastTime" /><br/>
创建时间：<form:input path="createTime" /><br/>
薪水：<form:input path="salary" /><br/>
备注：<form:input path="note" /><br/>
<form:button>提交</form:button>
</form:form>
</body>
</html>