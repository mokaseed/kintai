<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠時刻修正エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>再度お試しください。</p>
		<p>エラー内容<br>
		<c:forEach var="errorMsg" items="${errorMsgList}">
			<c:out value="${errorMsg}"/><br>
		</c:forEach></p>
		<a href="javascript:history.back()">戻る</a><br>
		<a href="/kintai/Forward?action=sysadminMenu">メニューに戻る</a>
		
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>