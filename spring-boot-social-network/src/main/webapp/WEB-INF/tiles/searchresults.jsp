<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${empty results}">
	No results
</c:if>

<c:forEach var="result" items="${results}">
	<c:url var="profilePhoto" value="/profile-photo/${result.userId}" />
	<c:url var="profileLink" value="/profile/${result.userId}" />
	<div>
		<a href="${profileLink}"><img id="profilePhoto" alt="avatar"
			src="${profilePhoto}"></a>
	</div>
	<p>
		<a href="${profileLink}"> ${result.userId} ${result.firstName}
			${result.lastName} </a>
	</p>
	<p>
		<c:forEach var="interest" items="${result.interests}">
			<c:url var="interestLink" value="/search?s=${interest.name}" />
			<a href="${interestLink}"><c:out value="${interest.name}" /></a>
		</c:forEach>
	</p>
</c:forEach>
