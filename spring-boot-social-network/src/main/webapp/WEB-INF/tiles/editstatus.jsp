<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<h4 class="card-header">Edit Status Update</h4>
	<div class="card-block">
		<form:form modelAttribute="statusUpdate">

			<form:hidden path="id" />
			<form:hidden path="added" />

			<div class="errors">
				<form:errors path="text" />
			</div>
			<div class="form-group">
				<form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>
			</div>
			<input type="submit" name="submit" value="Update Status" />
		</form:form>
	</div>
</div>

<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
<script>
	tinymce.init({
		selector : 'textarea',
		plugins : 'link'
	});
</script>