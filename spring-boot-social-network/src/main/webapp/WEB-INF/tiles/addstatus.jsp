<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="card">
	<h4 class="card-header">Add a Status Update</h4>
	<div class="card-block">
		<form>
			<div class="form-group">
				<textarea name="text" rows="10" cols="50"></textarea>
			</div>
			<input type="submit" name="submit" value="Add Status" />
		</form>
	</div>
</div>