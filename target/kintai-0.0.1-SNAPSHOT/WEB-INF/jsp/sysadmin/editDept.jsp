<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/updateWorkTime.css" %>" rel="stylesheet" type="text/css" />
<title>kintai 管理者 - 事業部管理</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>事業部編集</h1>
	</div>
	<div class="main_wrapper" align="center">
	<c:set var="index" value="${index}"/>
		<form action="<%= request.getContextPath() + "/DeptMaster" %>" method="post">
			<table>
				<tr>
					<td class="item_name">事業部名</td>
					<td><input class="add_dept_text" type="text" name="deptName" value="${deptList[index].deptName}"></td>
				</tr>
			</table>
			<input type="hidden" name="deptId" value="${deptList[index].deptId}">
			<input type="hidden" name="action" value="edit">
			<br><input class="bottom_btn"  type="submit" value="登録する">
		</form>
		<label class="open delete_btn" for="pop-up"><a>削除する</a></label>
		<input type="checkbox" id="pop-up">
		<div class="overlay">
			<div class="window">
				<p class="pop-up_text">事業部を削除します</p><br>
				<form action="<%= request.getContextPath() + "/DeptMaster" %>" method="post">
					<input type="hidden" name="deptId" value="${deptList[index].deptId}">
					<input type="hidden" name="action" value="delete">
					<input class="pop-up_btn" type="submit" value="はい">
					<label class="close" for="pop-up"><a class="pop-up_btn">いいえ</a></label>
				</form>
			</div>
		</div>
		<br>
	</div>
	<div class="bottom_btn_box" align="center">
		<a class="bottom_btn" href="<%= request.getContextPath() + "/DeptMaster" %>">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>