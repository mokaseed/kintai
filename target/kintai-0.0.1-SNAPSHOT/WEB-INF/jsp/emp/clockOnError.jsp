<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/style.css" %>" rel="stylesheet" type="text/css" />
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
		
		<div class="bottom_btn_box">
			<a class="bottom_btn" href="<%= request.getContextPath() + "/ClockOn" %>">勤怠入力に戻る</a><br>
			<a class="bottom_btn" href="<%= request.getContextPath() + "/Forward?action=empMenu" %>">メニューに戻る</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>