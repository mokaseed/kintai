<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<a href="/kintai/ClockOn">勤怠打刻</a><br>
		<a href="/kintai/SelectWorkTimeList">勤怠管理</a><br>
		<a href="/kintai/MyCalendarMain">スケジュール</a><br>
		<a href="/kintai/EmpList">従業員一覧</a><br>
		<a href="/kintai/PersonalSettingsEmpMaster">設定</a><br>
		<a href="/kintai/Logout">ログアウト</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>