<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="profilePhoto" value="/profile-photo/${userId}" />
<c:url var="editProfileAbout" value="/editprofileabout" />
<c:url var="saveInterest" value="/save-interest" />
<c:url var="deleteInterest" value="/delete-interest" />

<div class="col-md-10 col-md-offset-1">

	<div id="profile-photo-status"></div>

	<div id="interestDiv">
		<c:choose>
			<c:when test="${empty profile.interests}">
				<input name="tags" id="interestList"
					value="Add your interests here..." />
			</c:when>
			<c:otherwise>
				<input name="tags" id="interestList"
					value="<c:forEach var="interest" items="${profile.interests}">${interest.name},</c:forEach>" />
			</c:otherwise>
		</c:choose>

		<div class="profile-about">

			<div class="profile-image">
				<div>
					<img id="profilePhoto" alt="avatar" src="${profilePhoto}">
				</div>
				<div class="text-center">
					<c:if test="${ownProfile == true}">
						<a href="#" id="uploadLink">Upload Photo</a>
					</c:if>
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
				<c:if test="${ownProfile == true}">
					<a href="${editProfileAbout}">Edit</a>
				</c:if>
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

	function saveInterest(text) {
		editInterest(text, "${saveInterest}");
	}

	function deleteInterest(text) {
		editInterest(text, "${deleteInterest}");
	}

	function editInterest(text, actionUrl) {

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			jqXHR.setRequestHeader(header, token);
		});

		$.ajax({
			url : actionUrl,

			data : {
				'name' : text
			},

			type : 'POST',

			success : function() {
				// alert("OK");
			},

			error : function() {
				// alert("Not OK!");
			}
		});
	}

	$(document).ready(function() {

		$("#interestList").tagit({

			afterTagRemoved : function(event, ui) {
				deleteInterest(ui.tagLabel);
			},

			afterTagAdded : function(event, ui) {
				if (ui.duringInitialization != true) {
					saveInterest(ui.tagLabel);
				}
			},

			caseSensitive : false,
			allowSpaces : true,
			tagLimit : 10,
			readOnly: ${!ownProfile}
		});

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