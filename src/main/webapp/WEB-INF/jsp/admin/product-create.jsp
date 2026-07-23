<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品新規登録</title>
</head>
<body>

<h1>商品新規登録</h1>

<%-- エラーメッセージ --%>

<p>${errorMessage}</p>

<form action="${pageContext.request.contextPath}/admin/products/create" method="post">

<p>
<label for="productName">商品名</label><br>
<input type="text" id="productName" name="productName" required>
</p>

<p>
<label for="productPrice">価格</label><br>
<input type="number" id="productPrice" name="productPrice" min="0" required>
</p>

<p>
<label for="productStock">在庫数</label><br>
<input type="number" id="productStock" name="productStock" min="0" required>
</p>

<p>
<label for="productCategory">カテゴリー</label><br>
<input type="text" id="productCategory" name="productCategory" required>
</p>

<p>
<label for="productImgPath">画像パス</label><br>
<input type="text" id="productImgPath" name="productImgPath">
</p>

<p>
<label for="productDescription">商品説明</label><br>
<textarea id="productDescription" name="productDescription" rows="5" cols="40"></textarea>
</p>

<p>
<label for="productActive">販売状態</label><br>
<select id="productActive" name="productActive">
<option value="true">販売中</option>
<option value="false">販売停止</option>
</select>
</p>

<p>
<input type="submit" value="登録">
</p>

</form>

<form action="${pageContext.request.contextPath}/admin" method="get">
<input type="submit" value="管理者メニューへ戻る">
</form>

</body>
</html>
