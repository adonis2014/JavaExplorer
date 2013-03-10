<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>Person Management</title>
<link href="/static/lib/jquery-ui-1.10.0/css/redmond/jquery-ui-1.10.0.custom.min.css" rel="stylesheet" type="text/css" charset="utf-8"/>
<link href="/static/lib/jquery_fixheadertable_2.0/base.css" rel="stylesheet" type="text/css" charset="utf-8"/>
<!--[if lt IE 8]>
<script src="/static/lib/IE8.js"></script>
<![endif]-->
<script type="text/javascript" src="/static/lib/jquery.min.js"></script>
<script type="text/javascript" src="/static/lib/jquery-ui-1.10.0/js/jquery-ui-1.10.0.custom.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/static/lib/underscore-min.js"></script>
<script type="text/javascript" src="/static/lib/backbone-min.js"></script>
<script type="text/javascript" src="/static/lib/utils.js"></script>
<script type="text/javascript" src="/static/lib/jquery_fixheadertable_2.0/jquery.fixheadertable.min.js"></script>
<script type="text/javascript" src="/static/sp3.1/management/person.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<h1 align="center">��Ա����</h1>
	<table id="personTable">
		<thead>
			<tr>
				<th>id</th>
				<th>����</th>
				<th>�ձ�</th>
				<th>����</th>
				<th>���ʱ��</th>
				<th>����ʱ��</th>
				<th>���</th>
				<th>����</th>
				<th>����</th>
				<th>��ע</th>
				<th>�汾</th>
				<th>����</th>
				<th>����</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="dialog-edit-person" title="Edit person information">
	</div>
	<div id="cacheable"><input type="text"/><button type="button">����</button></div>
	<div id="deleteCache"><input type="text"/><button type="button">ɾ��ָ������</button></div>
	<div id="deleteCacheAll"><button type="button">ɾ�����л���</button></div>
	<spring:message code="error.requirement.name"/>
</body>
</html>