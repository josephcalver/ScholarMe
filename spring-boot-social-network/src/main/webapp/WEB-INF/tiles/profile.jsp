<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="phofilePhoto" value="/profile-photo" />
<c:url var="editProfileAbout" value="/editprofileabout" />

<div class="col-md-10 col-md-offset-1">

	<div class="profile-about">

		<div class="profile-image">

			<img alt="avatar" src="${profilePhoto}">

		</div>

		<div class="profile-text">

			<c:choose>
				<c:when test="${profile.about == null}"> 
				Click 'edit' to add information about yourself.
			</c:when>
				<c:otherwise>
					<c:out value="${profile.about}" />
				</c:otherwise>
			</c:choose>

		</div>

		<div class="profile-about-edit">

			<a href="${editProfileAbout}">Edit</a>

		</div>

		<p>&nbsp;</p>

		<c:url var="uploadPhotoLink" value="/upload-profile-photo" />
		<form action="${uploadPhotoLink}" method="post"
			enctype="multipart/form-data">

			Select photo: <input type="file" accept="image/*" name="file" /> <input
				type="submit" value="Upload" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>
	</div>

</div>