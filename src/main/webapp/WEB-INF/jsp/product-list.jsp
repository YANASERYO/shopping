<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>

<h1>商品一覧</h1>

<c:if test="${empty productList}"> <p>現在、販売中の商品はありません。</p>
</c:if>

<c:forEach var="product" items="${productList}">

```
<hr>

<p>
	商品名：${product.productName}
</p>

<p>
	価格：${product.productPrice}円
</p>

<p>
	在庫数：${product.productStock}
</p>

<p>
	カテゴリー：${product.productCategory}
</p>

<p>
	商品説明：${product.productDescription}
</p>

<c:choose>

	<c:when test="${product.productStock > 0}">

		<form action="${pageContext.request.contextPath}/cart/add"
			  method="post">

			<input type="hidden"
				   name="productId"
				   value="${product.productId}">

			数量：
			<input type="number"
				   name="quantity"
				   value="1"
				   min="1"
				   max="${product.productStock}">

			<input type="submit"
				   value="カートに追加">

		</form>

	</c:when>

	<c:otherwise>
		<p>在庫切れ</p>
	</c:otherwise>

</c:choose>
```

</c:forEach>

<hr>

<p>
<a href="${pageContext.request.contextPath}/Menu">
	メニューへ戻る
</a>
</p>

<p>
<a href="${pageContext.request.contextPath}/cart">
	カートを見る
</a>
</p>

</body>
</html>
