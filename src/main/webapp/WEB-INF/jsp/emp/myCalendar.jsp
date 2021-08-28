<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.MyCalendar,entity.MySchedule,java.util.List,java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	MyCalendar mc = (MyCalendar)request.getAttribute("mc");
	List<MySchedule> myScheduleList = (List<MySchedule>)session.getAttribute("myScheduleList");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
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
						if(col.startsWith("t")){%>
							<td class="today" style="background-color:#;"><%= col.substring(1) %></td>
						<% } else if(col.startsWith("b")) {%>
							<td class="before"><%= col.substring(1) %></td>
						<% } else if(col.startsWith("a")) {%>
							<td class="after"><%= col.substring(1) %></td>
						<% } else { %>
							<td><%= col %></td>
						<% }} %>
			</tr>
			<tr>
				<% for(String col : rows){ %>
				<td>
					<%
					String date = null; //カレンダーのそのマスの年月日
					String MM = null; //カレンダーのそのマスの月
					String dd = null;//カレンダーのそのマスの日
					int index = 0;
					
					for(MySchedule ms : myScheduleList){ 
						index ++;
							//日付に印が付いている場合は印を除く
							//日付が1桁の場合は0をつけて二桁にする
							if(col.startsWith("b") || col.startsWith("a") || col.startsWith("t")){
								dd =  col.substring(1);
								if(dd.length() == 1){
									dd =  "0" + col.substring(1);
								}
								//日付に”b”が付いている場合は前月
								if(col.startsWith("b")){
									if(String.valueOf(mc.getLastMonth()).length() == 1){
										MM = "0" + mc.getLastMonth();
									} else {
										MM = String.valueOf(mc.getLastMonth());
									}
									date = mc.getLastYear() + "-" + MM + "-" + dd;
								//日付に”a”が付いている場合は次月
								} else if(col.startsWith("a")) {
									if(String.valueOf(mc.getNextMonth()).length() == 1){
										MM = "0" + mc.getNextMonth();
									} else {
										MM = String.valueOf(mc.getNextMonth());
									}
									date = mc.getNextYear() + "-" + MM + "-" + dd;
								//日付に”t”が付いている場合は当月
								} else {
									if(String.valueOf(mc.getMonth()).length() == 1){
										MM = "0" + mc.getMonth();
									} else {
										MM = String.valueOf(mc.getMonth());
									}
									date = mc.getYear() + "-" + MM + "-" + dd;
								}
							//日付に印が付いていない場合は当月
							} else {
								if(String.valueOf(mc.getMonth()).length() == 1){
									MM = "0" + mc.getMonth();
								} else {
									MM = String.valueOf(mc.getMonth());
								}
								if(col.length() == 1){
									dd = "0" + String.valueOf(Integer.parseInt(col));
								} else {
									dd = String.valueOf(Integer.parseInt(col));
								}
								date = mc.getYear() + "-" + MM + "-" + dd;
							} 
							
							//日付が一致するスケジュールを表示
							if(ms.getSkdDate().format(dateFormat).equals(date)){ 
								if(ms.getSkdStartTime() == null) { %>
									<div style="background-color:<%=ms.getColor()%>;"><a style="color:black;text-decoration:none;" href="/kintai/AddSchedule?action=done&index=<%=index - 1%>"><%= "・" + ms.getSubject() %></a></div>
								<% } else if(ms.getSkdFinishTime() == null) { %>
									<div style="background-color:<%=ms.getColor()%>;"><%= ms.getSkdStartTime() %><br><a style="color:black;text-decoration:none;" href="/kintai/AddSchedule?action=done&index=<%=index - 1%>"><%= ms.getSubject() %></a></div>
								<% } else { %>
									<div style="background-color:<%=ms.getColor()%>;"><%= ms.getSkdStartTime() + "~" + ms.getSkdFinishTime()%><br><a style="color:black;text-decoration:none;" href="/kintai/AddSchedule?action=done&index=<%=index - 1%>"><%= ms.getSubject() %></a></div>
							  <% }
							} else { %>
								<p></p>
						<% } 
					 } %>
					 <form action="/kintai/AddSchedule">
					 	<input type="image" name="date" value="<%=date%>" src="/kintai/common/png/addSchedule.png" alt="予定を追加" width="20" height="20">
					 </form>
				</td>
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