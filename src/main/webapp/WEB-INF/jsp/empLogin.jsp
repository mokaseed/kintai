<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 従業員ログイン</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員ログイン</h1>
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
			<input type="submit" value="ログイン">
		</form>
		<a href="/kintai/Forward?action=top">TOPへ戻る</a>
	</div>
	<div align="center">
		<p>テスト用アカウント<br>
		社員ID：1<br>
		パスワード：1234</p>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>