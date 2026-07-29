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
<title>管理者メニュー</title>
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
 
<h1 class="admin-title">管理者メニュー</h1>
<div class="admin-tags">
<p><a href="${pageContext.request.contextPath}/admin/products/create">商品を登録する</a></p>
<p><a href="${pageContext.request.contextPath}/admin/products">商品を編集する</a></p>
<p><a href="${pageContext.request.contextPath}/logout">ログアウト</a></p>
</div>
</body>
</html>