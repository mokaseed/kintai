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
		<h1>事業部 削除エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>
		再度お試しください。</p>
		
		<p>※エラー内容※<br>
		<c:forEach var="errorMsg" items="${errorMsgList}">
			<c:out value="${errorMsg}"></c:out><br>
		</c:forEach>
		
		<div  class="bottom_btn_box">
			<a class="bottom_btn" href="<%= request.getContextPath() + "/DeptMaster" %>">戻る</a><br>
			<a class="bottom_btn" href="<%= request.getContextPath() + "/Forward?action=sysadminMenu" %>">メニューへ</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>