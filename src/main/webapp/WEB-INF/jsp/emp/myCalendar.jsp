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
<link href="<%= request.getContextPath() + "/common/css/myCalendar.css" %>" rel="stylesheet" type="text/css" />
<title>kintai - カレンダー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center" id="container">
		<h1>${mc.year}年${mc.month}月カレンダー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<div class="top_btn">
			<a href="<%= request.getContextPath() + "/MyCalendarMain" %>?year=${mc.year}&month=${mc.month - 1}">前月</a>
			<a href="<%= request.getContextPath() + "/MyCalendarMain" %>?year=${mc.year}&month=${mc.month + 1}">翌月</a>
			<a class="add_schedule_btn" href="<%= request.getContextPath() + "/AddSchedule" %>?year=${mc.year}&month=${mc.month}">予定を追加</a>
		</div>
		
		<!-- カレンダーを表示 -->
		<div class="calendar">
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
						<td class="schedule_td">
	 					<c:set var="index" value="0"/>
	 						<c:forEach var="ms" items="${myScheduleList}">
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
												<c:set var="lastMonth">${mc.lastMonth}</c:set>
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
												<c:set var="nextMonth">${mc.nextMonth}</c:set>
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
												<c:set var="month">${mc.month}</c:set>
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
										<c:set var="month">${mc.month}</c:set>
										<c:choose>
											<c:when test="${ fn:length(month) == 1 }">
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
								<c:if test="${ms.skdDate == date}">
									<c:choose>
										<c:when test="${ms.skdStartTime == null}">
											<div style="background-color:${ms.color};"><a style="color:black;text-decoration:none;" href="<%= request.getContextPath() + "/AddSchedule" %>?action=done&index=${index - 1}">・${ms.subject}</a></div>
										</c:when>
										<c:when test="${ms.skdFinishTime == null}">
											<div style="background-color:${ms.color};">${ms.skdStartTime}<br><a style="color:black;text-decoration:none;" href="<%= request.getContextPath() + "/AddSchedule" %>?action=done&index=${index - 1}">${ms.subject}</a></div>
										</c:when>
										<c:otherwise>
											<div style="background-color:${ms.color};">${ms.skdStartTime}~${ms.skdFinishTime}<br><a style="color:black;text-decoration:none;" href="<%= request.getContextPath() + "/AddSchedule" %>?action=done&index=${index - 1}">${ms.subject}</a></div>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
							<form action="<%= request.getContextPath() + "/AddSchedule" %>">
							<input type="hidden" name="date" value="${date}">
						 	<input class="add_icon" type="image" src="<%= request.getContextPath() + "/common/png/addSchedule.png" %>" alt="予定を追加" width="15" height="15">
						 	</form>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="bottom_btn_box" align="center">
		<a class="bottom_btn" href="<%= request.getContextPath() + "/Forward?action=empMenu" %>">メニューへ戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>