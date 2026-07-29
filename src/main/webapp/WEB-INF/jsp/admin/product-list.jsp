<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.example.demo.model.Account" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bungee&family=M+PLUS+Rounded+1c:wght@900&family=Philosopher:ital,wght@0,400;0,700;1,400;1,700&family=Zen+Old+Mincho:wght@400;600&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>商品管理一覧</title>
</head>
<body>
<header>

	
   
    
    <h1>KINARI</h1>
    </div>
    <%Account account = (Account) session.getAttribute("account");%>
 	
 	
		<div class="user-area">
			<p class="username">ようこそ <%= account.getAccountName() %> さん</p>
    		<a class="logout" href="${pageContext.request.contextPath}/logout">ログアウト</a>
    	</div>
   
    
 </header>
 
<h1 class="admin-title">商品管理一覧</h1>

<div class="admin-tags">
<p>
<a href="${pageContext.request.contextPath}/admin/products/create">
商品を新規登録
</a>
</p>

<table class="admin-table">
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
</div>
</body>
</html>