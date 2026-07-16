<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品編集・削除</title>
</head>
<body>

<h2>商品編集・削除</h2>

<form action="product-edit" method="post">

<p>
商品ID<br>
<input type="text" name="productId" value="${product.productId}" readonly>
</p>

<p>
商品名<br>
<input type="text" name="productName" value="${product.productName}">
</p>

<p>
価格<br>
<input type="number" name="productPrice" value="${product.productPrice}">
</p>

<p>
在庫数<br>
<input type="number" name="productStock" value="${product.productStock}">
</p>

<p>
カテゴリー<br>
<input type="text" name="productCategory" value="${product.productCategory}">
</p>

<p>
画像パス<br>
<input type="text" name="productImgPath" value="${product.productImgPath}">
</p>

<p>
商品説明<br>
<textarea name="productDescription" rows="5" cols="40">${product.productDescription}</textarea>
</p>

<p>
販売状態<br>
<select name="productActive">
    <option value="true">販売中</option>
    <option value="false">販売停止</option>
</select>
</p>

<input type="submit" name="action" value="更新">
<input type="submit" name="action" value="削除">

</form>

<br>

<form action="menu" method="get">
    <input type="submit" value="メニューへ戻る">
</form>

</body>
</html>