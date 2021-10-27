<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/kintai/common/css/empMaster.css" rel="stylesheet" type="text/css" />
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
				<td>${account.tel}</td>
			</tr>
			<tr>
				<td class="item_name">メールアドレス</td>
				<td>${account.mail}</td>
			</tr>
			<tr>
				<td class="item_name">入社日</td>
				<td>${account.hireDate}</td>
			</tr>
			<tr>
				<td class="item_name">パスワード</td>
				<td>-</td>
			</tr>
			<tr>
				<td class="item_name">備考</td>
				<td>${account.remarks}</td>
			</tr>
		</table>
	</div>
	<div class="bottom_btn_box" align="center">
		<a class="btn" href="/kintai/PersonalSettingsEmpMaster?action=done">修正する</a><br>
		<a class="btn" href="/kintai/Forward?action=empMenu">メニューに戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>