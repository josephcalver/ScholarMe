<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<h4 class="card-header">Edit Your 'About' Text</h4>
	<div class="card-block">
		<form:form modelAttribute="profile">

			<div class="errors">
				<form:errors path="about" />
			</div>

			<div class="form-group">
				<form:textarea path="about" name="about" rows="10" cols="50"></form:textarea>
			</div>
			<input type="submit" name="submit" value="Update Profile" />

		</form:form>
	</div>
</div>