<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 従業員一覧　詳細</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員情報　詳細</h1>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<c:set var="index" value="${index}"/>
			<tr>
				<td>氏名</td>
				<td>${empList[index].name}</td>
			</tr>
			<tr>
				<td>所属</td>
				<td>${empList[index].deptName}</td>
			</tr>
			<tr>
				<td>電話番号</td>
				<td>${empList[index].tel}</td>
			</tr>
			<tr>
				<td>メールアドレス</td>
				<td>${empList[index].mail}</td>
			</tr>
			<tr>
				<td>入社日</td>
				<td>${empList[index].hireDate}</td>
			</tr>
			<tr>
				<td>備考</td>
				<td>${empList[index].remarks}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<a href="javascript:history.back()">戻る</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>