<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 勤怠入力</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
			<h1>勤怠入力</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>
		<c:choose>
			<c:when test="${clockOn == 'work_start'}">出勤打刻</c:when>
			<c:when test="${clockOn == 'work_finish'}">退勤打刻</c:when>
			<c:when test="${clockOn == 'break_start'}">休憩開始打刻</c:when>
			<c:when test="${clockOn == 'break_finish'}">休憩終了打刻</c:when>
		</c:choose>
		<br>
		登録完了しました</p>
		<div class="bottom_btn_box">
			<a class="bottom_btn" href="/kintai/Forward?action=empMenu">メニューに戻る</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>