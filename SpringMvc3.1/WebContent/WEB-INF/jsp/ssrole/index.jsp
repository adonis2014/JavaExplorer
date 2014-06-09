<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title><spring:message code="ssrole.index.title"/></title>
</head>
<body>
<h1 align="center"><spring:message code="ssrole.index.heading1"/></h1>
<table align="center" border="1" width="100%">
	<thead>
		<tr>
			<td><spring:message code="ssrole.index.table.head.id"/></td>
			<td><spring:message code="ssrole.index.table.head.name"/></td>
			<td><spring:message code="ssrole.index.table.head.enabled"/></td>
			<td><spring:message code="ssrole.index.table.head.description"/></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="role" items="${ss_roles}">
			<tr>
				<td>${role.id}</td><td>${role.name}</td><td>${role.enabled}</td><td>${role.description}</td>
			</tr>
		</c:forEach>
	</tbody>
	<c:if test="${newRoleName!=null}">
		<h2 align="center">${newRoleName}添加成功</h2>
	</c:if>
</table>
<a href="create.htm">新建权限</a>
</body>
</html>