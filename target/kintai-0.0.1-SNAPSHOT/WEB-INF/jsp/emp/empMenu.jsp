<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<%= request.getContextPath() + "/common/css/menu.css" %>" rel="stylesheet" type="text/css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 従業員メニュー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員メニュー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a class="menu_btn" href="<%= request.getContextPath() + "/ClockOn" %>">勤怠打刻</a><br>
		<a class="menu_btn" href="<%= request.getContextPath() + "/SelectWorkTimeList" %>">勤怠管理</a><br>
		<a class="menu_btn" href="<%= request.getContextPath() + "/MyCalendarMain" %>">スケジュール</a><br>
		<a class="menu_btn" href="<%= request.getContextPath() + "/EmpList" %>">従業員一覧</a><br>
		<a class="menu_btn" href="<%= request.getContextPath() + "/PersonalSettingsEmpMaster" %>">設定</a><br>
		<a class="menu_btn" href="<%= request.getContextPath() + "/Logout" %>">ログアウト</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>