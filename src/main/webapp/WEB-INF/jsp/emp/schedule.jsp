<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.stream.IntStream,java.util.stream.Collectors,java.util.List,entity.MySchedule" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 予定登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>予定詳細</h1>
	</div>
	<div class="main_wrapper" align="center">
			<p>件名　${ms.subject}</p>
			<p>日付　${ms.year}年${ms.month}月${ms.date}日</p>		
			<p>時間　
			<c:choose>
				<c:when test="${ms.finishHour != null}">
					${ms.startHour}:${ms.startMinute}〜${ms.finishHour}:${ms.finishMinute}
				</c:when>
				<c:when test="${ms.startHour != null}">
					${ms.startHour}:${ms.startMinute}〜
				</c:when>
				<c:otherwise>時間指定なし</c:otherwise>
			</c:choose>
			</p>
			<p>色　 <span style="background:${ms.color};">　</span></p>
			<p>メモ　${ms.memo}</p>
			
		<a href="/kintai/AddSchedule?index=${index}">修正</a><br>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>