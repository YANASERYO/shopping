<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文者情報入力</title>
</head>
<body>
<h2>注文者情報入力</h2>
<form action="${pageContext.request.contextPath}/order/confirm" method="post">
	<p>
		発送先氏名<br>
		<input type="text" name="shippingName" value="${account.accountName}" required>
	</p>
	<p>
		郵便番号<br>
		<input type="text" name="shippingPostalCode" value="${account.postalCode}" required>
	</p>
	<p>
		発送先住所<br>
		<input type="text" name="shippingAddress" value="${account.accountAddress}" size="50" required>
	</p>
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

	<input type="submit" value="注文確定">
</form>
<p>
	<a href="${pageContext.request.contextPath}/cart">
		カートへ戻る
	</a>
</p>
</body>
</html>