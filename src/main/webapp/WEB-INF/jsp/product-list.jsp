<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.example.demo.model.Account" %>
<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ITEMS｜KINARI</title>
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
  <main class="product-list">

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

<h2 class="menutitle">ITEMS</h2>
<h3 class="subtitle">CATEGORY</h3>
<div class="category-menu">
<a href="#table">
Dining Table<br>ダイニングテーブル
</a>
<a href="#chair">
Dining Chair<br>ダイニングチェア
</a>
<a href="#sofa">
Sofa<br>ソファ
</a>
<a href="#storage">
Storage<br>収納
</a>
</div>

<c:if test="${empty productList}"> <p>現在、販売中の商品はありません。</p>
</c:if>

<c:forEach var="product" items="${productList}">

<hr>
<c:if test="${product.productCategory == 'テーブル' }">
<p class="category-name" id="table">
Dining Table<br>
ダイニングテーブル
</p>
</c:if>

<c:if test="${product.productCategory == 'チェア' }">
<p class="category-name" id="chair">
Dining Chair<br>
ダイニングチェア
</p>
</c:if>

<c:if test="${product.productCategory == 'ソファ' }">
<p class="category-name" id="sofa">
Sofa<br>
ソファ
</p>
</c:if>

<c:if test="${product.productCategory == '収納家具' }">
<p class="category-name" id="storage">
Storage<br>
収納
</p>
</c:if>

<div class="product-detail">
	<img class="product-img" src="${product.productImgPath}">
<div class="product-description">
<p class="product-name">
	${product.productName}
</p>


<p class="product-price">
	￥${product.formattedProductPrice}(税抜)

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
</div>
</div>

<c:choose>

	<c:when test="${product.productStock > 0}">

		<form action="${pageContext.request.contextPath}/cart/add"
			  method="post">

			<input type="hidden"
				   name="productId"
				   value="${product.productId}">
			<div class="quantity">
			数量：
			<input type="number"
				   name="quantity"
				   value="1"
				   min="1"
				   max="${product.productStock}">
				   
			<input class="cart-btn" type="submit" value="カートに追加">
			</div>
		</form>
		
	</c:when>
	
	<c:otherwise>
		<p>在庫切れ</p>
	</c:otherwise>
	
</c:choose>

</c:forEach>

<hr>
<div class="link-btn">
<p>
<a href="${pageContext.request.contextPath}/menu">
	メニューへ戻る
</a>
</p>

<p>
<a href="${pageContext.request.contextPath}/cart">
	カートを見る
</a>
</p>
</div>

</main>
<footer>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

</footer>

</body>
</html>
