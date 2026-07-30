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
<title>商品新規登録</title>
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

<h1 class="admin-title">商品新規登録</h1>

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
