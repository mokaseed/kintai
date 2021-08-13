<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>勤怠時刻修正エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>
		勤務時刻が正しく選択されているかご確認ください。</p><br>
		<p>※ 注意 ※<br>
		時刻入力は半角で入力してください。</p><br>
		<a href="/kintai/SelectWorkTimeList">タイムシート年月指定に戻る</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
		
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>