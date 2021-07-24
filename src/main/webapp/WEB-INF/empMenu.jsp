<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 従業員メニュー</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員メニュー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<a href="clockOn.jsp?action=clockOn">勤怠打刻</a><br>
		<a href="mgmtAttendance.jsp?action=mgmtAttendance">勤怠管理</a><br>
		<a href="schedule.jsp?action=schedule">スケジュール</a><br>
		<a href="empList.jsp?action=empList">従業員一覧</a><br>
		<a href="empConfig.jsp?action=empConfig">設定</a><br>
		<a href="logout.jsp">ログアウト</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>