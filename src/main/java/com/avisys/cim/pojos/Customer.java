package com.avisys.cim.pojos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	/*
	 * @Column(name = "MOBILE_NUMBER", unique = true, nullable = false) private
	 * String mobileNumber;
	 */

	@ElementCollection(fetch = FetchType.EAGER)

	@CollectionTable(name = "MOBILE_NUMBERS", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))

	@Column(name = "MOBILE_NUMBER")
	private Set<String> mobileNumber = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Set<String> mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/*
	 * public String getMobileNumber() { return mobileNumber; }
	 * 
	 * public void setMobileNumber(String mobileNumber) { this.mobileNumber =
	 * mobileNumber; }
	 */

}
