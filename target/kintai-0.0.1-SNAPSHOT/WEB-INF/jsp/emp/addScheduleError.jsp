<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "common/css/style.css" %>" rel="stylesheet" type="text/css" />
<title>Kintai スケジュール</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>スケジュール登録エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>再度お試しください。</p>
		<p>エラー内容<br>
		<c:forEach var="errorMsg" items="${errorMsgList}">
			<c:out value="${errorMsg}"/><br>
		</c:forEach></p>
		<a href="javascript:history.back()">戻る</a><br>
		<a href="<%= request.getContextPath() + "/Forward?action=empMenu" %>">メニューに戻る</a>
		
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>