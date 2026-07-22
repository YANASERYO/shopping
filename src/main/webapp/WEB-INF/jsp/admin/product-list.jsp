<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品管理一覧</title>
</head>
<body>

<h1>商品管理一覧</h1>

<p>
<a href="${pageContext.request.contextPath}/admin/products/create">
商品を新規登録
</a>
</p>

<table border="1">
<tr>
<th>商品ID</th>
<th>商品名</th>
<th>価格</th>
<th>在庫数</th>
<th>カテゴリー</th>
<th>販売状態</th>
<th>操作</th>
</tr>

<c:forEach var="product" items="${productList}">
<tr>
<td>${product.productId}</td>
<td>${product.productName}</td>
<td>${product.productPrice}円</td>
<td>${product.productStock}</td>
<td>${product.productCategory}</td>

<td>
<c:choose>
<c:when test="${product.productActive}">
販売中
</c:when>
<c:otherwise>
販売停止
</c:otherwise>
</c:choose>
</td>

<td>
<a href="${pageContext.request.contextPath}/admin/products/edit?id=${product.productId}">
編集
</a>
</td>
</tr>
</c:forEach>

</table>

<p>
<a href="${pageContext.request.contextPath}/admin">
管理者メニューへ戻る
</a>
</p>

</body>
</html>