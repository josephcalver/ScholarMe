<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="previewUrl" value="/preview" />

<div class="container">
	<br />
	<h1 class="page-heading">About ScholarMe</h1>
	<br /> <br />

	<div class="col-sm-12 about-text">
		<p>Finding likeminded academics with whom to collaborate or share
			your work can be a time-consuming and often fruitless task.</p>
		<br />
		<p>Alleviating the need to trawl university department websites
			one-by-one, ScholarMe enables users to:</p>
		<br />
	</div>
	<div class="col-sm-12 offset-sm-3 about-bullets">
		<ul>
			<li>Log in</li>
			<li>Search users by research topic</li>
			<li>Search users by field of research</li>
			<li>Browse all users</li>
			<li>Make contact via direct messaging [Under Construction]</li>
		</ul>
	</div>
	<div class="col-sm-12 about-text">
		<br />
		<p>To preview the app, please follow the link below:</p>
		<br /> <a class="btn btn-secondary btn-lg" role="button"
			href="${previewUrl}">PREVIEW</a>
	</div>
</div>