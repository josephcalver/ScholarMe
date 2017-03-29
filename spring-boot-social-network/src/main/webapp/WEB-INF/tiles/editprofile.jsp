<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="card">

		<h4 class="card-header">Edit Profile</h4>

		<div class="card-block">

			<form:form modelAttribute="profile" method="post">

				<div class="errors">
					<form:errors path="about" />
				</div>

				<div class="form-group row">
					<label for="institutionalAffiliation"
						class="col-sm-3 col-form-label">Institutional Affiliation:</label>
					<div class="col-sm-9">
						<form:input path="institutionalAffiliation" class="form-control"></form:input>
					</div>
				</div>

				<div class="form-group row">
					<label for="fieldOfStudy" class="col-sm-3 col-form-label">Field
						of Study:</label>
					<div class="col-sm-9">
						<form:select path="fieldOfStudy" class="form-control">
							<form:option value="" label="" />
							<form:option value="Accounting & Finance"
								label="Accounting & Finance" />
							<form:option value="Aeronautical & Manufacturing Engineering"
								label="Aeronautical & Manufacturing Engineering" />
							<form:option value="Agriculture & Forestry"
								label="Agriculture & Forestry" />
							<form:option value="American Studies" label="American Studies" />
							<form:option value="Anatomy & Physiology"
								label="Anatomy & Physiology" />
							<form:option value="Anthropology" label="Anthropology" />
							<form:option value="Archeology" label="Archeology" />
							<form:option value="Architecture" label="Architecture" />
							<form:option value="Art & Design" label="Art & Design" />
							<form:option value="Aural & Oral Sciences"
								label="Aural & Oral Sciences" />
							<form:option value="Business & Management Studies"
								label="Business & Management Studies" />
							<form:option value="Celtic Studies" label="Celtic Studies" />
							<form:option value="Chemical Engineering"
								label="Chemical Engineering" />
							<form:option value="Chemistry" label="Chemistry" />
							<form:option value="Civil Engineering" label="Civil Engineering" />
							<form:option value="Classics & Ancient History"
								label="Classics & Ancient History" />
							<form:option value="Communication & Media Studies"
								label="Communication & Media Studies" />
							<form:option value="Computer Science" label="Computer Science" />
							<form:option value="Criminology" label="Criminology" />
							<form:option value="Dentistry" label="Dentistry" />
							<form:option value="Drama, Dance & Cinematics"
								label="Drama, Dance & Cinematics" />
							<form:option value="East & South Asia Studies"
								label="East & South Asia Studies" />
							<form:option value="Economics" label="Economics" />
							<form:option value="Education" label="Education" />
							<form:option value="Electrical & Electronic Engineering"
								label="Electrical & Electronic Engineering" />
							<form:option value="English" label="English" />
							<form:option value="Fashion" label="Fashion" />
							<form:option value="Film Making" label="Film Making" />
							<form:option value="Food Science" label="Food Science" />
							<form:option value="Forensic Science" label="Forensic Science" />
							<form:option value="French" label="French" />
							<form:option value="Geography & Environmental Studies"
								label="Geography & Environmental Studies" />
							<form:option value="Geology" label="Geology" />
							<form:option value="General Engineering"
								label="General Engineering" />
							<form:option value="German" label="German" />
							<form:option value="History" label="History" />
							<form:option value="History of Art, Architecture & Design"
								label="History of Art, Architecture & Design" />
							<form:option value="Hospitality, Leisure, Recreation & Tourism"
								label="Hospitality, Leisure, Recreation & Tourism" />
							<form:option value="Hispanic Studies" label="Hispanic Studies" />
							<form:option value="Italian" label="Italian" />
							<form:option value="Land & Property Management"
								label="Land & Property Management" />
							<form:option value="Law" label="Law" />
							<form:option value="Librarianship & Information Management"
								label="Librarianship & Information Management" />
							<form:option value="Linguistics" label="Linguistics" />
							<form:option value="Marketing" label="Marketing" />
							<form:option value="Materials Technology"
								label="Materials Technology" />
							<form:option value="Medical Technology"
								label="Medical Technology" />
							<form:option value="Medicine" label="Medicine" />
							<form:option value="Middle Eastern & African Studies"
								label="Middle Eastern & African Studies" />
							<form:option value="Music" label="Music" />
							<form:option value="Natural Sciences" label="Natural Sciences" />
							<form:option value="Nursing" label="Nursing" />
							<form:option value="Occupational Therapy"
								label="Occupational Therapy" />
							<form:option value="Opthalmics" label="Opthalmics" />
							<form:option value="Pharmacology & Pharmacy"
								label="Pharmacology & Pharmacy" />
							<form:option value="Philosophy" label="Philosophy" />
							<form:option value="Physics & Astronomy"
								label="Physics & Astronomy" />
							<form:option value="Physiotherapy" label="Physiotherapy" />
							<form:option value="Politics" label="Politics" />
							<form:option value="Psychology" label="Psychology" />
							<form:option value="Robotics" label="Robotics" />
							<form:option value="Russian & East European Languages"
								label="Russian & East European Languages" />
							<form:option value="Social Policy" label="Social Policy" />
							<form:option value="Social Work" label="Social Work" />
							<form:option value="Sociology" label="Sociology" />
							<form:option value="Sports Science" label="Sports Science" />
							<form:option value="Theology & Religious Studies"
								label="Theology & Religious Studies" />
							<form:option value="Town & Country Planning and Landscape Design"
								label="Town & Country Planning and Landscape Design" />
							<form:option value="Veterinary Medicine"
								label="Veterinary Medicine" />
						</form:select>
					</div>
				</div>

				<div class="form-group row">
					<label for="publicEmail" class="col-sm-3 col-form-label">Public
						Email:</label>
					<div class="col-sm-9">
						<form:input path="publicEmail" class="form-control"></form:input>
					</div>
				</div>

				<div class="form-group row">
					<label for="about" class="col-sm-3 col-form-label">About
						Me:</label>
					<div class="col-sm-9">
						<form:textarea path="about" class="form-control" name="about"
							rows="10" cols="50"></form:textarea>
					</div>
				</div>

				<div class="form-group row container" id="profile-update-button">
					<input class="btn btn-secondary" role="button" type="submit"
						name="submit" value="Update Profile" />
				</div>

			</form:form>
		</div>
	</div>
</div>