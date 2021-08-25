<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.stream.Collectors,java.util.List" %>
<%
List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
List<Integer> dateNumbers = IntStream.range(1, 31).boxed().collect(Collectors.toList());
List<Integer> hourNumbers = IntStream.range(1, 24).boxed().collect(Collectors.toList());

String s_year = (String)request.getAttribute("year");
String s_month = (String)request.getAttribute("month");
int year = Integer.parseInt(s_year);
int month = Integer.parseInt(s_month);

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
		<h1>予定の登録</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/AddSchedule" method="post">
			<p>日付<select name="year">
				<option value="">-</option>
			<% for(int y : yearNumbers){ %>
				<option value="<%= y %>" <% if(y == year){ %>selected<% } %>><%= y %></option>
			<% } %>
			</select>
			<select name="month">
				<option value="">-</option>
			<% for(int m : monthNumbers){ %>
				<option value="<%= m %>" <% if(m == month){ %>selected<% } %>>
				<% if(String.valueOf(m).length() == 1){ %>
				<%= "0" + String.valueOf(m) %><% } else { %><%= m %><% } %>
				</option>
			<% } %>
			</select>
			<select name="date">
				<option value="">-</option>
			<% for(int d : dateNumbers){ %>
				<option value="<%= d %>" <% if(d == 99){ %>selected<% } %>>
				<% if(String.valueOf(d).length() == 1){ %>
				<%= "0" + String.valueOf(d) %><% } else { %><%= d %><% } %>
				</option>
			<% } %>
			</select></p>
			
			<p>開始時間<select name="startHour">
				<option value="">-</option>
			<% for(int h : hourNumbers){ %>
				<option value="<%= h %>" <% if(h == 99){ %>selected<% } %>>
				<% if(String.valueOf(h).length() == 1){ %>
				<%= "0" + String.valueOf(h) %><% } else { %><%= h %><% } %>
				</option>
			<% } %>
			</select>
			<select name="startMinute">
				<option value="">-</option>
			<% for(int min = 0; min <= 45; min = min += 15){ %>
				<option value="<%= min %>" <% if(min == 99){ %>selected<% } %>>
				<% if(String.valueOf(min).length() == 1){ %>
				<%= "0" + String.valueOf(min) %><% } else { %><%= min %><% } %>
				</option>
			<% } %>
			</select></p>
			
			<p>終了時間<select name="finishHour">
				<option value="">-</option>
			<% for(int h : hourNumbers){ %>
				<option value="<%= h %>" <% if(h == 99){ %>selected<% } %>>
				<% if(String.valueOf(h).length() == 1){ %>
				<%= "0" + String.valueOf(h) %><% } else { %><%= h %><% } %>
				</option>
			<% } %>
			</select>
			<select name="finishMinute">
				<option value="">-</option>
			<% for(int min = 0; min <= 45; min = min += 15){ %>
				<option value="<%= min %>" <% if(min == 99){ %>selected<% } %>>
				<% if(String.valueOf(min).length() == 1){ %>
				<%= "0" + String.valueOf(min) %><% } else { %><%= min %><% } %>
				</option>
			<% } %>
			</select></p>
			
			<p>色<select name="color">
				<option value="transparent">-</option>
				<option value="#ff7a60">赤</option>
				<option value="#ffff7a">黄</option>
				<option value="#abf9ff">青</option>
				<option value="#abf9a8">緑</option>
				<option value="#cd9fff">紫</option>
			</select></p>
			
			<p>件名<input type="text" name="subject" required></p>
			
			<p>メモ<textarea name="memo" wrap="hard"></textarea></p>
			
			<br><input type="submit" value="登録する">
		</form>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>