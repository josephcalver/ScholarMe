<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jdc"%>

<c:url var="url" value="/viewstatus" />


<jdc:pagination page="${page}" url="${url}" size="10"/>


<c:forEach var="statusUpdate" items="${page.content}">

	<div class="card">
		<h4 class="card-header">
			Status Update added on:
			<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
				value="${statusUpdate.added}" />
		</h4>
		<div class="card-block">
			<c:out value="${statusUpdate.text}" />
		</div>
	</div>

	<br />

</c:forEach>