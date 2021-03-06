<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar,java.util.List,entity.WorkTime,java.time.format.DateTimeFormatter,java.util.stream.IntStream,java.util.stream.Collectors,model.WorkTimeListTimeCheck" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
//選択された年月をセット
Calendar thisMonthCalendar = (Calendar)session.getAttribute("thisMonthCalendar");
request.setAttribute("year", thisMonthCalendar.get(Calendar.YEAR));
request.setAttribute("month", thisMonthCalendar.get(Calendar.MONTH));

//選択された月の最終日をセット
thisMonthCalendar.add(Calendar.MONTH, -1);
request.setAttribute("maxDate", thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

//タイムシートの年月指定で選択できる数字をセット
List<Integer> yearNumbers = IntStream.range(2020, 2030).boxed().collect(Collectors.toList());
request.setAttribute("yearNumbers",yearNumbers);
List<Integer> monthNumbers = IntStream.range(1, 13).boxed().collect(Collectors.toList());
request.setAttribute("monthNumbers",monthNumbers);

//タイムシートに記録されている時間が「出勤時間<休憩開始時間＜休憩終了時間＜退勤時間」になっているかチェックするためのクラス
WorkTimeListTimeCheck check = new WorkTimeListTimeCheck();
request.setAttribute("check", check);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/workTimeList.css" %>" rel="stylesheet" type="text/css" />
<title>Kintai 勤怠管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title">
		<h1>${year}年${month}月分タイムシート</h1>
	</div>
	<div class="select_box">
		<form action="<%= request.getContextPath() + "/SelectWorkTimeList" %>" method="post">
			<c:set var="year" value="${year}" />
			<select class="select_year" name="selectY">
				<c:forEach var="y" items="${yearNumbers}">
					<c:choose>
						<c:when test="${y == year}">
							<option value="${y}" selected>${y}</option>
						</c:when>
						<c:otherwise>
							<option value="${y}">${y}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<select class="select_month" name="selectM">
				<c:set var="month" value="${month}" />
				<c:forEach var="m" items="${monthNumbers}">
					<c:choose>
						<c:when test="${m == month}">
							<option value="${m}" selected>${m}</option>
						</c:when>
						<c:otherwise>
							<option value="${m}">${m}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<input class="btn_hover" type="submit" value="表示">
		</form>
	</div>
	<div class="main_wrapper" align="center">
		<table class="work_time_list_table">
			<tr>
				<th>日付</th><th>出勤</th><th>休憩開始</th><th>休憩終了</th><th>退勤</th><th>修正</th>
			</tr>
			<c:forEach varStatus="date" begin="1" end="${maxDate}" step="1">
			<c:set var="date" value="${date.index}"/>
				<c:set var="chkDateFlag" value="false"/>
				<!-- タイムシートに記録されている時間が「出勤時間<休憩開始時間＜休憩終了時間＜退勤時間」になっているかチェック
				falseの場合は列に色を付ける -->
				<c:set var="list" value="${workTimeList}"/>
				<c:set var="timeListFlag" value="${check.execute(list, date)}"/>
				<c:choose>
					<c:when test="${timeListFlag == true}">
						<tr>
					</c:when>
					<c:otherwise>
						<tr style="background-color:rgba(250,60,60,0.6);">
					</c:otherwise>
				</c:choose>
					<td>
						${month}月${date}日
					</td>
					<c:forEach var="workTime" items="${workTimeList}">
						<fmt:parseDate var="parseDate" value="${workTime.workDate}" pattern="yyyy-MM-dd" />
						<fmt:formatDate var="workDate" value="${parseDate}" pattern="dd" />
						<c:if test="${workDate == date}">
							<td>
								<c:if test="${workTime.startTime != null}">
									<fmt:parseDate var="startTime" value="${workTime.startTime}" pattern="HH:mm" />
									<fmt:formatDate value="${startTime}" pattern="HH:mm" />
								</c:if>
							</td>
							<td>
								<c:if test="${workTime.breakStartTime != null}">
									<fmt:parseDate var="breakStartTime" value="${workTime.breakStartTime}" pattern="HH:mm" />
									<fmt:formatDate value="${breakStartTime}" pattern="HH:mm" />
								</c:if>
							</td>
							<td>
								<c:if test="${workTime.breakFinishTime != null}">
									<fmt:parseDate var="breakFinishTime" value="${workTime.breakFinishTime}" pattern="HH:mm" />
									<fmt:formatDate value="${breakFinishTime}" pattern="HH:mm" />
								</c:if>
							</td>
							<td>
								<c:if test="${workTime.finishTime != null}">
									<fmt:parseDate var="finishTime" value="${workTime.finishTime}" pattern="HH:mm" />
									<fmt:formatDate value="${finishTime}" pattern="HH:mm" />
								</c:if>
							</td>
							<td>
								<form action="<%= request.getContextPath() + "/UpdateWorkTime" %>">
									<c:set var="s_month"><fmt:formatNumber value="${month}" pattern="00"/></c:set>
									<input type="hidden" name="workDate" value="${year}-${s_month}-${workDate}">
									<input type="hidden" name="startTime" value="${workTime.startTime}">
									<input type="hidden" name="breakStartTime" value="${workTime.breakStartTime}">
									<input type="hidden" name="breakFinishTime" value="${workTime.breakFinishTime}">
									<input type="hidden" name="finishTime" value="${workTime.finishTime}">
									<input class="btn_hover" type="submit" value="修正">
								</form>
							</td></tr>
						<c:set var="chkDateFlag" value="true"/>
						</c:if>
					</c:forEach>
					
					<c:if test="${chkDateFlag == false}">
						<td></td><td></td><td></td><td></td>
					<td>
						<form action="<%= request.getContextPath() + "/UpdateWorkTime" %>">
							<c:set var="s_month"><fmt:formatNumber value="${month}" pattern="00"/></c:set>
							<c:set var="s_date"><fmt:formatNumber value="${date}" pattern="00"/></c:set>
							<input type="hidden" name="workDate" value="${year}-${s_month}-${s_date}">
							<input type="hidden" name="startTime" value="">
							<input type="hidden" name="breakStartTime" value="">
							<input type="hidden" name="breakFinishTime" value="">
							<input type="hidden" name="finishTime" value="">
							<input class="btn_hover" type="submit" value="修正">
						</form>
					</td>
					</c:if>
			</c:forEach>
		</table>
	</div>
	<div class="bottom_btn_box">
		<a class="bottom_btn" href="<%= request.getContextPath() + "/Forward?action=empMenu" %>">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>