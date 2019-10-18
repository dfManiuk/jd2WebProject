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
	
		<form action='<spring:url value="/loginAction"/>' method="post">
    <table>
      <tr>
        <td>Username</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td><button type="submit">Login</button></td>
      </tr>
    </table>
  </form>
  <br/>
  </div>
</body>
</html>