<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Calendar"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Calendar cal = Calendar.getInstance();
request.setAttribute("date", cal.getTime());
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E)");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 勤怠入力</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠入力</h1>
	</div>
	<div class="main_wrapper" align="center">
		<div class="time">
		<fmt:formatDate value="${date}" pattern="yyyy年MM月dd日（E）"/><br>
		<!-- 現在時刻をリアルタイム表示 -->
		<p id="RealtimeClockArea2"></p> 
		</div>
		<div class="clock_on_btn">
			<!-- 出勤ボタン -->
			<form action="/kintai/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="work_start">
				<input type="submit" value="出勤">
			</form>
			<!-- 退勤ボタン -->
			<form action="/kintai/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="work_finish">
				<input type="submit" value="退勤">
			</form>
			<!-- 休憩開始ボタン -->
			<form action="/kintai/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="break_start">
				<input type="submit" value="休憩開始">
			</form>
			<!-- 休憩終了ボタン -->
			<form action="/kintai/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="break_finish">
				<input type="submit" value="休憩終了">
			</form>
		</div>
		<div>
			<a href="/kintai/Forward?action=empMenu">メニューへ戻る</a>
		</div>
	</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
<script src="${pageContext.request.contextPath}/common/JS/func.js"></script>
</body>
</html>