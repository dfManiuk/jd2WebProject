<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${namePage}</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" >

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.enter" var="enter_message" />
<fmt:message bundle="${loc}" key="local.locbutton.name.registration" var="registr_message" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.message.wrongLogination" var="loginationMessage" />
<fmt:message bundle="${loc}" key="local.message.login" var="LoginMessage" />
<fmt:message bundle="${loc}" key="local.line.name" var="LoginName" />
<fmt:message bundle="${loc}" key="local.line.password" var="LoginPassword" />
<fmt:message bundle="${loc}" key="local.line.first.page.name" var="namePage" />
</head>
<body>
<h2> РАБОЧАЯ ВЕРСИЯ</h2>
	<form action="controller" method="post" >
	<input type="hidden" name="command" value="logination" />
		<input type="hidden" name="local" value="ru" /> <input
			type="submit" value="${ru_button}">
	</form>
	<form action="controller" method="post">
	<input type="hidden" name="command" value="logination" />
		<input type="hidden" name="local" value="en" /> <input
			type="submit" value="${en_button}">
	</form>

<form class="transparent" action="controller" method="post">
<input type="hidden" name="command" value="logination" />
   <div class="form-inner">
    <h3>${LoginMessage}</h3>
     <label for="username">${LoginName}</label>
     <input type="text" name="login" value="">
     <label for="password">${LoginPassword}</label>
     <input type="text" name="password" value="">
     <input type="submit" value="${enter_message}" >
     <input type="submit" value="${registr_message}" formaction= "jsp/regPage.jsp">
     <h4>
     <c:if test="${requestScope.NullUser eq 'nullUserRequest'}">
     <c:out value ="${loginationMessage}" />
   	</c:if>
     </h4>
  </div>
</form>
</body>
</html>