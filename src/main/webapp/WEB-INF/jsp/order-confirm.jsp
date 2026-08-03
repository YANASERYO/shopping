<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注文内容の確認</title>
<link rel="stylesheet" href="css/style.css">
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

<h2>注文内容確認</h2>

<h3>商品内容</h3>

<c:forEach var="cart" items="${cartList}">
    <div>
        <p>商品名：${cart.productName}</p>
        <p>価格：${cart.productPrice}円</p>
        <p>購入数：${cart.cartQuantity}</p>
        <p>
            小計：
            ${cart.productPrice * cart.cartQuantity}円
        </p>
    </div>
    <hr>
</c:forEach>

<p>税抜合計：${shoppingTotalPrice}円</p>
<p>消費税：${taxPrice}円</p>
<p>税込合計：${taxAndShoppingPrice}円</p>

<h3>発送先</h3>

<p>氏名：${orderInfo.shippingName}</p>
<p>郵便番号：${orderInfo.shippingPostalCode}</p>
<p>住所：${orderInfo.shippingAddress}</p>
<p>電話番号：${orderInfo.shippingPhone}</p>
<p>メールアドレス：${orderInfo.shippingEmail}</p>
<p>支払い方法：${orderInfo.shippingPayment}</p>

<form action="${pageContext.request.contextPath}/order/complete"
      method="post">

    <input type="hidden"
           name="shippingName"
           value="${orderInfo.shippingName}">

    <input type="hidden"
           name="shippingPostalCode"
           value="${orderInfo.shippingPostalCode}">

    <input type="hidden"
           name="shippingAddress"
           value="${orderInfo.shippingAddress}">

    <input type="hidden"
           name="shippingPhone"
           value="${orderInfo.shippingPhone}">

    <input type="hidden"
           name="shippingEmail"
           value="${orderInfo.shippingEmail}">

    <input type="hidden"
           name="shippingPayment"
           value="${orderInfo.shippingPayment}">

    <button type="submit">注文を確定する</button>
</form>

<br>

<form action="${pageContext.request.contextPath}/order/buy"
      method="get">
    <button type="submit">戻る</button>
</form>

</body>
</html>