<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() + "/common/css/updateEmpMaster.css" %>" rel="stylesheet" type="text/css" />
<title>Kintai 個人設定 - 従業員情報修正</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>情報修正</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="<%= request.getContextPath() + "/PersonalSettingsEmpMaster" %>" method="post">
			<table>
				<tr>
					<td class="item_name">社員ID</td>
					<td>${account.empId}</td>
				</tr>
				<tr>
					<td class="item_name">氏名</td>
					<td>${account.name}</td>
				</tr>
				<tr>
					<td class="item_name">所属</td>
					<td>${account.deptName}</td>
				</tr>
				<tr>
					<td class="item_name">電話番号</td>
					<td>
						<input class="text" type="tel" name="tel" value="${account.tel}">
					</td>
				</tr>
				<tr>
					<td class="item_name">メールアドレス</td>
					<td>
						<input class="text" type="text" name="mail" value="${account.mail}">
					</td>
				</tr>
				<tr>
					<td class="item_name">入社日</td>
					<td>${account.hireDate}</td>
				</tr>
				<tr>
					<td class="item_name">パスワード</td>
					<td><input class="text" type="password" name="pass" value="${account.pass}" required></td>
				</tr>
				<tr>
					<td class="item_name">パスワード(確認用)</td>
					<td><input class="text" type="password" name="passCheck" value="${account.pass}" required></td>
				</tr>
				<tr>
					<td class="item_name memo">備考</td>
					<td>
						<textarea name="remarks">${account.remarks}</textarea>
					</td>
				</tr>
			</table>
			<input class="btn" type="submit" value="登録する">
		</form>
		<a class="btn" href="<%= request.getContextPath() + "/PersonalSettingsEmpMaster" %>">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>