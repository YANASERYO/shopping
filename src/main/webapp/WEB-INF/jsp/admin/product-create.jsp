<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品新規登録</title>
</head>
<body>

<h2>商品新規登録</h2>

<form action="product-create" method="post">

<p>
商品名<br>
<input type="text" name="productName">
</p>

<p>
価格<br>
<input type="number" name="productPrice">
</p>

<p>
在庫数<br>
<input type="number" name="productStock">
</p>

<p>
カテゴリー<br>
<input type="text" name="productCategory">
</p>

<p>
画像パス<br>
<input type="text" name="productImgPath">
</p>

<p>
商品説明<br>
<textarea name="productDescription" rows="5" cols="40"></textarea>
</p>

<p>
販売状態<br>
<select name="productActive">
    <option value="true">販売中</option>
    <option value="false">販売停止</option>
</select>
</p>

<input type="submit" value="登録">

</form>

<br>

<form action="menu" method="get">
    <input type="submit" value="メニューへ戻る">
</form>

</body>
</html>