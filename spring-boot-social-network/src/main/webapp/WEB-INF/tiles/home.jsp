<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:url var="search" value="/search" />


<div class="container-fluid">

	<sec:authorize var="userLoggedIn"
		access="hasAnyRole('USER', 'ADMIN', 'GUEST_USER')" />

	<c:choose>

		<c:when test="${userLoggedIn}">

			<div class="row">

				<div class="col-md-6 col-md-offset-3">

					<div class="homepage-status">${statusUpdate.text}</div>
				</div>

			</div>
			<br />
			<div class="row">

				<div class="col-xs-8 col-xs-offset-2">

					<form action="${search}" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="input-group input-group-lg">
							<input type="text" class="form-control" name="s"
								placeholder="Search interests..." /> <span
								class="input-group-btn">
								<button id="search-button" class="btn btn-outline-secondary" type="submit">Search</button>
							</span>
						</div>
					</form>

				</div>

			</div>

		</c:when>

		<c:otherwise>


			<div class="col-xs-8 offset-xs-2">
				<div class="home-main">
					<i class="fa fa-graduation-cap fa-5x" aria-hidden="true"></i>
					<div class="home-main-text display-1">ScholarMe</div>
					<h4 class="home-main-subtitle">Discover . Collaborate . Share</h4>
					<br />
					<br />
					<a class="btn btn-secondary btn-lg" role="button"
						href="${contextRoot}/about">Learn more</a>
				</div>

				<div>
					
				</div>
			</div>


			<!-- <div class="jumbotron jumbotron-fluid">
			<div class="container-fluid">
				<div class="jumbotron-text">
					<p>
					<h1 class="display-1">Olderly</h1>
					</p>
					<p>
					<h1 class="display-4">Volunteering, made simple.</h1>
					</p>
				</div>
			</div>
		</div> -->

		</c:otherwise>

	</c:choose>

</div>
