package com.josephcalver.model.entity;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.owasp.html.PolicyFactory;

import com.josephcalver.model.dto.FileInfo;

@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(targetEntity = SiteUser.class)
	@JoinColumn(name = "user_id", nullable = false)
	private SiteUser user;

	@Column(name = "institutional_affiliation", length = 50)
	@Size(max = 50, message = "{editprofile.institution.size}")
	private String institutionalAffiliation;

	@Column(name = "field_of_study", length = 30)
	@Size(max = 30)
	private String fieldOfStudy;

	@Column(name = "public_email", unique = true)
	@Email(message = "{register.email.invalid}")
	@NotBlank(message = "{register.email.invalid}")
	private String publicEmail;

	@Column(name = "about", length = 5000)
	@Size(max = 5000, message = "{editprofile.about.size}")
	private String about;

	@Column(name = "photo_directory", length = 10)
	private String photoDirectory;

	@Column(name = "photo_name", length = 10)
	private String photoName;

	@Column(name = "photo_extension", length = 5)
	private String photoExtension;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "profile_interests", joinColumns = { @JoinColumn(name = "profile_id") }, inverseJoinColumns = {
			@JoinColumn(name = "interest_id") })
	@OrderColumn(name = "display_order")
	private Set<Interest> interests;

	public Profile() {

	}

	public Profile(SiteUser user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}

	public String getInstitutionalAffiliation() {
		return institutionalAffiliation;
	}

	public void setInstitutionalAffiliation(String institutionalAffiliation) {
		this.institutionalAffiliation = institutionalAffiliation;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public String getPublicEmail() {
		return publicEmail;
	}

	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPhotoDirectory() {
		return photoDirectory;
	}

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	// Create a profile object that is safe to display via JSP
	public void safeCopyFrom(Profile other) {
		
		if (other.institutionalAffiliation != null) {
			this.institutionalAffiliation = other.institutionalAffiliation;
		}
		
		if (other.fieldOfStudy != null) {
			this.fieldOfStudy = other.fieldOfStudy;
		}
		
		if (other.publicEmail != null) {
			this.publicEmail = other.publicEmail;
		}
		
		if (other.about != null) {
			this.about = other.about;
		}

		if (other.interests != null) {
			this.interests = other.interests;
		}
	}

	// Create a profile object that is suitable for saving
	public void safeMergeWith(Profile webProfile, PolicyFactory htmlPolicy) {
		
		if (webProfile.institutionalAffiliation != null) {
			this.institutionalAffiliation = htmlPolicy.sanitize(webProfile.institutionalAffiliation);
		}
		
		if (webProfile.fieldOfStudy != null) {
			this.fieldOfStudy = htmlPolicy.sanitize(webProfile.fieldOfStudy);
		}
		
		if (webProfile.publicEmail != null) {
			this.publicEmail = webProfile.publicEmail;
		}
		
		if (webProfile.about != null) {
			this.about = htmlPolicy.sanitize(webProfile.about);
		}
	}

	public void setPhotoDetails(FileInfo info) {
		photoDirectory = info.getSubDirectory();
		photoExtension = info.getExtension();
		photoName = info.getBaseName();
	}

	public Path getPhoto(String baseDirectory) {
		if (photoName == null) {
			return null;
		}

		return Paths.get(baseDirectory, photoDirectory, photoName + "." + photoExtension);
	}

	public void addInterest(Interest interest) {
		interests.add(interest);
	}

	public void removeInterest(String interestName) {
		interests.remove(new Interest(interestName));
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", institutionalAffiliation=" + institutionalAffiliation
				+ ", publicEmail=" + publicEmail + ", about=" + about + ", photoDirectory=" + photoDirectory
				+ ", photoName=" + photoName + ", photoExtension=" + photoExtension + ", interests=" + interests + "]";
	}

}
