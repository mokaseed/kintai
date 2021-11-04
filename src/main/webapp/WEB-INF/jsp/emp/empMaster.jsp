<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/empMaster.css" %>" rel="stylesheet" type="text/css" />
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
				<td class="item_name">氏名</td>
				<td>${empList[index].name}</td>
			</tr>
			<tr>
				<td class="item_name">所属</td>
				<td>${empList[index].deptName}</td>
			</tr>
			<tr>
				<td class="item_name">電話番号</td>
				<td>${empList[index].tel}</td>
			</tr>
			<tr>
				<td class="item_name">メールアドレス</td>
				<td>${empList[index].mail}</td>
			</tr>
			<tr>
				<td class="item_name">入社日</td>
				<td>${empList[index].hireDate}</td>
			</tr>
			<tr>
				<td class="item_name">備考</td>
				<td>${empList[index].remarks}</td>
			</tr>
		</table>
	</div>
	<div class="bottom_btn_box" align="center">
		<a class="btn" href="javascript:history.back()">戻る</a><br>
		<a class="btn" href="<%= request.getContextPath() + "/Forward?action=empMenu" %>">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>