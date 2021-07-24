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
		<p>※勤怠入力はまだ完了していません</p>
	</div>
	<div class="main_wrapper" align="center">
		<p>今のコンディションを選択してください</p>
<!-- 	画像をラジオボタンにするやり方は以下を参考に作成する予定
		https://0forest.com/radio-button-image/ -->
		<form action="clockOnOK.jsp">
		<input type="radio" name="cond" value="verygood">verygood画像
		<input type="radio" name="cond" value="good">good画像
		<input type="radio" name="cond" value="OK">OK画像
		<input type="radio" name="cond" value="bad">bad画像<br>
		<input type="submit" value="登録する">
		</form>
		<a href="clockOn.jsp">出退勤選択に戻る</a>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>