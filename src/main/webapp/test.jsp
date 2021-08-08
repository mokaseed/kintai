<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.List,java.lang.Integer,java.util.stream.Collectors,java.util.Calendar" %>
<%
List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
Calendar thisMonthCalendar = (Calendar)session.getAttribute("thisMonthCalendar");
/* request.setAttribute("numbers", numbers);
String selectedNumbers = "2025";
request.setAttribute("selectedNumbers", selectedNumbers);
IntStream month = IntStream.range(1, 13);  */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
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
</form>
</body>
</html>