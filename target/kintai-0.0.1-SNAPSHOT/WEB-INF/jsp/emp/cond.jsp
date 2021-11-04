<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/cond.css" %>" rel="stylesheet" type="text/css" />
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
		<form action="<%= request.getContextPath() + "/Cond" %>" method="POST">
		<input type="hidden" name="clockOn" value="${ clockOn }">
		<div class="cond_btn">
			<input id="veryFine" type="radio" name="cond" value="1"><label for="veryFine"><img width="60" height="60" src="<%= request.getContextPath() + "/common/png/veryFine.png" %>"></label>
			<input id="fine" type="radio" name="cond" value="2"><label for="fine"><img width="60" height="60" src="<%= request.getContextPath() + "/common/png/fine.png" %>"></label>
			<input id="OK" type="radio" name="cond" value="3"><label for="OK"><img width="60" height="60" src="<%= request.getContextPath() + "/common/png/OK.png" %>"></label>
			<input id="bad" type="radio" name="cond" value="4"><label for="bad"><img width="60" height="60" src="<%= request.getContextPath() + "/common/png/bad.png" %>"></label><br>
		</div>
		<input class="regist_btn" type="submit" value="登録する">
		</form>
		<div class="bottom_btn_box">
			<a class="bottom_btn" href="<%= request.getContextPath() + "/ClockOn" %>">出退勤選択に戻る</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>