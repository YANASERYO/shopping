<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.model.Cart" %>
<%@ page import="com.example.demo.util.PriceUtil" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
</head>
<body>

<h2>ショッピングカート</h2>


<%List<Cart> cartList =(List<Cart>) request.getAttribute("cartList");
if (cartList == null || cartList.isEmpty()) 
{%>
<p>カートに商品はありません。</p>
<%
} else {
	int cartTotalPrice = 0;
%>

<table border="1">
    <tr>
		<th>画像</th>
		<th>商品ID</th>
		<th>商品名</th>
		<th>単価</th>
		<th>数量</th>
		<th>小計</th>
		<th>変更</th>
	<th>削除</th>
</tr>

<%
for (Cart cart : cartList) {
	int productTotal = cart.getProductTotal();
	cartTotalPrice += productTotal;
%>

<tr>
	<td>
		<%
		if (cart.getProductImgPath() != null
				&& !cart.getProductImgPath().isBlank()) {
		%>
			<img
				src="<%= request.getContextPath() %><%= cart.getProductImgPath() %>"
				alt="<%= cart.getProductName() %>"
				width="100">
		<%
		} else {
		%>
			画像なし
		<%
		}
		%>
	</td>
	<td><%= cart.getProductId() %></td>
	<td><%= cart.getProductName() %></td>
	<td><%= cart.getFormattedProductPrice() %>円</td>
	<td><%= cart.getCartQuantity() %></td>
	<td><%= cart.getFormattedProductTotal() %>円</td>
	<td>
	<form action="<%= request.getContextPath() %>/cart/update" method="post">
		<input type="hidden" name="cartId" value="<%= cart.getCartId() %>">
		<input type="hidden" name="productId" value="<%= cart.getProductId() %>">
		<input type="number" name="quantity" value="<%= cart.getCartQuantity() %>" min="1" required>
		<input type="submit" value="更新">
	</form>
    </td>
    <td>
    <form action="<%= request.getContextPath() %>/cart/delete" method="post">
	    <input type="hidden" name="cartId" value="<%= cart.getCartId() %>">
	    <input type="submit" value="削除">
    </form>
	</td>
</tr>
<%
}
%>
</table>
<p>
	合計金額：
	<strong>
		<%= PriceUtil.formatWithCommas(cartTotalPrice) %>円
	</strong>
</p>
<%
}
%>
<a href="<%= request.getContextPath() %>/products"> 商品一覧へ戻る </a>
<a href="<%= request.getContextPath() %>/menu"> メニューへ戻る </a>
<form action="<%= request.getContextPath() %>/order/buy" method="get">
    <input type="submit" value="購入する">
</form>
</body>
</html>