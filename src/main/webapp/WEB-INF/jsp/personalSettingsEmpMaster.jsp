<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kintai 個人設定 - 従業員情報</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/empHeader.jsp" />
	<div class="title" align="center">
		<h1>個人設定　従業員情報</h1>
	</div>
	<div class="main_wrapper" align="center">
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
				<td>${account.tel}</td>
			</tr>
			<tr>
				<td>メールアドレス</td>
				<td>${account.mail}</td>
			</tr>
			<tr>
				<td>入社日</td>
				<td>${account.hireDate}</td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td>-</td>
			</tr>
			<tr>
				<td>備考</td>
				<td>${account.remarks}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<a href="/kintai/PersonalSettingsEmpMaster?action=done">修正する</a><br>
		<a href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>