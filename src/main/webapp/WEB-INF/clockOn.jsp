<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Calendar,java.util.Date"%>

<%
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E)");
Date d = new Date();
SimpleDateFormat df = new SimpleDateFormat("HH:mm");
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
		<%= df.format(d.getTime()) %>
		</div>
		<div class="clock_on_btn">
			<a href="cond.jsp?action=work_start">出勤</a>
			<a href="cond.jsp?action=work_finish">退勤</a><br>
			<a href="clockOnOK.jsp?action=break_start">休憩開始</a>
			<a href="clockOnOK.jsp?action=break_finish">休憩終了</a><br>
		</div>
	</div>
<jsp:include page="/footer.jsp" />
</body>
</html>