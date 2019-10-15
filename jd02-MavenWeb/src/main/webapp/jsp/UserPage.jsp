<%@page import="java.util.ArrayList"%>
<%@page import="by.htp.entity.User"%>
<%@page import="by.htp.entity.Patient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String name = "User";
User user = (User) request.getAttribute(name);
System.out.print(user.toString());
%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.title" var="title1" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.fio" var="fio" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.position" var="position" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.specialization" var="specialization" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.change" var="change" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.fiksing" var="patients" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.find" var="find" />
<fmt:message bundle="${loc}" key="local.locbutton.find" var="button_find" />
<fmt:message bundle="${loc}" key="local.locLine.patient.table.name" var="name" />
<fmt:message bundle="${loc}" key="local.locbutton.exit" var="exit" />
<fmt:message bundle="${loc}" key="local.locbutton.user.page.title" var="title" />
<fmt:message bundle="${loc}" key="local.locbutton.user.page.home" var="home" />
<fmt:message bundle="${loc}" key="local.locbutton.user.page.shtaff" var="staff" />
<fmt:message bundle="${loc}" key="local.line.name" var="simple_name" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.personal.change" var="personal_change" />
<fmt:message bundle="${loc}" key="local.locbutton.user.page.personal.assing" var="assing" />
<fmt:message bundle="${loc}" key="local.locbutton.ok" var="ok" />
<fmt:message bundle="${loc}" key="local.locLine.patient.date" var="birth_date" />
<fmt:message bundle="${loc}" key="local.locLine.patient.location" var="location" />
<fmt:message bundle="${loc}" key="local.locLine.patient.telephone" var="telephone" />
<fmt:message bundle="${loc}" key="local.locLine.patient.choose" var="choose" />
<fmt:message bundle="${loc}" key="local.locLine.patient.appointments" var="appointments" />
<fmt:message bundle="${loc}" key="local.locLine.patient.leave" var="leave" />
<fmt:message bundle="${loc}" key="local.locLine.patient.add" var="add" />
<fmt:message bundle="${loc}" key="local.locLine.patient.pages" var="pages" />
<fmt:message bundle="${loc}" key="local.locLine.patient.withdraw" var="withdraw" />
<fmt:message bundle="${loc}" key="local.locLine.patient.perform" var="perform" />
<fmt:message bundle="${loc}" key="local.locLine.patient.withdraw.simple" var="withdraw_simple" />
<fmt:message bundle="${loc}" key="local.locLine.yes" var="yes" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.patient.simple" var="patient_simple" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<style>
   .b1 {
    border: 1px solid green;
    margin: auto; 
   }
   td {
    text-align: center; 
   }
  </style>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"
	media="screen" />
</head>
<body>
	<form action="controller" method="post">
		<input type="hidden" name="language" value="change" /> <input
			type="hidden" name="local" value="ru" /> <input type="submit"
			value="${ru_button}">
	</form>
	<form action="controller" method="post">
		<input type="hidden" name="language" value="change" /> <input
			type="hidden" name="local" value="en" /> <input type="submit"
			value="${en_button}">
	</form>

	<div id="main_container">
		<div class="header">
			<div id="logo">
				<a href="index.jsp"><img src="images/logo.png" alt="" title=""
					width="162" height="54" border="0" /></a>
			</div>

			<div class="right_header">
				<div id="menu">
					<ul>
						<li><a class="current" href="#" title="">${home}</a></li>
						<li>
						<form id="formShowAllUsers" action="controller" method="post">
								<a href="javascript:;" onclick="parentNode.submit();">${staff}</a>
								<input type="hidden" name="command" value="show_all_users" /> 
							</form>
						</li>
						<li><form id="formShowAllUsers" action="controller" method="post">
								<a href="javascript:;" onclick="parentNode.submit();">${patient_simple}</a>
								<input type="hidden" name="command" value="show_all_patients" /> 
							</form></li>
						<li><a href="Main.jsp" title="">${exit}</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="middle_box">
		<table class="b1">
			<caption>${title1}</caption>
			<tr>
				<td><label for="Имя"> ${fio} </label></td>
				<td><label> <%  out.print(user.getName()); %>
				</label></td>
			</tr>
			<tr>
				<td><label for="Должность"> ${position} </label></td>
				<td><label> <% out.print(user.getPosition()); %>
				</label></td>
			</tr>
			<tr>
				<td><label for="Cпециализация"> ${specialization} </label></td>
				<td><label> <% out.print(user.getSpecialization()); %>
				</label></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><a
					href="jsp/editProfile.jsp"> ${change} </a></td>
			</tr>
			<tr>
				<c:if test="${requestScope.User.position eq 'заведующий' }">
					<td colspan="2" style="text-align: center">
						<div id="patients_box">
							<form action="controller" method="post">
								<input type="hidden" name="command"
									value="user_changer"> <input type="submit" value="${personal_change}">
							</form>
						</div>
						</td>
				</c:if>
			</tr>			
			
			<jsp:useBean id="User" class="by.htp.entity.User" scope="request" />
			<jsp:getProperty property="position" name="User" />
			<tr>
				<td colspan="2" style="text-align: center">
					<div id="patients_box">
						<form class="transparent" action="controller" method="post">
							<input type="hidden" name="command"
								value="find_patient_from_user" /> <input type="submit"
								value="${patients}">
						</form>
					</div>
			</tr>
			<tr>
				<c:if test="${requestScope.User.position eq 'заведующий' }">
					<td colspan="2" style="text-align: center">
						<div id="patients_box">
							<form action="controller" method="post">
								 <input type="hidden" name="command"
									value="fixing"> <input type="submit" value="${assing}">
							</form>
						</div>
						</td>

				</c:if>

			</tr>
			<tr>
				<c:if test="${requestScope.User.position eq 'медсестра' }">
					<td colspan="2" style="text-align: center">
						<div id="patients_box">
							<form action="controller" method="post">
								${add} <input type="hidden" name="command"
									value="add_new_patient"> <input type="submit"
									value="${ok}">
							</form>
						</div>
				</c:if>
			</tr>
			<tr>

				<td colspan="2" style="text-align: center">
					<div id="patients_box">
						<form action="controller" method="post">
							${find} <input type="hidden" name="command"
								value="find_patient"> <input name="patientData" name="">
							<input type="submit" value="${button_find}">
						</form>
					</div>
			</tr>
		</table>
		<table border="1">
			<thead>
			</thead>
			<c:if test="${Patients != null}">
				<tr>
					<th>${name}</th>
					<th>${birth_date}</th>
					<th>${location}</th>
					<th>${telephone}</th>
					<th>${choose}</th>
					<th>${appointments}</th>
				</tr>
			</c:if>
			<c:forEach items="${Patients}" var="pt">

				<tr>
					<td>${pt.name}</td>
					<td>${pt.data}</td>
					<td>${pt.adress}</td>
					<td>${pt.telephone}</td>
					<td>
						<form action="controller" method="post">
							<select name="command">
								<option value="medication">${appointments}</option>
								<!-- <option value="check_nurs" >Закрепить медсестру</option> -->
								<c:if
									test="${ (requestScope.User.position eq 'врач') || (requestScope.User.position eq 'заведующий')  }">
									<option value="live">${leave}</option>
								</c:if>
							</select> <input type="hidden" name="name" value="${pt.passport}">
							<input type="submit" value="${perform}">
						</form>
					</td>
					<td>	
									<c:forEach items="${requestScope.ListInteger}" var="intlist"  >
									<c:if test="${pt.idPatient == intlist}">
									 <c:out value="${yes}"/>
									<%-- <c:out value="${pt.idPatient}"/>
									<c:out value="${intlist}"/> --%>
									</c:if>
									</c:forEach>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${Count != null}">
				${pages}  <c:forEach var="counter" begin="0"	end="${Count}">
						<c:if test="${counter != PageUsed }">
							<form action="controller" method="post">
								<input type="hidden" name="command"
									value="find_patient_from_user" /> <input type="hidden"
									name="start" value="${counter}"> <input type="submit"
									value="${counter + 1}">
							</form>
						</c:if>
						<c:if test="${counter == PageUsed }">
							<font color=red> <c:out value="${counter + 1}" />
							</font>
						</c:if>
					</c:forEach>

				<th>${withdraw}
					<form class="transparent" action="controller" method="post">
						<input type="hidden" name="command" value="find_patient_from_user" />
						<input name="delimeter" name=""> <input type="submit"
							value="${withdraw_simple}">
					</form>
				</th>

			</c:if>
		</table>
	</div>
	<table>

	</table>
</body>
</html>