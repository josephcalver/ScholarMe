<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="profilePhoto" value="/profile-photo" />
<c:url var="editProfileAbout" value="/editprofileabout" />

<div class="col-md-10 col-md-offset-1">

	<div id="profile-photo-status"></div>

	<div class="profile-about">

		<div class="profile-image">
			<div>
				<img id="profilePhoto" alt="avatar" src="${profilePhoto}">
			</div>
			<div class="text-center">
				<a href="#" id="uploadLink">Upload Photo</a>
			</div>
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

		<div>
			<c:url var="uploadPhotoLink" value="/upload-profile-photo" />
			<form action="${uploadPhotoLink}" method="post"
				enctype="multipart/form-data" id="photoUploadForm">

				Select photo: <input type="file" accept="image/*" name="file"
					id="photoFileInput" /> <input type="submit" value="Upload" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</div>

</div>

<script>
	function setUploadStatusText(text) {
		$("#profile-photo-status").text(text);
		window.setTimeout(function() {
			$("#profile-photo-status").text("");
		}, 2000);
	}

	function uploadSuccess(data) {
		$("#profilePhoto").attr("src", "${profilePhoto};t=" + new Date()); // Prevent caching

		$("#photoFileInput").val("");

		setUploadStatusText(data.message);
	}

	function uploadPhoto(event) {

		$.ajax({
			url : $(this).attr("action"),
			type : 'POST',
			data : new FormData(this),
			processData : false,
			contentType : false,
			success : uploadSuccess,
			error : function() {
				setUploadStatusText("Server unreachable");
			}
		});

		event.preventDefault();
	}

	$(document).ready(function() {

		$("#uploadLink").click(function(event) {
			event.preventDefault();
			$("#photoFileInput").trigger('click');
		});

		$("#photoFileInput").change(function() {
			$("#photoUploadForm").submit();
		});

		$("#photoUploadForm").on("submit", uploadPhoto);
	});
</script>