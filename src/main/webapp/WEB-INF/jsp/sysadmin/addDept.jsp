<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>kintai 事業部管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>事業部新規登録</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/DeptMaster" method="post">
			事業部名：<input type="text" name="deptName">
			<input type="hidden" name="action" value="regist">
			<br><input type="submit" value="登録する">
		</form>
	</div>
	<div align="center">
		<a href="/kintai/DeptMaster">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>