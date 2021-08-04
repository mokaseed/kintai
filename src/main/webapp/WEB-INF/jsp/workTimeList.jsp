<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar,java.util.List,entity.WorkTime,java.time.format.DateTimeFormatter" %>
<%
Calendar thisMonthCalendar = (Calendar)session.getAttribute("thisMonthCalendar");
List<WorkTime> workTimeList = (List<WorkTime>)session.getAttribute("workTimeList");

DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd");

thisMonthCalendar.add(Calendar.MONTH, -1);
int maxDay = thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
thisMonthCalendar.add(Calendar.MONTH, +1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠管理</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
			<h1><%= thisMonthCalendar.get(Calendar.YEAR) %>年<%= thisMonthCalendar.get(Calendar.MONTH) %>月分タイムシート</h1>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr>
				<th>日付</th><th>出勤</th><th>休憩開始</th><th>休憩終了</th><th>退勤</th>
			</tr>
			<% for(int i = 1; i <= maxDay; i++){ 
			 	Boolean chkDateFlag = false;
			%>
				<tr>
					<td>
						<%= thisMonthCalendar.get(Calendar.MONTH) + "月" + i + "日" %>
					</td>
					<%
					for(WorkTime workTime : workTimeList){
						String workDate = workTime.getWorkDate().format(dateFormat);
						if(Integer.parseInt(workDate) == i){
					%>
					<td>
						<% if(workTime.getStartTime() != null) { %>
						<%= workTime.getStartTime().format(timeFormat) %><% } %>
					</td>
					<td>
						<% if(workTime.getBreakStartTime() != null) { %>
						<%= workTime.getBreakStartTime().format(timeFormat) %><% } %>
					</td>
					<td>
						<% if(workTime.getBreakFinishTime() != null) { %>
						<%= workTime.getBreakFinishTime().format(timeFormat) %><% } %>
					</td>
					<td>
						<% if(workTime.getFinishTime() != null) { %>
						<%= workTime.getFinishTime().format(timeFormat) %><% } %>
					</td></tr>
					<%
						chkDateFlag = true;
						break;
						}
					}
					if(!chkDateFlag) {
					%>
					<td></td><td></td><td></td><td></td></tr>
			<% } }%>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/SelectWorkTimeList">年月選択に戻る</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>