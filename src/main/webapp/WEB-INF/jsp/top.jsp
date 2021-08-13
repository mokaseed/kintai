<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai TOP</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>TOPページ</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a href="/kintai/Login?action=done">従業員ログイン</a><br>
		<a href="/kintai/Login">管理者ログイン</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>