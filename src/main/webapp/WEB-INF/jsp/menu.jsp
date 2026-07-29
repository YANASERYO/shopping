<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.model.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
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
    <header>
    <div class="logotitle">
    <img class="logo" src="images/logo.png">
    <h1>KINARI</h1>
    </div>
    <%Account account = (Account) session.getAttribute("account");%>
 	<div class="user-area">
	<p class="username">ようこそ <%= account.getAccountName() %> さん</p>
    <a class="logout" href="${pageContext.request.contextPath}/logout">ログアウト</a>
    </div>
  </header>
  <main class="munubackground">

<div class="roomimg">
    <h2 class="concept">
        －自然と暮らす、心地よい毎日－
    </h2>
</div>
<div class="js-scroll-item">
	<h3 class="menutitle">ABOUT</h3>
	<p class="menutitleja">KINARIについて</p>
	<p class="menutext">KINARI（キナリ）は、木の温もりと北欧デザインを大切にした家具・インテリアをお届けするオンラインショップです。<br>
	
	「自然と暮らす、心地よい毎日。」をコンセプトに、毎日の暮らしに寄り添う家具や照明、インテリアをご提案します。
	
	お気に入りの一品との出会いを通して、心豊かで快適な住まいづくりをお手伝いします。</p>
</div>
<br>
<div class="js-scroll-item">
	<h4 class="menutitle">MENU</h4>
	<div class="menu-contents">
	
		<a class="menu1" href="${pageContext.request.contextPath}/products"><img src="images/menu1.png" alt="商品を選択する"></a>
		<a class="menu2" href="${pageContext.request.contextPath}/cart"><img src="images/menu2.png" alt="ショッピングカートを見る"></a>
		<a class="menu3" href="${pageContext.request.contextPath}/orders"><img src="images/menu3.png" alt="注文履歴を見る"></a>
		<a class="menu4" href="${pageContext.request.contextPath}/account-edit"><img src="images/menu4.png" alt="会員情報の変更"></a>
	
	</div>
</div>

  </main>
<footer>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

</footer>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>