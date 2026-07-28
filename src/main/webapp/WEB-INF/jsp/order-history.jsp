<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
</head>
<body>

<h2>購入履歴</h2>

<c:choose>
	<c:when test="${empty detailList}">
		<p>購入履歴はありません。</p>
	</c:when>

	<c:otherwise>
		<table border="1">
			<tr>
				<th>注文番号</th>
				<th>商品名</th>
				<th>価格</th>
				<th>数量</th>
				<th>小計</th>
<!--				<th>税込</th>-->
			</tr>

			<c:forEach var="detail" items="${detailList}">
				<tr>
					<td>${detail.shoppingId}</td>
					<td>${detail.productName}</td>
					<td>${detail.productPrice}円</td>
					<td>${detail.productPieces}</td>
					<td>${detail.productTotal}円</td>
<!--					<td>${detail.productTaxAndPrice}円</td>-->
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/menu">
	メニューへ戻る
</a>

</body>
</html>