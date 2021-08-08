<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 管理者ログイン</title>
</head>
<body>
	<jsp:include page="/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>管理者ログイン</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="Login" method="post">
			<table>
				<tr>
					<td>社員ID</td>
					<td><input type="tel" name="empId" maxlength="5"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="pass"></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="done">
			<input type="submit" value="ログイン">
		</form>
		<a href="top.jsp">TOPへ戻る</a>
	</div>
	<div align="center">
		<p>テスト用アカウント<br>
		社員ID：2<br>
		パスワード：2345</p>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>