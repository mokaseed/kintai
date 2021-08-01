<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠入力</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
			<h1>勤怠入力</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>${ clockOn }</p><br>
		<p>登録完了しました</p>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>