<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.example.demo.model.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント編集 | KINARI</title>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
<!-- フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bungee&family=M+PLUS+Rounded+1c:wght@900&family=Philosopher:ital,wght@0,400;0,700;1,400;1,700&family=Zen+Old+Mincho:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
<header >
	<div class="logotitle">
    <img class="logo" src="${pageContext.request.contextPath}/images/logo.png">
    <h1>KINARI</h1>
    </div>
    <%Account account = (Account) session.getAttribute("account");%>
 	<div class="header-menu">
 	<a href="${pageContext.request.contextPath}/orders">
	注文履歴を確認</a>
 	<a href="${pageContext.request.contextPath}/menu">
	メニューへ戻る</a>
	<a href="${pageContext.request.contextPath}/cart">
	カートを見る</a>
	</div>
	<div class="user-area">
	<p class="username">ようこそ <%= account.getAccountName() %> さん</p>
    <a class="logout" href="${pageContext.request.contextPath}/logout">ログアウト</a>
    </div>
  </header>
  <main>

<h2>アカウント編集</h2>

<c:if test="${not empty accountEditError}">
		<p>${accountEditError}</p>
	</c:if>
	
	<div class="account-edit-form">
	<form action="${pageContext.request.contextPath}/accountEdit" method="post">

		<p>アカウントID<br>
			<input type="text" value="${account.accountId}" readonly>
		</p>

		<p>氏名<br>
			<input type="text" name="accountName"
				value="${account.accountName}" required>
		</p>

		<p>郵便番号<br>
			<input type="text" id="postalCode" name="postalCode"
				value="${account.postalCode}" maxlength="8" required>
		</p>

		<button type="button"
			class="postal-code-search-button"
			data-postal-code-id="postalCode"
			data-address-id="accountAddress"
			data-message-id="postalCodeMessage"
			data-context-path="${pageContext.request.contextPath}">
			住所検索
		</button>



		<p id="postalCodeMessage"></p>

		<p>住所<br>
			<input type="text" id="accountAddress" name="accountAddress"
				value="${account.accountAddress}" required>
		</p>


		<p>電話番号<br>
			<input type="text" name="accountPhone"
				value="${account.accountPhone}" required>
		</p>

		<p>生年月日<br>
			<input type="date" name="birthday"
				value="${account.birthday}" required>
		</p>

		<p>メールアドレス<br>
			<input type="email" name="email"
				value="${account.email}" required>
		</p>

		<p>支払い方法<br>
			<select name="payment" required>
				<option value="">選択してください</option>
				<option value="クレジットカード"
					${account.payment == 'クレジットカード' ? 'selected' : ''}>
					クレジットカード
				</option>
				<option value="代金引換"
					${account.payment == '代金引換' ? 'selected' : ''}>
					代金引換
				</option>
				<option value="銀行振込"
					${account.payment == '銀行振込' ? 'selected' : ''}>
					銀行振込
				</option>
			</select>
		</p>

		<p>新しいパスワード<br>
			<input type="password" name="accountPass" id="inputPassword">
		</p>
		<div class="password-check">
    	<label for="inputCheckbox"><input id="inputCheckbox" type="checkbox"> パスワードを表示する</label>
    	</div>
		<p class="account-message">変更しない場合は空欄にしてください。</p>

		<input class="edit-btn" type="submit" value="更新">
	</form>
	</div>
	<div class="link-btn">
		<a href="${pageContext.request.contextPath}/menu">
		メニューへ戻る
		</a>
	</div>

</main>

<footer>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

</footer>

<script src="${pageContext.request.contextPath}/js/postal-code.js"></script>
<script>
  document.addEventListener('DOMContentLoaded', function(event) {

	  const targetElement = document.getElementById('inputPassword');
	  const triggerElement = document.getElementById('inputCheckbox');

	  triggerElement.addEventListener('change', function(event) {
	    if ( this.checked ) {
	      targetElement.setAttribute('type', 'text');
	    } else {
	      targetElement.setAttribute('type', 'password');
	    }
	  }, false);

	}, false);
  </script>

</body>
</html>