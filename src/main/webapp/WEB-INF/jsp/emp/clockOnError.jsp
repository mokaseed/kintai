<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠入力</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
			<h1>勤怠入力エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>勤怠入力に失敗しました。</p>
		<p>もう一度やり直してください。</p>
		<a href="/kintai/ClockOn">勤怠入力に戻る</a>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>