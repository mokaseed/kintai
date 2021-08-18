<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 管理者 - 従業員新規登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員新規登録（登録完了）</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>従業員情報の新規登録が完了しました。</p>
		<a href="/kintai/SysadminEmpList">従業員一覧に戻る</a><br>
		<a href="/kintai/AddEmpMaster">続けて従業員情報の新規登録を行う</a><br>
		<a href="/kintai/Forward?action=sysadminMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>