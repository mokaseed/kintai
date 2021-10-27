<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/menu.css" rel="stylesheet" type="text/css" />
<title>Kintai　ログアウト</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
<div class="title">
	<h1>ログアウト</h1>
</div>
<div class="main_wrapper" align="center">
	<p >ログアウトしました</p><br>
	<a class="menu_btn" href="/kintai/Forward?action=top">TOPへ戻る</a>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>