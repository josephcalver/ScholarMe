<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jdc"%>

<c:url var="url" value="/viewstatus" />


<jdc:pagination page="${page}" url="${url}" size="10" />


<c:forEach var="statusUpdate" items="${page.content}">

	<c:url var="editLink" value="/editstatus?id=${statusUpdate.id}" />
	<c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}" />

	<div class="card">
		<h4 class="card-header">
			Status Update added on:
			<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
				value="${statusUpdate.added}" />
		</h4>
		<div class="card-block">
			<div>${statusUpdate.text}</div>
			<div class="edit-links pull-right">
				<a href="${editLink}">Edit</a> | <a
					onclick="return confirm('Are you sure you want to delete this status update?');"
					href="${deleteLink}">Delete</a>
			</div>
		</div>
	</div>

	<br />

</c:forEach>