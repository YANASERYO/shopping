<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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

<c:if test="${not empty loginError}">
    <p class="login-error">
        <c:out value="${loginError}" />
    </p>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">

    <div class="inputbox">
    <input type="text" name="accountId" value="<c:out value='${accountId}' />" placeholder="会員ID">
	<c:if test="${not empty accountIdError}">
    		<p class="input-error"><c:out value="${accountIdError}" /></p>
    </c:if>
    
    <input type="password" name="accountPass" id="inputPassword" placeholder="パスワード">
    
    <div class="password-check">
    <label for="inputCheckbox"><input id="inputCheckbox" type="checkbox"> パスワードを表示する</label>
    </div>

	<c:if test="${not empty accountPassError}">
    		<p class="input-error"><c:out value="${accountPassError}" /></p>
    </c:if>
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
 	
    <div class="copyright">
      <small>&copy; 2026 KINARI</small>
    </div>

  </footer>
  <script>
  document.addEventListener('DOMContentLoaded', function(event) {

	  const targetElement = document.getElementById('inputPassword');
	  const triggerElement = document.getElementById('inputCheckbox');

	  triggerElement.addEventListener('change', function(event) {
	    if ( this.checked ) {
	      targetElement.setAttribute('type', 'text');
	    } else {
	      targetElement.setAttribute('type', 'password');
	    }
	  }, false);

	}, false);
  </script>
<!--  account-delete-confirm.jsp でアカウント削除が成功した場合にアラートを表示-->
  <c:if test="${accountDeleted}">
    <script>
        alert("アカウントを削除しました");
    </script>
</c:if>
</body>
</html>
