<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="search" value="/search" />

<div class="row">

	<div class="col-md-6 col-md-offset-3">

		<div class="homepage-status">${statusUpdate.text}</div>

	</div>

</div>
<br />
<div class="row">

	<div class="col-md-8 col-md-offset-2">

		<form action="${search}" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="input-group input-group-lg">
				<input type="text" class="form-control" name="s"
					placeholder="Search interests..." /> <span class="input-group-btn">
					<button id="search-button" class="btn btn-primary" type="submit">MatchMe!</button>
				</span>
			</div>
		</form>

	</div>

</div>