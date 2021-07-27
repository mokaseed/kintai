<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 従業員ログイン</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員ログイン</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="Login" method="post">
			<table>
				<tr>
					<td>ユーザー名</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="pass"></td>
				</tr>
			</table>
			<input type="submit" value="ログイン">
		</form>
		<a href="top.jsp">TOPへ戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>