<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 事業部管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>事業部管理</h1>
	</div>
	<div align="center">
		<form action="/kintai/DeptMaster">
			<input type="hidden" name="action" value="regist">
			<input type="submit" value="事業部新規登録">
		</form>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr><th>事業部名</th><th>編集</th></tr>
			<c:forEach var="dept" items="${deptList}" varStatus="status">
				<tr>
				<td><c:out value="${dept.deptName}"/></td>
				<td><form action="/kintai/DeptMaster">
					<input type="hidden" name="action" value="edit">
					<input type="hidden" name="index" value="${status.index}">
					<input type="submit" value="編集">
				</form></td>
				</tr>
			</c:forEach>
		</table>
		<a href="/kintai/Forward?action=sysadminMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>