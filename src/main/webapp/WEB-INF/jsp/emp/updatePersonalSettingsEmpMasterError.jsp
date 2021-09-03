<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 個人設定 - 従業員情報</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>修正エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>修正エラーが発生しました。<br>
		入力内容を確認し再度お試しください。</p>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>