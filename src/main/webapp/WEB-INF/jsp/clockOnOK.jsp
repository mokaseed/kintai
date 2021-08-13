<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String clockOn = (String)request.getAttribute("clockOn");
String co = null;
if(clockOn.equals("work_start")){
	co = "出勤打刻";
} else if(clockOn.equals("work_finish")){
	co = "退勤打刻";
} else if(clockOn.equals("break_start")){
	co = "休憩開始打刻";
} else if(clockOn.equals("break_finish")){
	co = "休憩終了打刻";
}
%>
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
	</div>
	<div class="main_wrapper" align="center">
		<p><%= co %></p><br>
		<p>登録完了しました</p>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>