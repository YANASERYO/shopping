<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.model.Cart" %>

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
%>

<table border="1">
    <tr>
        <th>商品ID</th>
        <th>数量</th>
        <th>変更</th>
        <th>削除</th>
    </tr>

<%
for (Cart cart : cartList) {

%>

<tr>
	<td><%= cart.getProductId() %></td>

	<td><%= cart.getCartQuantity() %></td>
	<td>
	<form action="<%= request.getContextPath() %>/cart/update" method="post">
		<input type="hidden" name="cartId" value="<%= cart.getCartId() %>">
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