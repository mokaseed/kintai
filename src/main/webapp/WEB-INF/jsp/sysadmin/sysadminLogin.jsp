<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<%= request.getContextPath() + "/common/css/login.css" %>" rel="stylesheet" type="text/css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kintai 管理者 - 管理者ログイン</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>管理者ログイン</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="<%= request.getContextPath() + "/Login" %>" method="post">
			<table>
				<tr>
					<td class="box_name">社員ID</td>
					<td><input class="text_box" type="tel" name="empId" maxlength="5"></td>
				</tr>
				<tr>
					<td class="box_name">パスワード</td>
					<td><input class="text_box" type="password" name="pass"></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="done">
			<input class="btn" type="submit" value="ログイン">
		</form>
		<a class="btn" href="<%= request.getContextPath() + "/Forward?action=top" %>">TOPへ戻る</a>
	</div>
	<div class="test_account">
		<p>テスト用アカウント<br>
		社員ID：2<br>
		パスワード：2345</p>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>