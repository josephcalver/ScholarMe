<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="loginUrl" value="/login" />

<c:if test="${param.error != null}">
	<div class="login-error">Incorrect username or password</div>
</c:if>

<div class="card">

	<h4 class="card-header">User Login</h4>

	<div class="card-block">

		<form class="form" action="${loginUrl}" method="post">

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="form-group row">
				<input class="form-control" type="text" name="username"
					placeholder="Username" />
			</div>

			<div class="form-group row">
				<input class="form-control" type="password" name="password"
					placeholder="Password" />
			</div>

			<div class="form-group row">
				<button class="btn btn-primary form-control" type="submit">Log
					In</button>
			</div>

		</form>

	</div>

</div>