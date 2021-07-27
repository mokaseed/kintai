<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai ログイン</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
			<h1>ログインエラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>ログインできませんでした。</p>
		<p>ユーザー名とパスワードが正しいかご確認ください。</p>
		<p></p>
		<a href="sysadminLogin.jsp">ログインページへ戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>