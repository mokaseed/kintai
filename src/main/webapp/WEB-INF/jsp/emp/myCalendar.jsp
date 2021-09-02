<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.MyCalendar,entity.MySchedule,java.util.List,java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<h1>${mc.year}年${mc.month}月カレンダー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>
			<a href="?year=${mc.year}&month=${mc.month - 1}">前月</a>
			<a href="?year=${mc.year}&month=${mc.month + 1}">翌月</a>
			<a href="/kintai/AddSchedule?year=${mc.year}&month=${mc.month}">予定を追加</a>
		</p>
		
		<!-- カレンダーを表示 -->
		<table border="1" style="border-collapse: collapse">
			<tr>
				<th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th>
			</tr>
			<c:forEach var="rows" items="${mc.data}">
				<tr>
					<c:forEach var="col" items="${rows}">
						<!-- カレンダーの日数を表示（印をつけたものは印を外す） -->
						<c:choose>
							<c:when test="${fn:startsWith(col, 't')}">
								<td class="today" style="background-color:#;">${fn:substring(col, 1, -1)}</td>
							</c:when>
							<c:when test="${fn:startsWith(col, 'b')}">
								<td class="before">${fn:substring(col, 1, -1)}</td>
							</c:when>
							<c:when test="${fn:startsWith(col, 'a')}">
								<td class="after">${fn:substring(col, 1, -1)}</td>
							</c:when>
							<c:otherwise><td>${col}</td></c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="col" items="${rows}">
					<td>
 					<c:set var="index" value="0"/>
						<%-- <c:forEach var="ms" items="${myScheduleList}"> --%>
						<c:forEach var="ms" items="${sessionScope.myScheduleList}">
							<c:set var="index" value="${index + 1}"/>
							<!-- そのマスが何年何月何日であるかを調べ、変数dateに代入する -->
							<c:choose>
								<c:when test="${fn:startsWith(col, 't') or fn:startsWith(col, 'b') or fn:startsWith(col, 'a')}">
									<c:set var="dd" value="${fn:substring(col, 1, -1)}"/>
									<c:if test="${ fn:length(dd) == 1 }">
										<c:set var="dd">0${dd}</c:set>
									</c:if>
									<c:choose>
										<c:when test="${fn:startsWith(col, 'b')}">
											<c:set var="lastMonth" value="${mc.lastMonth}"/>
											<c:choose>
												<c:when test="${ fn:length(lastMonth) == 1 }">
													<c:set var="MM">0${lastMonth}</c:set>
												</c:when>
												<c:otherwise>
													<c:set var="MM" value="${lastMonth}" />
												</c:otherwise>
											</c:choose>
											<c:set var="date">${mc.lastYear}-${MM}-${dd}</c:set>
										</c:when>
										<c:when test="${fn:startsWith(col, 'a')}">
											<c:set var="nextMonth" value="${mc.nextMonth}"/>
											<c:choose>
												<c:when test="${fn:length(nextMonth) == 1}">
													<c:set var="MM">0${nextMonth}</c:set>
												</c:when>
												<c:otherwise>
													<c:set var="MM" value="${nextMonth}" />
												</c:otherwise>
											</c:choose>
											<c:set var="date">${mc.nextYear}-${MM}-${dd}</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="month" value="${mc.month}"/>
											<c:choose>
												<c:when test="${fn:length(month) == 1}">
													<c:set var="MM">0${month}</c:set>
												</c:when>
												<c:otherwise>
													<c:set var="MM" value="${month}" />
												</c:otherwise>
											</c:choose>
											<c:set var="date">${mc.year}-${MM}-${dd}</c:set>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:set var="dd" value="${col}"/>
									<c:if test="${ fn:length(dd) == 1 }">
										<c:set var="dd">0${dd}</c:set>
									</c:if>
									<c:set var="month" value="${mc.month}"/>
									<c:choose>
										<c:when test="${fn:length(month) == 1}">
											<c:set var="MM">0${month}</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="MM" value="${month}" />
										</c:otherwise>
									</c:choose>
									<c:set var="date">${mc.year}-${MM}-${dd}</c:set>
								</c:otherwise>
							</c:choose>
							
							<!-- 日付が一致するスケジュールを表示 -->
							<c:choose>
								<c:when test="${ms.skdDate == date}">
									<c:choose>
										<c:when test="${skdStartTime == null}">
											<div style="background-color:${ms.color};"><a style="color:black;text-decoration:none;" href="/kintai/AddSchedule?action=done&index=${index - 1}">・${ms.subject}</a></div>
										</c:when>
										<c:when test="${skdFinishTime == null}">
											<div style="background-color:${ms.color};">${ms.skdStartTime}<br><a style="color:black;text-decoration:none;" href="/kintai/AddSchedule?action=done&index=${index - 1}">${ms.subject}</a></div>
										</c:when>
										<c:otherwise>
											<div style="background-color:${ms.color};">${ms.skdStartTime}~${ms.skdFinishTime}<br><a style="color:black;text-decoration:none;" href="/kintai/AddSchedule?action=done&index=${index - 1}">${ms.subject}</a></div>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									　
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<form action="/kintai/AddSchedule">
					 	<input type="image" name="date" value="${date}" src="/kintai/common/png/addSchedule.png" alt="予定を追加" width="20" height="20">
					 	</form>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<%-- <table border="1" style="border-collapse: collapse">
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
		</table> --%>
	</div>
	<div align="center">
		<a href="/kintai/Forward?action=empMenu">メニューへ戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>