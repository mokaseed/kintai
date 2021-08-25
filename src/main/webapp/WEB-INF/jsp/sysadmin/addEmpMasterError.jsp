<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai エラー</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員新規登録　エラー</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>エラーが発生しました。<br>
		入力内容を確認しもう一度やり直してください。</p>
		<p>※注意※<br>
		電話番号：半角数字を入力してください（ハイフン等不要）<br>
		メールアドレス：半角英数字を入力してください<br>
		入社日：日付を手入力する場合は「yyyy-mm-dd」(全て半角)で入力してください。<br>
		（例：2020年4月1日の場合 → 2020-04-01）<br>
		パスワード：半角英数字を入力してください</p>
		<a href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>