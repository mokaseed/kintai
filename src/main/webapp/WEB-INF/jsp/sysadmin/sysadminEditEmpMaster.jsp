<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/updateEmpMaster.css" rel="stylesheet" type="text/css" />
<title>Kintai 管理者 - 従業員情報詳細</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/sysadminHeader.jsp" />
	<div class="title" align="center">
		<h1>従業員情報　修正</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/SysadminEmpMaster" method="post">
			<table>
				<c:set var="index" value="${index}"/>
				<tr>
					<td class="item_name">社員ID</td>
					<td>${empList[index].empId}</td>
					<input type="hidden" name="empId" value="${empList[index].empId}">
				</tr>
				<tr>
					<td class="item_name">氏名</td>
					<td><input class="text" type="text" name="name" value="${empList[index].name}" required></td>
				</tr>
				<tr>
					<td class="item_name">所属</td>
					<td>
						<select name="deptName">
							<c:set var="deptName" value="${empList[index].deptName}" />
							<c:forEach var="dept" items="${deptList}">
								<option value="${dept.deptName}" <c:if test="${dept.deptName == deptName}">selected</c:if>><c:out value="${dept.deptName}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="item_name">電話番号</td>
					<td><input class="text" type="tel" name="tel" value="${empList[index].tel}"></td>
				</tr>
				<tr>
					<td class="item_name">メールアドレス</td>
					<td><input class="text" type="text" name="mail" value="${empList[index].mail}"></td>
				</tr>
				<tr>
					<td class="item_name">入社日</td>
					<td><input class="text" type="date" name="hireDate" value="${empList[index].hireDate}"></td>
				</tr>
				<tr>
					<td class="item_name">パスワード</td>
					<td><input class="text" type="password" name="pass" value="${empList[index].pass}" required></td>
				</tr>
				<tr>
					<td class="item_name">パスワード(確認用)</td>
					<td><input class="text" type="password" name="passCheck" value="${empList[index].pass}" required></td>
				</tr>
				<tr>
					<td class="item_name">管理者権限</td>
					<td>
					<input type="radio" name="sysadmin" value="1" <c:if test="${empList[index].sysadmin == '1'}">checked</c:if>>有
					<input type="radio" name="sysadmin" value="0" <c:if test="${empty empList[index].sysadmin}">checked</c:if>>無
					</td>
				</tr>
				<tr>
					<td class="item_name memo">備考</td>
					<td><textarea name="remarks" >${empList[index].remarks}</textarea></td>
				</tr>
			</table>
			<input class="btn" type="submit" value="修正内容を登録する">
		</form>
	
		<label class="open delete_btn" for="pop-up"><a>削除する</a></label>
		<input type="checkbox" id="pop-up">
		<div class="overlay">
			<div class="window">
				<p class="pop-up_text">従業員情報を削除します</p><br>
				<form action="/kintai/DeleteEmpMaster" method="post">
					<input type="hidden" name="empId" value="${empList[index].empId}">
					<input class="pop-up_btn" type="submit" value="はい">
					<label class="close" for="pop-up"><a class="pop-up_btn">いいえ</a></label>
				</form>
			</div>
		</div>
		<br>
		<a class="btn" href="javascript:history.back()">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>