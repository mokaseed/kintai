<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/style.css" %>" rel="stylesheet" type="text/css" />
<title>kintai 管理者 - エラー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員新規登録　エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>
		入力内容を確認しもう一度やり直してください。</p>
		<p>※エラー内容※<br>
		<c:forEach var="errorMsg" items="${errorMsgList}">
			<c:out value="${errorMsg}"></c:out><br>
		</c:forEach>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>