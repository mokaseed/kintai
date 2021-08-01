<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Calendar"%>

<%
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E)");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠入力</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠入力</h1>
	</div>
	<div class="main_wrapper" align="center">
		<div class="time">
		<%= sdf.format(cal.getTime()) %><br>
		<!-- 現在時刻をリアルタイム表示 -->
		<p id="RealtimeClockArea2"></p> 
		</div>
		<div class="clock_on_btn">
			<!-- 出勤ボタン -->
			<form action="/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="work_start">
				<input type="submit" value="出勤">
			</form>
			<!-- 退勤ボタン -->
			<form action="/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="work_finish">
				<input type="submit" value="退勤">
			</form>
			<!-- 休憩開始ボタン -->
			<form action="/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="break_start">
				<input type="submit" value="休憩開始">
			</form>
			<!-- 休憩終了ボタン -->
			<form action="/ClockOn" method="POST">
				<input type="hidden" name="clockOn" value="break_finish">
				<input type="submit" value="休憩終了">
			</form>
<!-- 			<a href="cond.jsp?action=work_start">出勤</a>
			<a href="cond.jsp?action=work_finish">退勤</a><br>
			<a href="clockOnOK.jsp?action=break_start">休憩開始</a>
			<a href="clockOnOK.jsp?action=break_finish">休憩終了</a><br> -->
		</div>
	</div>
<jsp:include page="/footer.jsp" />
<script src="${pageContext.request.contextPath}/common/JS/func.js"></script>
</body>
</html>