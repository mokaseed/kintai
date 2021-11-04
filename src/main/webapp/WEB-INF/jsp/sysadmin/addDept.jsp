<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<h1>事業部新規登録</h1>
	</div>
	<div class="main_wrapper" align="center">
		<form action="<%= request.getContextPath() + "/DeptMaster" %>" method="post">
			<table>
				<tr>
					<td class="item_name">事業部名</td>
					<td><input class="add_dept_text" type="text" name="deptName"></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="regist">
			<br><input class="bottom_btn" type="submit" value="登録する">
		</form>
	</div>
	<div align="center">
		<a class="bottom_btn" href="<%= request.getContextPath() + "/DeptMaster" %>">戻る</a>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>