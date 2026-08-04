<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.example.demo.model.Account" %>
<!DOCTYPE html>
<html lang="ja">
<head>
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
<title>商品編集・削除</title>
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
<c:if test="${not empty errorMessage}">
    <p><c:out value="${errorMessage}" /></p>
</c:if>

<h2>商品編集・削除</h2>

<form action="${pageContext.request.contextPath}/admin/products/edit" method="post">

<p>
商品ID<br>
<input type="text" name="productId" value="${product.productId}" readonly>
</p>

<p>
商品名<br>
<input type="text" name="productName" value="${product.productName}">
</p>

<p>
価格<br>
<input type="number" name="productPrice" value="${product.productPrice}">
</p>

<p>
在庫数<br>
<input type="number" name="productStock" value="${product.productStock}">
</p>

<p>
カテゴリー<br>
<input type="text" name="productCategory" value="${product.productCategory}">
</p>

<p>
画像パス<br>
<input type="text" name="productImgPath" value="${product.productImgPath}">
</p>

<p>
商品説明<br>
<textarea name="productDescription" rows="5" cols="40">${product.productDescription}</textarea>
</p>

<p>
販売状態<br>
<select name="productActive">
<option value="true"${product.productActive ? 'selected' : ''}>販売中</option>
<option value="false"${!product.productActive ? 'selected' : ''}>販売停止</option>
</select>
</p>

<input type="submit" name="action" value="更新">
<input type="submit" name="action" value="削除">

</form>

<br>

<form action="${pageContext.request.contextPath}/admin" method="get">
<input type="submit" value="管理者メニューへ戻る">
</form>

</body>
</html>