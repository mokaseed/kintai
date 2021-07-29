<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Calendar,java.util.Date,java.time.LocalDateTime"%>

<%
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E)");
Date d = new Date();
SimpleDateFormat df = new SimpleDateFormat("HH:mm");
LocalDateTime now = LocalDateTime.now();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script type="text/javascript" src="/common/JS/func.js"></script> -->
<%-- <script src="${pageContext.request.contextPath}../common/JS/func.js"></script> --%>
<script type="text/javascript" src="../../common/JS/func.js"></script>
<title>Kintai 勤怠入力</title>
</head>
<body>
<%-- <script type="text/javascript" src="/common/JS/func.js"></script> 
<script src="${pageContext.request.contextPath}func.js"></script> --%>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠入力</h1>
	</div>
	<div class="main_wrapper" align="center">
		<div class="time">
		<%= sdf.format(cal.getTime()) %><br>
		<%-- リアルタイムではない時刻
		<%= df.format(d.getTime()) %> --%>
		<!-- 現在時刻をリアルタイム表示 -->
		<p id="RealtimeClockArea2"></p> 
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