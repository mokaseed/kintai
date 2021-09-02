<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.WorkTime,java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠修正</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠修正</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/UpdateWorkTime" method="POST">
			<table>
				<tr>
					<td>日付</td>
					<fmt:parseDate var="workDate" value="${workTime.workDate}" pattern="yyyy-MM-dd"/>
					<td><fmt:formatDate value="${workDate}" pattern="yyyy年MM月dd日"/></td>
					<input type="hidden" name="workDate" value="${workTime.workDate}">
				</tr>
				<tr>
					<td>出勤時刻</td>
					<c:choose>
						<c:when test="${workTime.startTime != null}">
							<fmt:parseDate var="startTime" value="${workTime.startTime}" pattern="HH:mm"/>
							<td><input type="tel" name="hStartTime" size="2" maxlength="2" value="<fmt:formatDate value="${startTime}" pattern="HH"/>" >:
							<input type="tel" name="mStartTime" size="2" maxlength="2" value="<fmt:formatDate value="${startTime}" pattern="mm"/>" ></td>
						</c:when>
						<c:otherwise>
							<td><input type="tel" name="hStartTime" size="2" maxlength="2">:
							<input type="tel" name="mStartTime" size="2" maxlength="2"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td>休憩開始時刻</td>
					<c:choose>
						<c:when test="${workTime.breakStartTime != null}">
							<fmt:parseDate var="breakStartTime" value="${workTime.breakStartTime}" pattern="HH:mm"/>
							<td><input type="tel" name="hBreakStartTime" size="2" maxlength="2" value="<fmt:formatDate value="${breakStartTime}" pattern="HH"/>" >:
							<input type="tel" name="mBreakStartTime" size="2" maxlength="2" value="<fmt:formatDate value="${breakStartTime}" pattern="mm"/>" ></td>
						</c:when>
						<c:otherwise>
							<td><input type="tel" name="hBreakStartTime" size="2" maxlength="2">:
							<input type="tel" name="mBreakStartTime" size="2" maxlength="2"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td>休憩終了時刻</td>
					<c:choose>
						<c:when test="${workTime.breakFinishTime != null}">
							<fmt:parseDate var="breakFinishTime" value="${workTime.breakFinishTime}" pattern="HH:mm"/>
							<td><input type="tel" name="hBreakFinishTime" size="2" maxlength="2" value="<fmt:formatDate value="${breakFinishTime}" pattern="HH"/>" >:
							<input type="tel" name="mBreakFinishTime" size="2" maxlength="2" value="<fmt:formatDate value="${breakFinishTime}" pattern="mm"/>" ></td>
						</c:when>
						<c:otherwise>
							<td><input type="tel" name="hBreakFinishTime" size="2" maxlength="2">:
							<input type="tel" name="mBreakFinishTime" size="2" maxlength="2"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td>退勤時刻</td>
					<c:choose>
						<c:when test="${workTime.finishTime != null}">
							<fmt:parseDate var="finishTime" value="${workTime.finishTime}" pattern="HH:mm"/>
							<td><input type="tel" name="hFinishTime" size="2" maxlength="2" value="<fmt:formatDate value="${finishTime}" pattern="HH"/>" >:
							<input type="tel" name="mFinishTime" size="2" maxlength="2" value="<fmt:formatDate value="${finishTime}" pattern="mm"/>" ></td>
						</c:when>
						<c:otherwise>
							<td><input type="tel" name="hFinishTime" size="2" maxlength="2">:
							<input type="tel" name="mFinishTime" size="2" maxlength="2"></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
			<input type="submit" value="修正する">
		</form>
	</div>
	<div align="center">
		<a href="javascript:history.back()">戻る</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>