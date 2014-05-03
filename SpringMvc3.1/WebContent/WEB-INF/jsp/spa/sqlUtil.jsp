<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>sqlUtil</title>
<script type="text/javascript" src="/static/js/lib/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
constant = window.constant || {};

constant.servletPath = location.pathname.substring(0, location.pathname.indexOf("/management/sqlUtil"));

$().ready(function(){
	$("#digestPassword>:button").click(function(event){
		var $input=$(this).prev();
		$.get(constant.servletPath+"/sqlUtil/digestPassword/"+encodeURIComponent($input.val()),function(data){
			alert(data);
		},"text");
	});
	$("#digestOldPassword>:button").click(function(event){
		var $input=$(this).prev();
		$.get(constant.servletPath+"/sqlUtil/digestOldPassword/"+encodeURIComponent($input.val()),function(data){
			alert(data);
		},"text");
	});
});
</script>
</head>
<body>
<div id="digestPassword"><input type="text"/><button type="button">算密码</button></div>
<div id="digestOldPassword"><input type="text"/><button type="button">算密码（旧）</button></div>
</body>
</html>