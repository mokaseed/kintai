<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>kintai 管理者 - ログイン</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
			<h1>ログインエラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>ログインできませんでした。<br>
		管理者権限がない、もしくはユーザー名・パスワードが誤っている可能性があります。<br></p>
		<p></p>
		<div class="bottom_btn_box">
			<a class="bottom_btn" href="/kintai/Forward?action=sysadminLogin">ログインページへ戻る</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>