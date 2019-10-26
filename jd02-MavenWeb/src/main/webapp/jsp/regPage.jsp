<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>${registration}</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="screen" >

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.registration.data" var="regData" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locLine.user.registration.enter" var="enter_your_details" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.fio" var="fio" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.position" var="position" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.specialization" var="specialization" />
<fmt:message bundle="${loc}" key="local.line.password" var="password" />
<fmt:message bundle="${loc}" key="local.line.login" var="login" />
<fmt:message bundle="${loc}" key="local.locLine.user.registration.registrtion.ok" var="registration" />
<fmt:message bundle="${loc}" key="local.locLine.user.registration.invalid" var="invalid_data" />

</head>
<body>
<form action="controller" method="post">
	<input type="hidden" name="command" value="registration" />
		<input type="hidden" name="local" value="ru" /> <input
			type="submit" value="${ru_button}">
	</form>
	<form action="controller" method="post">
	<input type="hidden" name="command" value="registration" />
		<input type="hidden" name="local" value="en" /> <input
			type="submit" value="${en_button}">
	</form>

	<form class="transparent2" action="../controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<div class="form-inner">
			<h3>${enter_your_details }</h3>
			
			<label for="name">${fio}</label>
			<input type="text"  name="name" value="" >
			
			<label for="login">${login}</label>
			<input type="text" name="login" value="" >
			
			<label for="password">${password}</label>
			 <input id="password" name="password" value="" >
			 
			<label for="position">${position}</label>
			<input type="text" name="position" value="" >
		
			<label for="specialization">${specialization}</label>
			<input type="text" name="specialization" value="" >
			
			<input	type="submit" value="${registration}" > 
			
			<label for="name">
			<c:if test="${requestScope.NullUser eq 'nullUserRequest'  }">
			${invalid_data}
			</c:if>
			</label>
	
		 </div>

		</form>
	
</body>
</html>