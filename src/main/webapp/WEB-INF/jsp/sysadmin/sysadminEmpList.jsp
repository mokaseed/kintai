<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/empList.css" %>" rel="stylesheet" type="text/css" />
<title>kintai 管理者 - 従業員一覧</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員一覧</h1>
	</div>
	<div class="main_wrapper" align="center">
		<div class="select_box" align="center">
			<form action="<%= request.getContextPath() + "/SysadminEmpList" %>">
				<input type="hidden" name="action" value="selectDept">
				<select name="dept" onchange="submit(this.form)">
					<c:set var="nowDept" value="${nowDept}"/>
					<c:forEach var="dept" items="${deptList}">
						<option value="${dept.deptId}"<c:if test="${dept.deptId == nowDept}">selected</c:if> ><c:out value="${dept.deptName}"/></option>
					</c:forEach>
					<option value="0">管理者</option>
				</select>
			</form>
			<form action="<%= request.getContextPath() + "/SysadminEmpList" %>">
				<input type="hidden" name="action" value="search">
				<input class="search_window" type="text" name="search">
				<input class="search_btn btn_hover" type="submit" value="検索">
			</form>
			<form action="<%= request.getContextPath() + "/SysadminEmpList" %>" method="post">
				<input type="hidden" name="action" value="addEmp">
				<input class="add_btn btn_hover" type="submit" value="従業員新規登録">
			</form>
		</div>
		<div class="emp_list_table" align="center">
			<table>
				<tr><th>　氏名　</th><th>　事業部　</th><th>　従業員情報　</th><th>　勤怠管理　</th></tr>
				<c:forEach var="emp" items="${ empList }" varStatus="status">
					<tr>
						<td><c:out value="${emp.name}"/></td>
						<td><c:out value="${emp.deptName}"/></td>
						<td>
							<form action="<%= request.getContextPath() + "/SysadminEmpList" %>" method="post" action="empMaster">
								<input type="hidden" name="index" value="${status.index}">
								<input type="hidden" name="action" value="empMaster">
								<input class="sysadmin_input_submit btn_hover" type="submit" value="情報詳細">
							</form>
						</td>
						<td>
							<form action="<%= request.getContextPath() + "/SysadminEmpList" %>" method="post" action="workTime">
								<input type="hidden" name="index" value="${status.index}">
								<input type="hidden" name="action" value="workTime">
								<input class="sysadmin_input_submit btn_hover" type="submit" value="勤怠詳細">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="bottom_btn_box" align="center">
		<a class="bottom_btn" href="<%= request.getContextPath() + "/Forward?action=sysadminMenu" %>">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>