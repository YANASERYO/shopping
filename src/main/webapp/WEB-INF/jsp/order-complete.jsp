<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文完了</title>
</head>
<body>

<h2>注文が完了しました</h2>

<p>ご注文ありがとうございました。</p>

<p>注文番号：${orderInfo.shoppingId}</p>

<form action="menu" method="get">
    <input type="submit" value="メニューへ戻る">
</form>

</body>
</html>