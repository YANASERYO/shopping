<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文内容確認</title>
</head>
<body>

<h2>注文内容確認</h2>

<p>商品名：${product.productName}</p>
<p>価格：${product.productPrice}円</p>
<p>購入数：${cart.cartQuantity}</p>
<p>合計金額：${orderInfo.shoppingTotalPrice}円</p>

<h3>発送先</h3>

<p>氏名：${orderInfo.shippingName}</p>
<p>郵便番号：${orderInfo.shippingPostalCode}</p>
<p>住所：${orderInfo.shippingAddress}</p>
<p>電話番号：${orderInfo.shippingPhone}</p>
<p>メールアドレス：${orderInfo.shippingEmail}</p>
<p>支払い方法：${orderInfo.shippingPayment}</p>

<form action="order-complete" method="post">
    <input type="submit" value="注文を確定する">
</form>

<br>

<form action="order-info" method="get">
    <input type="submit" value="戻る">
</form>

</body>
</html>