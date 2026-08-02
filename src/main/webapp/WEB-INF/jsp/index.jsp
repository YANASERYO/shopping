<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>LOGIN｜KINARI</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
</head>

<body>
 <header>
    <img class="logo" src="images/logo.png">
    <h1>KINARI</h1>
  </header>

  <main class="loginbackground">

    <h2>LOGIN</h2>

<p class="message">
  オンラインストアを利用するにはアカウントにログインしてください。
</p>

<form action="${pageContext.request.contextPath}/login" method="post">

    <div class="inputbox">
    <input type="text" name="accountId" placeholder="会員ID">

    
    <input type="password" name="accountPass" placeholder="パスワード">
    </div>

    <input class="login-btn" type="submit" value="ログイン">
  
    <hr>
    <p class="register-text">
      未登録のお客様はこちらから
    </p>

</form>

     <p class="register-area">
      <a class="register-btn" href="${pageContext.request.contextPath}/account-create">新規会員登録</a></p>
          
  </main>
 	<footer>
 	<a href="${pageContext.request.contextPath}/recruit">採用情報</a>
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

  </footer>
</body>
</html>
