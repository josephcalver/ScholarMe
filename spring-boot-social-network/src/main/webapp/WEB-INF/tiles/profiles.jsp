<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jdc"%>


<c:url var="url" value="/profiles" />

<jdc:pagination page="${page}" url="${url}" size="5" />


<c:forEach var="profile" items="${page.content}">

	<c:url var="profilePhoto" value="/profile-photo/${profile.userId}" />
	<c:url var="profileLink" value="/profile/${profile.userId}" />

	<div class="frame">

		<div class="results-image">
			<a href="${profileLink}"><img id="profilePhoto" alt="avatar"
				src="${profilePhoto}"></a>
		</div>

		<div class="results-text">
			<p>
				Name: <a class="btn btn-secondary" href="${profileLink}">${profile.firstName}
					${profile.lastName} </a>
			</p>
			<p>
				Interests:
				<c:forEach var="interest" items="${profile.interests}">
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