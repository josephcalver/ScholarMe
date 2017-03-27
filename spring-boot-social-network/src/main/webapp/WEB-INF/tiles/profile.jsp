<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="profilePhoto" value="/profile-photo/${userId}" />
<c:url var="editProfile" value="/edit-profile" />
<c:url var="saveInterest" value="/save-interest" />
<c:url var="deleteInterest" value="/delete-interest" />

<div class="container">


	<div class="col-sm-12 offset-sm-0">

		<div class="frame">

			<div class="container">

				<div class="profile-header">

					<div id="profile-photo-status"></div>


					<div id="profile-photo">
						<img id="profilePhoto" alt="avatar" src="${profilePhoto}">
					</div>
					<div>
						<c:if test="${ownProfile == true}">
							<a href="#" id="uploadLink">Upload Photo</a>
						</c:if>
					</div>
					<div>
						<h1 class="display-4">${user.firstName}&nbsp;${user.lastName}</h1>
						<p>
							<b>${profile.institutionalAffiliation}</b>
						</p>
					</div>
				</div>

				<br />

				<div class="profile-text">

					<dl class="row col-sm-10 offset-sm-1">

						<dt class="col-sm-4">Field of Study:</dt>
						<dd class="col-sm-6">
							<c:out value="${profile.fieldOfStudy}" />
						</dd>
						<dt class="col-sm-4">Contact:</dt>
						<dd class="col-sm-6">
							<c:out value="${profile.publicEmail}" />
						</dd>

						<dt class="col-sm-4">About ${user.firstName}:</dt>
						<dd class="col-sm-6">
							<c:choose>
								<c:when test="${profile.about == null}">
									<p>Click 'edit' to add information about yourself.</p>
								</c:when>
								<c:otherwise>
									<p>
										<c:out value="${profile.about}" />
									</p>
								</c:otherwise>
							</c:choose>
						</dd>

						<dt class="col-sm-4">Research Interests:</dt>
						<dd class="col-sm-6">
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
						</dd>
					</dl>
				</div>

				<div class="row"></div>
				<div id="profile-edit-button">
					<c:if test="${ownProfile == true}">
						<a class="btn btn-secondary" role="button" href="${editProfile}">Edit</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- #photoUploadForm not displayed -->
<c:url var="uploadPhotoLink" value="/upload-profile-photo" />
<form action="${uploadPhotoLink}" method="post"
	enctype="multipart/form-data" id="photoUploadForm">

	Select photo: <input type="file" accept="image/*" name="file"
		id="photoFileInput" /> <input type="submit" value="Upload" /> <input
		type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<!-- End #photoUpload Form -->


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