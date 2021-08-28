<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.stream.Collectors,java.util.List,entity.MySchedule" %>
<%
MySchedule ms = (MySchedule)request.getAttribute("ms");
String index = (String)request.getAttribute("index");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 予定登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>予定詳細</h1>
	</div>
	<div class="main_wrapper" align="center">
			<p>件名　<%=ms.getSubject()%></p>
			<p>日付　<%= ms.getYear() %>年<%= ms.getMonth()%>月<%= ms.getDate() %>日</p>			
			<p>時間　<%if(ms.getFinishHour() != null){ %>
			<%= ms.getStartHour() %>:<%= ms.getStartMinute() %>〜<%= ms.getFinishHour() %>:<%= ms.getFinishMinute() %></p>				
			<% } else if(ms.getStartHour() != null) { %>
			<%= ms.getStartHour() %>:<%= ms.getStartMinute() %>〜</p>
			<% } else { %>
			指定なし
			<% } %>
			<p>色　 <span style="background:<%= ms.getColor() %>;">　</span></p>
			<p>メモ　<%= ms.getMemo()%></p>
			
		<a href="/kintai/AddSchedule?index=<%=index%>">修正</a><br>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>