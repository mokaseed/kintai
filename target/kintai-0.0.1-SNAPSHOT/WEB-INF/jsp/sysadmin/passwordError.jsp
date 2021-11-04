<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/style.css" %>" rel="stylesheet" type="text/css" />
<title>kintai 管理者 - パスワードエラー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>修正エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>入力した二つのパスワードが不一致です。<br>
		もう一度やり直してください。</p>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>