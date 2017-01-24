<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="loginUrl" value="/login" />

<div class="errors">
	<form:errors path="email" />
	<form:errors path="password" />
</div>

<div class="card">
	<h4 class="card-header">User Login</h4>
	<div class="card-block">
		<form:form modelAttribute="siteUser" method="post">

			<div class="form-group-row">
				<form:input class="form-control" type="text" path="email"
					placeholder="Email" />
			</div>
			<div class="form-group-row">
				<form:input class="form-control" type="password" path="password"
					placeholder="Password" />
			</div>
			<div class="form-group-row">
				<input class="form-control" type="password" name="repeatPassword"
					placeholder="Repeat Password" />
			</div>
			<div class="form-group-row">
				<button class="btn btn-primary form-control" type="submit">Register</button>
			</div>
		</form:form>

	</div>
</div>