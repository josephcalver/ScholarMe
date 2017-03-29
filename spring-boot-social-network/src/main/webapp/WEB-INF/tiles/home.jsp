<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url var="search" value="/search" />
<c:url var="searchByField" value="/search-by-field" />

<sec:authorize var="userLoggedIn" access="isAuthenticated()" />


<div class="container">

	<c:choose>

		<c:when test="${userLoggedIn}">

			<div class="col-xs-10 offset-xs-1">

				<div class="home-main">
					<i class="fa fa-graduation-cap fa-2x" aria-hidden="true"></i>
					<div class="home-main-text display-4">ScholarMe</div>
				</div>

				<div class="home-search-box">
					<form action="${search}" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="input-group input-group-lg">
							<input type="text" class="form-control" name="s"
								placeholder="Search users by research interest..." /> <span
								class="input-group-btn">
								<button id="search-button" class="btn btn-outline-secondary"
									type="submit">Search</button>
							</span>
						</div>
					</form>
				</div>

				<div class="home-search-box">
					<form action="${searchByField}" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="input-group input-group-lg">
							<select class="form-control" name="s">
								<option value="" label="Browse users by field of study..." />
								<option value="Accounting & Finance"
									label="Accounting & Finance" />
								<option value="Aeronautical & Manufacturing Engineering"
									label="Aeronautical & Manufacturing Engineering" />
								<option value="Agriculture & Forestry"
									label="Agriculture & Forestry" />
								<option value="American Studies" label="American Studies" />
								<option value="Anatomy & Physiology"
									label="Anatomy & Physiology" />
								<option value="Anthropology" label="Anthropology" />
								<option value="Archeology" label="Archeology" />
								<option value="Architecture" label="Architecture" />
								<option value="Art & Design" label="Art & Design" />
								<option value="Aural & Oral Sciences"
									label="Aural & Oral Sciences" />
								<option value="Business & Management Studies"
									label="Business & Management Studies" />
								<option value="Celtic Studies" label="Celtic Studies" />
								<option value="Chemical Engineering"
									label="Chemical Engineering" />
								<option value="Chemistry" label="Chemistry" />
								<option value="Civil Engineering" label="Civil Engineering" />
								<option value="Classics & Ancient History"
									label="Classics & Ancient History" />
								<option value="Communication & Media Studies"
									label="Communication & Media Studies" />
								<option value="Computer Science" label="Computer Science" />
								<option value="Criminology" label="Criminology" />
								<option value="Dentistry" label="Dentistry" />
								<option value="Drama, Dance & Cinematics"
									label="Drama, Dance & Cinematics" />
								<option value="East & South Asia Studies"
									label="East & South Asia Studies" />
								<option value="Economics" label="Economics" />
								<option value="Education" label="Education" />
								<option value="Electrical & Electronic Engineering"
									label="Electrical & Electronic Engineering" />
								<option value="English" label="English" />
								<option value="Fashion" label="Fashion" />
								<option value="Film Making" label="Film Making" />
								<option value="Food Science" label="Food Science" />
								<option value="Forensic Science" label="Forensic Science" />
								<option value="French" label="French" />
								<option value="Geography & Environmental Studies"
									label="Geography & Environmental Studies" />
								<option value="Geology" label="Geology" />
								<option value="General Engineering" label="General Engineering" />
								<option value="German" label="German" />
								<option value="History" label="History" />
								<option value="History of Art, Architecture & Design"
									label="History of Art, Architecture & Design" />
								<option value="Hospitality, Leisure, Recreation & Tourism"
									label="Hospitality, Leisure, Recreation & Tourism" />
								<option value="Hispanic Studies" label="Hispanic Studies" />
								<option value="Italian" label="Italian" />
								<option value="Land & Property Management"
									label="Land & Property Management" />
								<option value="Law" label="Law" />
								<option value="Librarianship & Information Management"
									label="Librarianship & Information Management" />
								<option value="Linguistics" label="Linguistics" />
								<option value="Marketing" label="Marketing" />
								<option value="Materials Technology"
									label="Materials Technology" />
								<option value="Medical Technology" label="Medical Technology" />
								<option value="Medicine" label="Medicine" />
								<option value="Middle Eastern & African Studies"
									label="Middle Eastern & African Studies" />
								<option value="Music" label="Music" />
								<option value="Natural Sciences" label="Natural Sciences" />
								<option value="Nursing" label="Nursing" />
								<option value="Occupational Therapy"
									label="Occupational Therapy" />
								<option value="Opthalmics" label="Opthalmics" />
								<option value="Pharmacology & Pharmacy"
									label="Pharmacology & Pharmacy" />
								<option value="Philosophy" label="Philosophy" />
								<option value="Physics & Astronomy" label="Physics & Astronomy" />
								<option value="Physiotherapy" label="Physiotherapy" />
								<option value="Politics" label="Politics" />
								<option value="Psychology" label="Psychology" />
								<option value="Robotics" label="Robotics" />
								<option value="Russian & East European Languages"
									label="Russian & East European Languages" />
								<option value="Social Policy" label="Social Policy" />
								<option value="Social Work" label="Social Work" />
								<option value="Sociology" label="Sociology" />
								<option value="Sports Science" label="Sports Science" />
								<option value="Theology & Religious Studies"
									label="Theology & Religious Studies" />
								<option value="Town & Country Planning and Landscape Design"
									label="Town & Country Planning and Landscape Design" />
								<option value="Veterinary Medicine" label="Veterinary Medicine" />
							</select> <span class="input-group-btn">
								<button id="search-button" class="btn btn-outline-secondary"
									type="submit">Search</button>
							</span>
						</div>
					</form>
				</div>
			</div>

		</c:when>

		<c:otherwise>

			<div class="col-xs-10 offset-xs-1">
				<div class="home-main">
					<i class="fa fa-graduation-cap fa-5x" aria-hidden="true"></i>
					<div class="home-main-text display-1">ScholarMe</div>
					<h4 class="home-main-subtitle">Discover . Collaborate . Share</h4>
					<br /> <br /> <a class="btn btn-secondary btn-lg" role="button"
						href="${contextRoot}/about">Learn more</a>
				</div>
			</div>

		</c:otherwise>

	</c:choose>

</div>
