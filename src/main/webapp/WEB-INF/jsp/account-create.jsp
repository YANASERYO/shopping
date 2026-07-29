<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント作成</title>
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
	<h2>アカウント作成</h2>
	<form action="${pageContext.request.contextPath}/account-create" method="post">
		<p>アカウントID<br><input type="text" name="accountId" required></p>
		<p>パスワード<br><input type="password" name="accountPass" required></p>
		<p>氏名<br><input type="text" name="accountName" required></p>	
		<p>郵便番号<br><input type="text" name="postalCode" required></p>
		<p>住所<br><input type="text" name="accountAddress" required></p>
		<p>電話番号<br><input type="text" name="accountPhone" required></p>
		<p>生年月日<br><input type="date" name="birthday" required></p>
		<p>メールアドレス<br><input type="email" name="email" required></p>
		<p>支払い方法<br>
			<select name="payment" required>
			    <option value="">選択してください</option>
			    <option value="クレジットカード">クレジットカード</option>
			    <option value="代金引換">代金引換</option>
			    <option value="銀行振込">銀行振込</option>
			</select>
		</p>
		<input type="submit" value="登録する" onclick="clickEvent()">
	</form>
	<br>
	<form action="${pageContext.request.contextPath}/login" method="get">
	    <input type="submit" value="ログイン画面へ戻る">
	</form>
	
	<script>
	function clickEvent(){
		alert('会員登録しました');
		}
	</script>
</body>
</html>