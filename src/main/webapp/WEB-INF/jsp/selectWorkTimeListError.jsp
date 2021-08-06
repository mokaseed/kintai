<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 勤怠管理</title>
</head>
<body>
	<jsp:include page="/empHeader.jsp" />
	<div class="title" align="center">
		<h1>タイムシート エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>
		年月が正しく選択されているかご確認ください。</p><br>
		<p>※ 注意 ※<br>
		年月を手入力する場合は半角で下記のように入力してください。<br>
		2020年4月のタイムシート表示したい場合　→　【2020-04】</p><br>
		<a href="/kintai/SelectWorkTimeList">タイムシート年月指定に戻る</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>