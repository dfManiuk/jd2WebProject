<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#centerLayer {
	position: absolute; /* Абсолютное позиционирование */
	width: 350px; /* Ширина слоя в пикселах */
	height: 380px; /* Высота слоя в пикселах */
	left: 50%; /* Положение слоя от левого края */
	top: 50%; /* Положение слоя от верхнего края */
	margin-left: -150px; /* Отступ слева */
	margin-top: -150px; /* Отступ сверху */
	background: #00BFFF; /* Цвет фона */
	border: solid 1px black; /* Параметры рамки вокруг */
	padding: 10px; /* Поля вокруг текста */
	overflow: auto; /* Добавление полосы прокрутки */
}
</style>
</head>
<body>
<div id="centerLayer">

<h1>Spring + Hibernate version</h1>
	
		<h1>Вход в систему</h1>
		<form action="check-user" method="get">
			<h5>Введите имя пользователя</h5>
			<input type="text" name="login" value="" />
			<h5>Введите пароль</h5>
			<input type="text" name="password" value="" /> <br >  
			<br >
			<input type="submit" value="Ввод" /> <br />
		</form>
		<form action="registr-user" method="get">
			<h5>Регистрация пользователя</h5>
			<input type="submit" value="Ввод" /> <br />
		</form>
</div>
</body>
</html>