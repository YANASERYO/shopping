<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.example.demo.model.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文者情報入力</title>
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
 	<a href="${pageContext.request.contextPath}/products">
	商品一覧へ戻る</a>
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
<h2>注文者情報入力</h2>
<form action="${pageContext.request.contextPath}/order/confirm" method="post">
	<p>
		発送先氏名<br>
		<input type="text" name="shippingName" value="${account.accountName}" required>
	</p>
	<p>
		郵便番号<br>
		<input type="text" id="shippingPostalCode" name="shippingPostalCode" value="${account.postalCode}" maxlength="8" required>
	</p>
	<button type="button"
		class="postal-code-search-button"
		data-postal-code-id="shippingPostalCode"
		data-address-id="shippingAddress"
		data-message-id="shippingPostalCodeMessage"
		data-context-path="${pageContext.request.contextPath}">
		住所検索
	</button>
	<p id="shippingPostalCodeMessage"></p>
	<p>
		発送先住所<br>
		<input type="text" id="shippingAddress" name="shippingAddress" value="${not empty orderInfo.shippingAddress ? orderInfo.shippingAddress : account.accountAddress}" size="50" required>
	</p>
	<c:if test="${not empty shippingAddressError}">
		<p class="error-message">
			<c:out value="${shippingAddressError}" />
		</p>
	</c:if>
	<p>
		電話番号<br>
		<input type="text" name="shippingPhone" value="${account.accountPhone}" required>
	</p>
	<p>
		メールアドレス<br>
		<input type="email" name="shippingEmail" value="${account.email}" size="40" required>
	</p>
	<p>
		支払い方法<br>
		<select name="shippingPayment" required>
			<option value="クレジットカード"
				<c:if test="${account.payment == 'クレジットカード'}">
					selected
				</c:if>>
				クレジットカード
			</option>
			<option value="代引き"
				<c:if test="${account.payment == '代引き'}">
					selected
				</c:if>>
				代引き
			</option>
			<option value="銀行振込"
				<c:if test="${account.payment == '銀行振込'}">
					selected
				</c:if>>
				銀行振込
			</option>
		</select>
	</p>
	<p>
		商品小計：${shoppingTotalPrice}円
	</p>
	<p>
		消費税：${taxPrice}円
	</p>
	<p>
		税込合計：<strong>${taxAndShoppingPrice}円</strong>
	</p>

	<input type="submit" value="次へ">
</form>
<div class="link-btn">
<p>
	<a href="${pageContext.request.contextPath}/cart">
		カートへ戻る
	</a>
	<a href="${pageContext.request.contextPath}/menu">

	メニューへ戻る
	</a>
</p>

</div>
</main>
<footer>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

</footer>

<script src="${pageContext.request.contextPath}/js/postal-code.js"></script>

</body>
</html>