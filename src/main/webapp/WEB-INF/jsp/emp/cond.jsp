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
		<h1>勤怠入力</h1>
		<p>※勤怠入力はまだ完了していません</p>
	</div>
	<div class="main_wrapper" align="center">
		<p>今のコンディションを選択してください</p>
<!-- 	画像をラジオボタンにするやり方は以下を参考に作成する予定
		https://0forest.com/radio-button-image/ 
		・ボタンを消す　・未選択のものは半透明、選択したものは濃くする-->
		<form action="/kintai/Cond" method="POST">
		<input type="hidden" name="clockOn" value="${ clockOn }">
		<label><input type="radio" name="cond" value="1"><img width="60" height="60" src="/kintai/common/png/veryFine.png"></label>
		<label><input type="radio" name="cond" value="2"><img width="60" height="60" src="/kintai/common/png/fine.png"></label>
		<label><input type="radio" name="cond" value="3"><img width="60" height="60" src="/kintai/common/png/OK.png"></label>
		<label><input type="radio" name="cond" value="4"><img width="60" height="60" src="/kintai/common/png/bad.png"></label><br>
		<input type="submit" value="登録する">
		</form>
		<a href="/kintai/ClockOn">出退勤選択に戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>