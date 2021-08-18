<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 管理者 - 従業員新規登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員新規登録（入力内容確認）</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/AddEmpMaster" method="post">
			<table>
				<tr>
					<td>氏名</td>
					<td>${addEmp.lastName} ${addEmp.firstName}</td>
				</tr>
				<tr>
					<td>所属</td>
					<td>${addEmp.deptName}</td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td>${addEmp.tel}</td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td>${addEmp.mail}</td>
				</tr>
				<tr>
					<td>入社日</td>
					<td>${addEmp.hireDate}</td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td>-</td>
				</tr>
				<tr>
					<td>管理者権限</td>
					<td>
						<c:choose>
							<c:when test="${addEmp.sysadmin == '1'}">有</c:when>
							<c:when test="${addEmp.sysadmin == '0'}">無</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>備考</td>
					<td>${addEmp.remarks}</td>
				</tr>
			</table>
			<input type="hidden" name="action" value="done"> 
			<input type="submit" value="登録する">
		</form>
	</div>
	<div align="center">
		<a href="javascript:history.back()">入力内容を修正する</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>