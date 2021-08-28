<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai  管理者メニュー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>管理者メニュー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a href="/kintai/SysadminEmpList">従業員一覧</a><br>
		<a href="/kintai/DeptMaster">事業部管理</a><br>
		<a href="/kintai/Logout">ログアウト</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>