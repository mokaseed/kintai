<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/kintai/common/css/menu.css" rel="stylesheet" type="text/css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kintai 管理者 - 管理者メニュー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>管理者メニュー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a class="menu_btn" href="/kintai/SysadminEmpList">従業員一覧</a><br>
		<a class="menu_btn" href="/kintai/DeptMaster">事業部管理</a><br>
		<a class="menu_btn" href="/kintai/Logout">ログアウト</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>