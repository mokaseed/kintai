<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 管理者 - 従業員情報詳細</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員情報　修正</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/SysadminEmpMaster" method="post">
			<table>
				<c:set var="index" value="${index}"/>
				<tr>
					<td>社員ID</td>
					<td>${empList[index].empId}</td>
					<input type="hidden" name="empId" value="${empList[index].empId}">
				</tr>
				<tr>
					<td>氏名</td>
					<td><input type="text" name="name" value="${empList[index].name}" required></td>
				</tr>
				<tr>
					<td>所属</td>
					<td>
						<select name="deptName">
							<c:set var="deptName" value="${empList[index].deptName}" />
							<c:forEach var="dept" items="${deptList}">
								<option value="${dept.deptName}" <c:if test="${dept.deptName == deptName}">selected</c:if>><c:out value="${dept.deptName}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td><input type="tel" name="tel" value="${empList[index].tel}"></td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><input type="text" name="mail" value="${empList[index].mail}"></td>
				</tr>
				<tr>
					<td>入社日</td>
					<td><input type="date" name="hireDate" value="${empList[index].hireDate}"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="pass" value="${empList[index].pass}" required></td>
				</tr>
				<tr>
					<td>パスワード(確認用)</td>
					<td><input type="password" name="passCheck" value="${empList[index].pass}" required></td>
				</tr>
				<tr>
					<td>管理者権限</td>
					<td>
					<input type="radio" name="sysadmin" value="1" <c:if test="${empList[index].sysadmin == '1'}">checked</c:if>>有
					<input type="radio" name="sysadmin" value="0" <c:if test="${empty empList[index].sysadmin}">checked</c:if>>無
					</td>
				</tr>
				<tr>
					<td>備考</td>
					<td><textarea name="remarks" >${empList[index].remarks}</textarea></td>
				</tr>
			</table>
			<input type="submit" value="修正内容を登録する">
		</form>
	</div>
	<div align="center">
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>