<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="com.example.demo.model.Account" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント削除｜KINARI</title>
<style>
.account-delete-confirm {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
}

.policy-box {
	width: 400px;
	height: 200px;
	border: 1px solid #ccc;
	padding: 15px;
	overflow-y: scroll;
	background: #f9f9f9;
}

.agree-area {
	margin-top: 15px;
}
</style>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/account-delete.css">
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bungee&family=M+PLUS+Rounded+1c:wght@900&family=Philosopher:ital,wght@0,400;0,700;1,400;1,700&family=Zen+Old+Mincho:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
	<header>
		<div class="logotitle">
			<img class="logo" src="images/logo.png">
			<h1>KINARI</h1>
		</div>
		<%Account account = (Account) session.getAttribute("account");%>
		<div class="header-menu">
			<a href="${pageContext.request.contextPath}/products"> 商品一覧へ戻る</a> 
			<a href="${pageContext.request.contextPath}/menu"> メニューへ戻る</a> 
			<a href="${pageContext.request.contextPath}/cart"> カートを見る</a>
		</div>
		<div class="user-area">
			<p class="username">
				ようこそ
				<%=account.getAccountName()%>
				さん
			</p>
			<a class="logout" href="${pageContext.request.contextPath}/logout">ログアウト</a>
		</div>
	</header>
	<main class="account-delete-confirm">
		<h2>アカウント削除確認</h2>
		<c:if test="${not empty accountDeleteError}">
			<p class="error-message">
				<c:out value="${accountDeleteError}" />
			</p>
		</c:if>
		<div class="policy-box" id="delete-policy">
			<h3>アカウントを削除する前にご確認ください</h3>
			<p>アカウントを削除すると、このアカウントでは ログインできなくなります。</p>
			<p>登録されている会員情報も利用できなくなります。</p>
			<p>注文履歴や購入履歴も削除されます。</p>
			<p>削除したアカウントは復元できません。</p>
			<p>アカウントを削除する前に、必要な情報は
			バックアップを取ることをおすすめします。</p>
			<p>一度削除すると、同じIDでの再登録はできません。</p>
			<p>この操作は取り消すことができません。</p>
			<p>
				削除するアカウント名：<c:out value="${account.accountName}" />様
			</p>
		</div>
		<form action="${pageContext.request.contextPath}/account-delete" method="post" id="accountDeleteForm">
			<div class="delete-confirm-checkbox">
				<label> <input type="checkbox" name="deleteConfirmed" value="true" id="deleteConfirmed"> 
				上記の重要事項を確認し、アカウントの削除に同意します
				</label>
			</div>
			<button type="submit" class="account-delete-btn" id="accountDeleteButton" disabled>アカウントを削除する</button>
		</form>
		<div class="link-btn">
			<a href="${pageContext.request.contextPath}/account-edit">アカウント編集へ戻る </a>
		</div>
	</main>
	<footer>
		<div class="copyright">
			<small>&copy; 2026 KINARI</small>
		</div>
	</footer>
	<script>
        const policy = document.getElementById('policy');
        const agreeCheck = document.getElementById('agree-check');
        const submitBtn = document.getElementById('submit-btn');


        policy.addEventListener('scroll', () => {

            if (policy.scrollTop + policy.clientHeight >= policy.scrollHeight - 2) {
                agreeCheck.disabled = false;
            }
        });

        // チェックボックスの状態に合わせてボタンの活性・非活性を切り替え
        agreeCheck.addEventListener('change', () => {
            submitBtn.disabled = !agreeCheck.checked;
        });
    </script>
	<script>
		const deleteConfirmed = document.getElementById("deleteConfirmed");
		const deleteButton = document.getElementById("accountDeleteButton");
		const deleteForm = document.getElementById("accountDeleteForm");
		deleteConfirmed.addEventListener("change", function() {
			deleteButton.disabled = !this.checked;
		});
		deleteForm.addEventListener("submit", function(event) {
			if (!deleteConfirmed.checked) {
				event.preventDefault();
				alert("重要事項を確認し、チェックを入れてください。");
				return;
			}
			const confirmed = confirm("本当にアカウントを削除しますか？\nこの操作は取り消せません。");
			if (!confirmed) {
				event.preventDefault();
			}
		});
	</script>
</body>
</html>