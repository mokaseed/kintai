<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai TOP</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>TOPページ</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a href="empLogin.jsp?action=done">従業員ログイン</a><br>
		<a href="sysadminLogin.jsp">管理者ログイン</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>