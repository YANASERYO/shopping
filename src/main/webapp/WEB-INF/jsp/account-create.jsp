<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント作成</title>
</head>
<body>

<h2>アカウント作成</h2>

<form action="account-create" method="post">

<p>
氏名<br>
<input type="text" name="accountName" required>
</p>

<p>
パスワード<br>
<input type="password" name="accountPass" required>
</p>

<p>
郵便番号<br>
<input type="text" name="postalCode" required>
</p>

<p>
住所<br>
<input type="text" name="accountAddress" required>
</p>

<p>
電話番号<br>
<input type="text" name="accountPhone" required>
</p>

<p>
生年月日<br>
<input type="date" name="birthday" required>
</p>

<p>
メールアドレス<br>
<input type="email" name="email" required>
</p>

<p>
支払い方法<br>
<select name="payment" required>
    <option value="">選択してください</option>
    <option value="クレジットカード">クレジットカード</option>
    <option value="代金引換">代金引換</option>
    <option value="銀行振込">銀行振込</option>
</select>
</p>

<input type="submit" value="登録する">

</form>

<br>

<form action="login" method="get">
    <input type="submit" value="ログイン画面へ戻る">
</form>

</body>
</html>