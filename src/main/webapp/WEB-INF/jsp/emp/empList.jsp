<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 従業員一覧</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員一覧</h1>
	</div>
	<div align="center">
		<form action="/kintai/EmpList">
			<input type="hidden" name="action" value="selectDept">
			<select name="dept" onchange="submit(this.form)">
				<c:set var="nowDept" value="${nowDept}"/>
				<c:forEach var="dept" items="${deptList}">
					<option value="${dept.deptId}"<c:if test="${dept.deptId == nowDept}">selected</c:if> ><c:out value="${dept.deptName}"/></option>
				</c:forEach>
			</select>
		</form>
		<form action="/kintai/EmpList">
			<input type="hidden" name="action" value="search">
			<input type="text" name="search">
			<input type="submit" value="検索">
		</form>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<tr><th>氏名</th><th>事業部</th><th>電話番号</th><th>メールアドレス</th><th>詳細</th></tr>
			<c:forEach var="emp" items="${ empList }" varStatus="status">
				<tr>
					<td><c:out value="${emp.name}"/></td>
					<td><c:out value="${emp.deptName}"/></td>
					<td><c:out value="${emp.tel}"/></td>
					<td><c:out value="${emp.mail}"/></td>
					<td>
						<form action="/kintai/EmpList" method="post">
							<input type="hidden" name="index" value="${status.index}">
							<input type="submit" value="詳細">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>