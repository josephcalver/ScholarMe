<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title><tiles:insertAttribute name="title" /></title>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<script src="https://use.fontawesome.com/10e566c058.js"></script>

<!-- Bootstrap -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"
	integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css"
	integrity="sha384-MIwDKRSSImVFAZCVLtU0LMDdON6KVCrZHyVQQj6e8wIEJkW4tvwqXrbMIya1vriY"
	crossorigin="anonymous">

<!-- Tag-It -->
<link href="${contextRoot}/css/jquery.tagit.css" rel="stylesheet">
<link href="${contextRoot}/css/tagit.ui-zendesk.css" rel="stylesheet">
<script src="${contextRoot}/js/jquery-ui.min.js"></script>
<script src="${contextRoot}/js/tag-it.min.js"></script>

<link href="${contextRoot}/css/main.css" rel="stylesheet">

</head>
<body>

	<nav class="navbar navbar-fixed-top navbar-light bg-faded">

	<button class="navbar-toggler hidden-sm-up pull-xs-right" type="button"
		data-toggle="collapse" data-target="#navbar" aria-controls="navbar"
		aria-expanded="false" aria-label="Toggle navigation">&#9776;</button>

	<a class="navbar-brand display-4" href="${contextRoot}/"><i
		class="fa fa-graduation-cap" aria-hidden="true"></i> ScholarMe</a>

	<div class="collapse navbar-toggleable-xs">

		<ul class="nav navbar-nav pull-xs-right">

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/addstatus">Add Status</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/viewstatus">View Statuses</a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/profile">My Profile</a></li>
				<li class="nav-item"><a class="nav-link"
					href="javascript:$('#logoutForm').submit()">Log Out</a></li>
			</sec:authorize>

			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item"><a class="btn btn-outline-secondary"
					href="${contextRoot}/preview">PREVIEW</a></li>
				<li class="nav-item"><a class="btn btn-outline-secondary"
					href="${contextRoot}/register">Create Account</a></li>
				<li class="nav-item"><a class="btn btn-outline-secondary"
					href="${contextRoot}/login">Log In</a></li>
			</sec:authorize>

		</ul>

	</div>

	</nav>

	<c:url var="logoutLink" value="/logout" />
	<form action="${logoutLink}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="container">

		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>

	</div>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"
		integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js"
		integrity="sha384-ux8v3A6CPtOTqOzMKiuo3d/DomGaaClxFYdCu2HPMBEkf6x2xiDyJ7gkXU0MWwaD"
		crossorigin="anonymous"></script>
</body>
</html>