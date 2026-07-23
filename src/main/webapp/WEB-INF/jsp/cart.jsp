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

<table border="1">
    <tr>
        <th>商品ID</th>
        <th>数量</th>
    </tr>

<%
List<Cart> cartList = (List<Cart>)request.getAttribute("cartList");

if (cartList != null) {
    for (Cart cart : cartList) {
%>

<tr>
	<td><%= cart.getProductId() %></td>
	
	<td>
		<form action="/cart/update" method="post">
		
			<input type="hidden"
					name="cartId"
					value="<%= cart.getCartId() %>">
					
			<input type="number"
					name="quantity"
					value="<%= cart.getCartQuantity() %>"
					min="1">
					
			<input type="submit" value="更新">
		</form>
	</td>
	
	
</tr>

<%
    }
}
%>


</table>
<br>
<a href="/products">商品一覧へ戻る</a>
</body>
</html>