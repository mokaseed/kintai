<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/top.css" %>" rel="stylesheet" type="text/css" />
<title>Kintai TOP</title>
</head>
<body>
<header>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
</header>
<div class="title">
	<h1>Kintai TOP</h1>
</div>
<div class="main_wrapper">
	<a class="menu_btn" href="<%= request.getContextPath() + "/Login?action=done" %>">従業員ログイン</a><br>
	<a class="menu_btn" href="<%= request.getContextPath() + "/Login" %>">管理者ログイン</a>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>