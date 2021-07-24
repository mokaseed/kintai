<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai  管理者メニュー</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>管理者メニュー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a href="sysadminEmpList.jsp?action=sysadminEmpList">従業員一覧</a><br>
		<a href="logout.jsp">ログアウト</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>