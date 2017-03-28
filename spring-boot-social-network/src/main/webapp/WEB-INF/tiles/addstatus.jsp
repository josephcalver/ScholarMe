<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="card">
	<h4 class="card-header">Add a Status Update</h4>
	<div class="card-block">
		<form:form modelAttribute="statusUpdate">
			<div class="errors">
				<form:errors path="text" />
			</div>
			<div class="form-group">
				<form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>
			</div>
			<input type="submit" name="submit" value="Add Status" />
		</form:form>
	</div>
</div>
<br />
<div class="card">
	<h4 class="card-header">
		Status Update added on:
		<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
			value="${latestStatusUpdate.added}" />
	</h4>
	<div class="card-block">${latestStatusUpdate.text}</div>
</div>