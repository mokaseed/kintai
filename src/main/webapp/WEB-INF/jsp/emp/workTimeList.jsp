<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar,java.util.List,entity.WorkTime,java.time.format.DateTimeFormatter,java.text.DecimalFormat,java.util.stream.IntStream,java.util.stream.Collectors,model.WorkTimeListTimeCheck" %>
<%
Calendar thisMonthCalendar = (Calendar)session.getAttribute("thisMonthCalendar");
List<WorkTime> workTimeList = (List<WorkTime>)session.getAttribute("workTimeList");

DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd");
/* DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MM"); */
DecimalFormat mFormat= new DecimalFormat("00");

thisMonthCalendar.add(Calendar.MONTH, -1);
int maxDay = thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
thisMonthCalendar.add(Calendar.MONTH, +1);

List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1><%= thisMonthCalendar.get(Calendar.YEAR) %>年<%= thisMonthCalendar.get(Calendar.MONTH) %>月分タイムシート</h1>
	</div>
	<div align="center">
		<form action="/kintai/SelectWorkTimeList" method="post">
			<select name="selectY">
			<% for(int y : yearNumbers){ %>
				<option value="<%= y %>" <% if(y == thisMonthCalendar.get(Calendar.YEAR)){ %>selected<% } %>><%= y %></option>
			<% } %>
			</select>
			<select name="selectM">
			<% for(int m : monthNumbers){ %>
				<option value="<%= m %>" <% if(m == thisMonthCalendar.get(Calendar.MONTH)){ %>selected<% } %>><%= m %></option>
			<% } %>
			</select>
			<input type="submit" value="表示">
		</form>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr>
				<th>日付</th><th>出勤</th><th>休憩開始</th><th>休憩終了</th><th>退勤</th><th>修正</th>
			</tr>
			<% for(int i = 1; i <= maxDay; i++){ 
			 	Boolean chkDateFlag = false;
			%>
			<%  //タイムシートに記録されている時間が「出勤時間<休憩開始時間＜休憩終了時間＜退勤時間」になっているかチェック
				//falseの場合は列を赤く表示
				WorkTimeListTimeCheck dateTest = new WorkTimeListTimeCheck();
				boolean flag = dateTest.execute(workTimeList, i);
				if(flag){ %>
					<tr>
				<% } else { %>
					<tr style="background-color:red;">
				<% } %>
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
					</td>
					<td>
						<form action="/kintai/UpdateWorkTime">
							<input type="hidden" name="workDate" value="<%= thisMonthCalendar.get(Calendar.YEAR) + "-" + mFormat.format(thisMonthCalendar.get(Calendar.MONTH)) + "-" + workTime.getWorkDate().format(dateFormat) %>">
							<input type="hidden" name="startTime" value="<%= workTime.getStartTime() %>">
							<input type="hidden" name="breakStartTime" value="<%= workTime.getBreakStartTime() %>">
							<input type="hidden" name="breakFinishTime" value="<%= workTime.getBreakFinishTime() %>">
							<input type="hidden" name="finishTime" value="<%= workTime.getFinishTime() %>">
							<input type="submit" value="修正">
						</form>
					</td></tr>
					<%
						chkDateFlag = true;
						break;
						}
					}
					if(!chkDateFlag) {
					%>
					<td></td><td></td><td></td><td></td>
					<td>
						<form action="/kintai/UpdateWorkTime">
							<%
							String date;
							if(String.valueOf(i).length() == 1){
								date = "0" + i;
							} else {
								date = String.valueOf(i);
							}
							%>
							<input type="hidden" name="workDate" value="<%= thisMonthCalendar.get(Calendar.YEAR) + "-" + mFormat.format(thisMonthCalendar.get(Calendar.MONTH)) + "-" + date %>">
							<input type="hidden" name="startTime" value="">
							<input type="hidden" name="breakStartTime" value="">
							<input type="hidden" name="breakFinishTime" value="">
							<input type="hidden" name="finishTime" value="">
							<input type="submit" value="修正">
						</form>
					</td></tr>
			<% } }%>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>