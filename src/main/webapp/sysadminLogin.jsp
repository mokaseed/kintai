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
		<form action="#" method="post">
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
			<!-- <input type="submit" value="ログイン"> -->
			<a href="sysadminMenu.jsp">ログイン</a> <!-- モック用ボタン -->
		</form>
		<a href="top.jsp">TOPへ戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>