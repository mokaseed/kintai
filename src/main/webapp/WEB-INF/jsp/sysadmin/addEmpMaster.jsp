<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 管理者 - 従業員新規登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員新規登録</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/AddEmpMaster" method="post">
			<table>
				<tr>
					<td>氏名</td>
					<td>姓<input type="text" name="lastName" required>名<input type="text" name="firstName" required></td>
				</tr>
				<tr>
					<td>所属</td>
					<td>
						<select name="deptName">
							<c:forEach var="dept" items="${deptList}">
								<option value="${dept.deptName}"><c:out value="${dept.deptName}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td><input type="tel" name="tel"></td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><input type="text" name="mail"></td>
				</tr>
				<tr>
					<td>入社日</td>
					<td><input type="date" name="hireDate"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="pass" required></td>
				</tr>
				<tr>
					<td>パスワード(確認用)</td>
					<td><input type="password" name="passCheck" required></td>
				</tr>
				<tr>
					<td>管理者権限</td>
					<td>
					<input type="radio" name="sysadmin" value="1">有
					<input type="radio" name="sysadmin" value="0" checked>無
					</td>
				</tr>
				<tr>
					<td>備考</td>
					<td><textarea name="remarks"></textarea></td>
				</tr>
			</table>
			<input type="submit" value="入力内容を確認する">
		</form>
	</div>
	<div align="center">
		<a href="/kintai/SysadminEmpList">戻る</a><br>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html></html>