<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.WorkTime,java.time.format.DateTimeFormatter" %>
<%
WorkTime workTime = (WorkTime)request.getAttribute("workTime");
DateTimeFormatter hFormat = DateTimeFormatter.ofPattern("HH");
DateTimeFormatter mFormat = DateTimeFormatter.ofPattern("mm");
DateTimeFormatter workDateFormat = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠修正</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠修正</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/UpdateWorkTime" method="POST">
			<table>
				<tr>
					<td>日付</td>
					<td><%= workTime.getWorkDate().format(workDateFormat) %></td>
					<input type="hidden" name="workDate" value="<%= workTime.getWorkDate() %>">
				</tr>
				<tr>
					<td>出勤時刻</td>
					<% if(workTime.getStartTime() != null){ %>
					<td><input type="tel" name="hStartTime" size="2" maxlength="2" value="<%= workTime.getStartTime().format(hFormat)%>" >:
					<input type="tel" name="mStartTime" size="2" maxlength="2" value="<%= workTime.getStartTime().format(mFormat) %>" ></td>
					<% } else { %>
					<td><input type="tel" name="hStartTime" size="2" maxlength="2">:
					<input type="tel" name="mStartTime" size="2" maxlength="2"></td>
					<% } %>
				</tr>
				<tr>
					<td>休憩開始時刻</td>
					<% if(workTime.getBreakStartTime() != null){ %>
					<td><input type="tel" name="hBreakStartTime" size="2" maxlength="2" value="<%= workTime.getBreakStartTime().format(hFormat) %>">:
					<input type="tel" name="mBreakStartTime" size="2" maxlength="2" value="<%= workTime.getBreakStartTime().format(mFormat) %>"></td>
					<% } else { %>
					<td><input type="tel" name="hBreakStartTime" size="2" maxlength="2">:
					<input type="tel" name="mBreakStartTime" size="2" maxlength="2"></td>
					<% } %>
				</tr>
				<tr>
					<td>休憩終了時刻</td>
					<% if(workTime.getBreakFinishTime() != null){ %>
					<td><input type="tel" name="hBreakFinishTime" size="2" maxlength="2" value="<%= workTime.getBreakFinishTime().format(hFormat)%>">:
					<input type="tel" name="mBreakFinishTime" size="2" maxlength="2" value="<%= workTime.getBreakFinishTime().format(mFormat) %>"></td>
					<% } else { %>
					<td><input type="tel" name="hBreakFinishTime" size="2" maxlength="2">:
					<input type="tel" name="mBreakFinishTime" size="2" maxlength="2"></td>
					<% } %>
				</tr>
				<tr>
					<td>退勤時刻</td>
					<% if(workTime.getFinishTime() != null){ %>
					<td><input type="tel" name="hFinishTime" size="2" maxlength="2" value="<%= workTime.getFinishTime().format(hFormat)%>">:
					<input type="tel" name="mFinishTime" size="2" maxlength="2" value="<%= workTime.getFinishTime().format(mFormat) %>"></td>
					<% } else { %>
					<td><input type="tel" name="hFinishTime" size="2" maxlength="2">:
					<input type="tel" name="mFinishTime" size="2" maxlength="2"></td>
					<% } %>
				</tr>
			</table>
			<input type="submit" value="修正する">
		</form>
	</div>
	<div align="center">
		<a href="javascript:history.back()">戻る</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>