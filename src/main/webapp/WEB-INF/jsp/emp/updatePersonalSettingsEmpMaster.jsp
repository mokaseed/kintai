<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/style.css" rel="stylesheet" type="text/css" />
<title>Kintai 個人設定 - 従業員情報修正</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>情報修正</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="/kintai/PersonalSettingsEmpMaster" method="post">
			<table>
				<tr>
					<td>社員ID</td>
					<td>${account.empId}</td>
				</tr>
				<tr>
					<td>氏名</td>
					<td>${account.name}</td>
				</tr>
				<tr>
					<td>所属</td>
					<td>${account.deptName}</td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td>
						<input type="tel" name="tel" value="${account.tel}">
					</td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td>
						<input type="text" name="mail" value="${account.mail}">
					</td>
				</tr>
				<tr>
					<td>入社日</td>
					<td>${account.hireDate}</td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="pass" value="${account.pass}" required></td>
				</tr>
				<tr>
					<td>パスワード(確認用)</td>
					<td><input type="password" name="passCheck" value="${account.pass}" required></td>
				</tr>
				<tr>
					<td>備考</td>
					<td>
						<textarea name="remarks">${account.remarks}</textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="登録する">
		</form>
	</div>
	<div align="center">
		<a href="/kintai/PersonalSettingsEmpMaster">戻る</a><br>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>