<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="previewUrl" value="/preview"/>

<div class="container">
	<br />
	<h1 class="page-heading">About ScholarMe</h1>
	<br /> <br />

	<div class="col-sm-12 about-text">

		<p>Volunteering for a good cause can be a great use of your
			personal time.</p>
		<br />
		<p>
			<i>Arranging</i> to volunteer, however, is often time-consuming and
			needlessly complicated.
		</p>
		<br />

		<p>Booking a single volunteering assignment can involve multiple
			phone calls, text messages or emails -- all of which eat up valuable
			time that would be better spent... volunteering!</p>
		<br />
		<p>Olderly takes the hassle out of arranging to volunteer,
			eliminating unnecessary calls and messages, allowing you to focus on
			the task at hand.</p>
		<br />
		<p>To book an assignment, users simply:</p>
		<br />
	</div>
	<div class="col-sm-12 offset-sm-4 about-bullets">
		<ul>
			<li>Log in</li>
			<li>Browse available assignments</li>
			<li>Click "Volunteer!"</li>
		</ul>
	</div>
	<div class="col-sm-12 about-text">
		<br />
		<p>Olderly also benefits the volunteer coordinator. Admin users
			are able to post details of all assignments in one place, removing
			the need to send the same information to all volunteers individually.</p>
		<br />
		<p>To preview the app, please follow the link below:</p>
		<br /> <a class="btn btn-secondary btn-lg" role="button"
			href="${previewUrl}">PREVIEW</a>
	</div>
</div>