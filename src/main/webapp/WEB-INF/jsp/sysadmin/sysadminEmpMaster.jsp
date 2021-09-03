<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 管理者 - 従業員情報詳細</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員情報　詳細</h1>
	</div>
	<div class="main_wrapper" align="center">
		<table>
			<c:set var="index" value="${index}"/>
			<tr>
				<td>社員ID</td>
				<td>${empList[index].empId}</td>
			</tr>
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
				<td>パスワード</td>
				<td>-</td>
			</tr>
			<tr>
				<td>管理者権限</td>
				<td>
					<c:choose>
						<c:when test="${empList[index].sysadmin == '1'}">有</c:when>
						<c:otherwise>無</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>備考</td>
				<td>${empList[index].remarks}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/SysadminEmpMaster">従業員情報を修正する</a><br>
		<a href="/kintai/SysadminEmpList">従業員一覧に戻る</a><br>
		<a href="/kintai/Forward?action=sysadminMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>