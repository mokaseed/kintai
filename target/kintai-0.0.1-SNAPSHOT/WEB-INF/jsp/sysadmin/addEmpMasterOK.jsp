<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/style.css" %>" rel="stylesheet" type="text/css" />
<title>Kintai 管理者 - 従業員新規登録</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員新規登録（登録完了）</h1>
	</div>
	<div class="main_wrapper" align="center">
		<p>従業員情報の新規登録が完了しました。</p>
		<div class="bottom_btn_box">
			<a class="bottom_btn" href="<%= request.getContextPath() + "/SysadminEmpList" %>">従業員一覧に戻る</a><br>
			<a class="bottom_btn" href="<%= request.getContextPath() + "/AddEmpMaster" %>">続けて新規登録を行う</a><br>
			<a class="bottom_btn" href="<%= request.getContextPath() + "/Forward?action=sysadminMenu" %>">メニューに戻る</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>