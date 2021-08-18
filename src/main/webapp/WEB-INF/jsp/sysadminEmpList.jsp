<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 管理者 従業員一覧</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員一覧</h1>
	</div>
	<div align="center">
		<form action="/kintai/SysadminEmpList" method="post">
			<input type="hidden" name="action" value="addEmp">
			<input type="submit" value="従業員新規登録">
		</form>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr><th>氏名</th><th>事業部</th><th>従業員情報</th><th>勤怠管理</th></tr>
			<c:forEach var="emp" items="${ empList }" varStatus="status">
				<tr>
					<td><c:out value="${emp.name}"/></td>
					<td><c:out value="${emp.deptName}"/></td>
					<td>
						<form action="/kintai/SysadminEmpList" method="post" action="empMaster">
							<input type="hidden" name="index" value="${status.index}">
							<input type="hidden" name="action" value="empMaster">
							<input type="submit" value="情報詳細">
						</form>
					</td>
					<td>
						<form action="/kintai/SysadminEmpList" method="post" action="workTime">
							<input type="hidden" name="index" value="${status.index}">
							<input type="hidden" name="action" value="workTime">
							<input type="submit" value="勤怠詳細">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/Forward?action=sysadminMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>