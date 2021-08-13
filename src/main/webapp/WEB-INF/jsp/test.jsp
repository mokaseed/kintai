<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
					<td><input type="password" name="pass" value="${account.pass}"></td>
				</tr>
				<tr>
					<td>パスワード(確認用)</td>
					<td><input type="password" name="passCheck" value="${account.pass}"></td>
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
	<div class="cotainer">
  <div class="row justify-content-center mt-5">
    <div class="col-md-8">
      <div class="card">
        <div class="card-header">パスワードのチェック</div>
        <div class="card-body">

          <form action="" method="post" onsubmit="">
            <div class="form-group row">
              <label for="password" class="col-md-4 col-form-label text-md-right">パスワード</label>
              <div class="col-md-6">
                <input type="password" id="password" class="form-control" name="password" required>
              </div>
            </div>
            <div class="form-group row">
              <label for="confirm-password" class="col-md-4 col-form-label text-md-right">パスワード（確認）</label>
              <div class="col-md-6">
                <input type="password" id="confirm-password" class="form-control" name="confirm-password" required>
                <div class="valid-feedback">O.K.</div>
                <div class="invalid-feedback">入力されたパスワードが一致しません。</div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-8 offset-md-4">
                <button class="btn btn-primary" type="button" onclick="">
                  登録
                </button>
                <button class="btn btn-secondary ml-3" type='button' onclick="document.location.href='';">
                  戻る
                </button>
              </div>
            </div>
          </form>

        </div><!-- div class="card-body" -->
      </div><!-- div class="card" -->
    </div><!-- div class="col-md-8" -->
  </div><!-- div class="row justify-content-center" -->
</div><!-- div class="cotainer" -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
<script src="${pageContext.request.contextPath}/common/JS/test.js"></script>
</body>
</html>