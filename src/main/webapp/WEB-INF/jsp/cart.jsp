<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.model.Cart" %>
<%@ page import="com.example.demo.util.PriceUtil" %>
<%@ page import="com.example.demo.model.Account" %>
<%@ page import="com.example.demo.util.TaxUtil" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CART｜KINARI</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bungee&family=M+PLUS+Rounded+1c:wght@900&family=Philosopher:ital,wght@0,400;0,700;1,400;1,700&family=Zen+Old+Mincho:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
<header >
	<div class="logotitle">
    <img class="logo" src="images/logo.png">
    <h1>KINARI</h1>
    </div>
    <%Account account = (Account) session.getAttribute("account");%>
 	<div class="header-menu">
 	<a href="${pageContext.request.contextPath}/products">
	商品一覧へ戻る</a>
 	<a href="${pageContext.request.contextPath}/menu">
	メニューへ戻る</a>
	<a href="${pageContext.request.contextPath}/cart">
	カートを見る</a>
	</div>
	<div class="user-area">
	<p class="username">ようこそ <%= account.getAccountName() %> さん</p>
    <a class="logout" href="${pageContext.request.contextPath}/logout">ログアウト</a>
    </div>
  </header>
  
  <main>

<c:if test="${not empty cartMessage}">
	<p class="message success-message">
		<c:out value="${cartMessage}" />
	</p>
</c:if>

<c:if test="${not empty cartError}">
	<p class="message error-message">
		<c:out value="${cartError}" />
	</p>
</c:if>

<h2 class="menutitle">CART</h2>


<%List<Cart> cartList =(List<Cart>) request.getAttribute("cartList");
if (cartList == null || cartList.isEmpty()) 
{%>
<p class="not-in-the-cart">カートに商品はありません。</p>
<%
} else {
	int cartTotalPrice = 0;
%>
<div class= "cart-list">
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
</div>
<div class="cart-price">
<p>
	小計：
	<strong>
		<%= PriceUtil.formatWithCommas(cartTotalPrice) %>円
	</strong>
</p>
<%
	int salesTax = TaxUtil.inflictTax(cartTotalPrice);
	int priceAndTax = TaxUtil.inflictPriceAndTax(cartTotalPrice);
%>
<p>
	消費税（10％）：
	<strong>
		<%= PriceUtil.formatWithCommas(salesTax) %>円
	</strong>
</p>

<p>
	税込合計：
	<strong>
		<%= PriceUtil.formatWithCommas(priceAndTax) %>円
	</strong>
</p>


</div>


<form action="<%= request.getContextPath() %>/order/buy" method="get">
    <input class="buy-btn" type="submit" value="購入する">
</form>


<%
}
%>
<div class="link-btn">
<a href="<%= request.getContextPath() %>/products"> 商品一覧へ戻る </a>
<a href="<%= request.getContextPath() %>/menu"> メニューへ戻る </a>
</div>

</main>

<footer>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

</footer>

</body>
</html>