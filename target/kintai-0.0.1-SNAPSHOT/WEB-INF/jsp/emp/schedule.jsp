<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.stream.Collectors,java.util.List,entity.MySchedule" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/schedule.css" rel="stylesheet" type="text/css" />
<title>Kintai 予定登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>予定詳細</h1>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr><td class="item_name">件名</td><td>${ms.subject}</td></tr>
			<tr><td class="item_name">日付</td><td>${ms.year}年${ms.month}月${ms.date}日</td></tr>		
			<tr><td class="item_name">時間</td>
			<td><c:choose>
				<c:when test="${ms.finishHour != null}">
					${ms.startHour}:${ms.startMinute}〜${ms.finishHour}:${ms.finishMinute}
				</c:when>
				<c:when test="${ms.startHour != null}">
					${ms.startHour}:${ms.startMinute}〜
				</c:when>
				<c:otherwise>時間指定なし</c:otherwise>
			</c:choose></td></tr>
			<tr><td class="item_name">色</td><td> <span style="background:${ms.color};">　　　</span></td></tr>
			<tr><td class="item_name">メモ</td><td>${ms.memo}</td></tr>
		</table>
			
		<a class="btn" href="/kintai/AddSchedule?index=${index}">修正</a><br>
		<a class="btn" href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>