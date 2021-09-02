<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.stream.Collectors,java.util.List,entity.MySchedule,java.util.Objects,java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
List<Integer> dateNumbers = IntStream.range(1, 32).boxed().collect(Collectors.toList());
List<Integer> hourNumbers = IntStream.range(1, 25).boxed().collect(Collectors.toList());

MySchedule ms = (MySchedule)request.getAttribute("ms");

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
				<option value="<%= y %>" <% if(String.valueOf(y).equals(ms.getYear())){ %>selected<% } %> ><%= y %></option>
			<% } %>
			</select>
			<select name="month">
				<option value="">-</option>
				<% for(int m : monthNumbers){ 
					String month = null;
					if(String.valueOf(m).length() == 1){
					month = "0" + String.valueOf(m); } else { month = String.valueOf(m); } %>
					<option value="<%= m %>" <% if(month.equals(ms.getMonth())){ %>selected<% } %>>
					<%= month %>
				</option>
			<% } %>
			</select>
			<select name="date">
				<option value="">-</option>
				<% for(int d : dateNumbers){ 
					String date = null;
					if(String.valueOf(d).length() == 1){
					date = "0" + String.valueOf(d); } else { date = String.valueOf(d); } %>
					<option value="<%= d %>" <% if(date.equals(ms.getDate())){ %>selected<% } %>>
					<%= date %>
				</option>
			<% } %>
			</select></p>
			
			<p>時間<select name="startHour">
				<option value="">-</option>
			<% for(int h : hourNumbers){ 
					String hour = null;
					if(String.valueOf(h).length() == 1){
					hour = "0" + String.valueOf(h); } else { hour = String.valueOf(h); } %>
					<option value="<%= h %>" <% if(hour.equals(ms.getStartHour())){ %>selected<% } %>>
					<%= hour %>
				</option>
			<% } %>
			</select>
			<select name="startMinute">
				<option value="">-</option>
			<% for(int min = 0; min <= 45; min = min += 15){ 
					String minute = null;
					if(String.valueOf(min).length() == 1){
					minute = "0" + String.valueOf(min); } else { minute = String.valueOf(min); } %>
					<option value="<%= min %>" <% if(minute.equals(ms.getStartMinute())){ %>selected<% } %>>
					<%= minute %>
				</option>
			<% } %>
			</select>
			
			〜<select name="finishHour">
				<option value="">-</option>
			<% for(int h : hourNumbers){ 
					String hour = null;
					if(String.valueOf(h).length() == 1){
					hour = "0" + String.valueOf(h); } else { hour = String.valueOf(h); } %>
					<option value="<%= h %>" <% if(hour.equals(ms.getFinishHour())){ %>selected<% } %>>
					<%= hour %>
				</option>
			<% } %>
			</select>
			<select name="finishMinute">
				<option value="">-</option>
			<% for(int min = 0; min <= 45; min = min += 15){ 
					String minute = null;
					if(String.valueOf(min).length() == 1){
					minute = "0" + String.valueOf(min); } else { minute = String.valueOf(min); } %>
					<option value="<%= min %>" <% if(minute.equals(ms.getFinishMinute())){ %>selected<% } %>>
					<%= minute %>
				</option>
			<% } %>
			</select></p>
			
			<p>色
				
				<label><input type="radio" name="color" value="transparent" <% if(Objects.equals(ms.getColor(), "transparent")){ %>checked <% } %>> 無 </label> 
				<label><input type="radio" name="color"  value="rgba(250,60,60,0.4)" <% if(Objects.equals(ms.getColor(), "rgba(250,60,60,0.4)")){ %>checked <% } %>><span style="background-color:rgba(250,60,60,0.4);">　</span></label> 
				<label><input type="radio" name="color"  value="#ffff7a" <% if(Objects.equals(ms.getColor(), "#ffff7a")){ %>checked <% } %>><span style="background-color:#ffff7a;">　</span></label> 
				<label><input type="radio" name="color"  value="#abf9ff" <% if(Objects.equals(ms.getColor(), "#abf9ff")){ %>checked <% } %>><span style="background-color:#abf9ff;">　</span></label> 
				<label><input type="radio" name="color"  value="#abf9a8" <% if(Objects.equals(ms.getColor(), "#abf9a8")){ %>checked <% } %>><span style="background-color:#abf9a8;">　</span></label> 
				<label><input type="radio" name="color"  value="rgba(212,60,250,0.4)" <% if(Objects.equals(ms.getColor(), "rgba(212,60,250,0.4)")){ %>checked <% } %>><span style="background-color:rgba(212,60,250,0.4);">　</span></label> 
			</p>
			
			<p>件名<input type="text" name="subject" value="<%=ms.getSubject()%>" required></p>
			
			<p>メモ<textarea name="memo" wrap="hard"><%=ms.getMemo()%></textarea></p>
			
			<input type="hidden" name="skdId" value="<%=ms.getSkdId()%>">
			<br><input type="submit" value="登録する">
		</form>
		<% if(ms.getSubject().length() != 0){ %>
		<form action="/kintai/AddSchedule" method="post">
			<input type="hidden" name="action" value="delete">
			<input type="hidden" name="skdId" value="<%=ms.getSkdId()%>">
			<input type="submit" value="削除">
		</form>			
		<% } %>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html> --%>

<%
List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
request.setAttribute("yearNumbers", yearNumbers);
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
request.setAttribute("monthNumbers", monthNumbers);
List<Integer> dateNumbers = IntStream.range(1, 32).boxed().collect(Collectors.toList());
request.setAttribute("dateNumbers", dateNumbers);
List<Integer> hourNumbers = IntStream.range(1, 25).boxed().collect(Collectors.toList());
request.setAttribute("hourNumbers", hourNumbers);
List<String> minuteNumbers = Arrays.asList("00", "15", "30", "45");
request.setAttribute("minuteNumbers", minuteNumbers);

MySchedule ms = (MySchedule)request.getAttribute("ms");
%>
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
			日付
			<!-- 年 -->
			<select name="year">
				<c:set var="selectYear" value="${ms.year}"/>
				<option value="">-</option>
				<c:forEach var="year" items="${yearNumbers}">
					<c:choose>
						<c:when test="${year == selectYear}">
							<option value="${year}" selected><c:out value="${year}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${year}"><c:out value="${year}"/></option>
						</c:otherwise>						
					</c:choose>
				</c:forEach>
			</select>年
			<!-- 月 -->
			<select name="month">
				<c:set var="selectMonth" value="${ms.month}"/>
				<option value="">-</option>
				<c:forEach var="month" items="${monthNumbers}">
					<c:choose>
						<c:when test="${month == selectMonth}">
							<option value="${month}" selected><c:out value="${month}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${month}"><c:out value="${month}"/></option>
						</c:otherwise>						
					</c:choose>
				</c:forEach>
			</select>月
			<!-- 日 -->
			<select name="date">
				<c:set var="selectDate" value="${ms.date}"/>
				<option value="">-</option>
				<c:forEach var="date" items="${dateNumbers}">
					<c:choose>
						<c:when test="${date == selectDate}">
							<option value="${date}" selected><c:out value="${date}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${date}"><c:out value="${date}"/></option>
						</c:otherwise>						
					</c:choose>
				</c:forEach>
			</select>日
			
			<br>
			時間
			<!-- 開始時間の時 -->
			<select name="startHour">
				<c:set var="selectStartHour" value="${ms.startHour}"/>
				<option value="">-</option>
				<c:forEach var="hour" items="${hourNumbers}">
					<c:choose>
						<c:when test="${hour == selectStartHour}">
							<option value="${hour}" selected><c:out value="${hour}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${hour}"><c:out value="${hour}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>:
			<!-- 開始時間の分 -->
			<select name="startMinute">
				<c:set var="selectStartMinute" value="${ms.startMinute}"/>
				<option value="">-</option>
				<c:forEach var="minute" items="${minuteNumbers}">
					<c:choose>
						<c:when test="${minute == selectStartMinute}">
							<option value="${minute}" selected><c:out value="${minute}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${minute}"><c:out value="${minute}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			
			〜
			
			<!-- 終了時間の時 -->
			<select name="finishHour">
				<c:set var="selectFinishHour" value="${ms.finishHour}"/>
				<option value="">-</option>
				<c:forEach var="hour" items="${hourNumbers}">
					<c:choose>
						<c:when test="${hour == selectStartHour}">
							<option value="${hour}" selected><c:out value="${hour}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${hour}"><c:out value="${hour}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>:
			<!-- 終了時間の分 -->
			<select name="finishMinute">
				<c:set var="selectFinishMinute" value="${ms.finishMinute}"/>
				<option value="">-</option>
				<c:forEach var="minute" items="${minuteNumbers}">
					<c:choose>
						<c:when test="${minute == selectStartMinute}">
							<option value="${minute}" selected><c:out value="${minute}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${minute}"><c:out value="${minute}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			
			<p>色
			<c:choose>
				<c:when test="${ms.color == 'transparent'}">
					<label><input type="radio" name="color" value="transparent" checked > 無 </label>
				</c:when>
				<c:when test="${ms.color == null}">
					<label><input type="radio" name="color" value="transparent" checked > 無 </label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="color" value="transparent"> 無 </label>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ms.color == 'rgba(250,60,60,0.4)'}">
					<label><input type="radio" name="color" value="rgba(250,60,60,0.4)" checked ><span style="background-color:rgba(250,60,60,0.4);">　</span></label>
				</c:when>
				<c:when test="${ms.color == null}">
					<label><input type="radio" name="color" value="rgba(250,60,60,0.4)" checked ><span style="background-color:rgba(250,60,60,0.4);">　</span></label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="color" value="rgba(250,60,60,0.4)"><span style="background-color:rgba(250,60,60,0.4);">　</span></label>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ms.color == '#ffff7a'}">
					<label><input type="radio" name="color" value="#ffff7a" checked ><span style="background-color:#ffff7a;">　</span></label>
				</c:when>
				<c:when test="${ms.color == null}">
					<label><input type="radio" name="color" value="#ffff7a" checked ><span style="background-color:#ffff7a;">　</span></label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="color" value="#ffff7a"><span style="background-color:#ffff7a;">　</span></label>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ms.color == '#abf9ff'}">
					<label><input type="radio" name="color" value="#abf9ff" checked ><span style="background-color:#abf9ff;">　</span></label>
				</c:when>
				<c:when test="${ms.color == null}">
					<label><input type="radio" name="color" value="#abf9ff" checked ><span style="background-color:#abf9ff;">　</span></label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="color" value="#abf9ff"><span style="background-color:#abf9ff;">　</span></label>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ms.color == '#abf9a8'}">
					<label><input type="radio" name="color" value="#abf9a8" checked ><span style="background-color:#abf9a8;">　</span></label>
				</c:when>
				<c:when test="${ms.color == null}">
					<label><input type="radio" name="color" value="#abf9a8" checked ><span style="background-color:#abf9a8;">　</span></label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="color" value="#abf9a8"><span style="background-color:#abf9a8;">　</span></label>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ms.color == 'rgba(212,60,250,0.4)'}">
					<label><input type="radio" name="color" value="rgba(212,60,250,0.4)" checked ><span style="background-color:rgba(212,60,250,0.4);">　</span></label>
				</c:when>
				<c:when test="${ms.color == null}">
					<label><input type="radio" name="color" value="rgba(212,60,250,0.4)" checked ><span style="background-color:rgba(212,60,250,0.4);">　</span></label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="color" value="rgba(212,60,250,0.4)"><span style="background-color:rgba(212,60,250,0.4);">　</span></label>
				</c:otherwise>
			</c:choose>
			</p>
			
			<p>件名<input type="text" name="subject" value="${ms.subject}" required></p>
			
			<p>メモ<textarea name="memo" wrap="hard">${ms.memo}</textarea></p>
			
			<input type="hidden" name="skdId" value="${ms.skdId}">
			<br><input type="submit" value="登録する">
		</form>
		<c:if test="${ms.subject == null}">
			<form action="/kintai/AddSchedule" method="post">
				<input type="hidden" name="action" value="delete">
				<input type="hidden" name="skdId" value="${ms.skdId}">
				<input type="submit" value="削除">
			</form>
		</c:if>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>