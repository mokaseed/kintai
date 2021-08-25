<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.MyCalendar" %>
<%
	MyCalendar mc = (MyCalendar)request.getAttribute("mc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カレンダーテスト</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center" id="container">
		<h1><%= mc.getYear() %>年<%= mc.getMonth() %>月カレンダー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>
			<a href="?year=<%= mc.getYear() %>&month=<%= mc.getMonth() - 1 %>">前月</a>
			<a href="?year=<%= mc.getYear() %>&month=<%= mc.getMonth() + 1 %>">翌月</a>
			<a href="/kintai/AddSchedule?year=<%= mc.getYear() %>&month=<%= mc.getMonth()%>">予定を追加</a>
		</p>
		<table border="1" style="border-collapse: collapse">
			<tr>
				<th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th>
			</tr>
			<% for(String[] rows : mc.getData()){ %>
			<tr>
				<% for(String col : rows){
						if(col.startsWith("*")){%>
							<td class="today" style="background-color:#;"><%= col.substring(1) %></td>
						<% } else if(col.startsWith("#")) {%>
							<td class="notThisMonth"><%= col.substring(1) %></td>
						<% } else { %>
							<td><%= col %></td>
						<% }} %>
			</tr>
			<tr>
				<% for(int i = 0; i < 7; i++){ %>
				<td><p></p></td>
				<% } %>
			</tr>
				<% } %>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/Forward?action=empMenu">メニューへ戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>