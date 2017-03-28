<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jdc"%>


<c:url var="searchUrl" value="/search?s=${s}" />

<jdc:pagination page="${page}" url="${searchUrl}" size="5" />


<div class="container">

	<div class="col-sm-12">

		<h4 id="results-page-title">
			Search results for: <i>${s}</i>
		</h4>
		<br /> <br />

		<c:if test="${empty page.content}">
			<h4>No results found</h4>
		</c:if>

		<c:forEach var="result" items="${page.content}">

			<c:url var="profilePhoto" value="/profile-photo/${result.userId}" />
			<c:url var="profileLink" value="/profile/${result.userId}" />

			<div class="frame">
				<div class="results-image">
					<a href="${profileLink}"><img id="profilePhoto" alt="avatar"
						src="${profilePhoto}"></a>
				</div>

				<div class="results-text">
					<p>
						Name: <a class="btn btn-secondary" href="${profileLink}">${result.firstName}
							${result.lastName} </a>
					</p>
					<p>
						Interests:
						<c:forEach var="interest" items="${result.interests}">
							<c:url var="interestLink" value="/search?s=${interest.name}" />
							<a class="btn btn-secondary" href="${interestLink}"><c:out
									value="${interest.name}" /></a>
						</c:forEach>
					</p>
				</div>
			</div>
			<br />
			<br />
		</c:forEach>

	</div>

</div>
