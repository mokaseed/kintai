<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
			<h1>勤怠管理</h1>
	</div>
	<div class="main_wrapper" align="center">
	<form action="/kintai/SelectWorkTimeList" method="POST">
	  <label for="request-month">タイムシート 年月選択</label><br>
	  <input type="month" name="request-month" id="request-month" value="<%= sdf.format(date) %>" required>
	  <button type="submit">表示する</button>
	</form>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>