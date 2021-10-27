<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/empList.css" rel="stylesheet" type="text/css" />
<title>kintai 管理者 - 事業部管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>事業部管理</h1>
	</div>
	<div class="select_box" align="center">
		<form action="/kintai/DeptMaster">
			<input type="hidden" name="action" value="regist">
			<input class="add_btn btn_hover" type="submit" value="事業部新規登録">
		</form>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr><th>　事業部名　</th><th>　編集　</th></tr>
			<c:forEach var="dept" items="${deptList}" varStatus="status">
				<tr>
				<td><c:out value="${dept.deptName}"/></td>
				<td><form action="/kintai/DeptMaster">
					<input type="hidden" name="action" value="edit">
					<input type="hidden" name="index" value="${status.index}">
					<input class="btn_hover" type="submit" value="編集">
				</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="bottom_btn_box">
		<a class="bottom_btn" href="/kintai/Forward?action=sysadminMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>