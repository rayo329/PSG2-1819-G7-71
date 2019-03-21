package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;


@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {
	
	@NotEmpty
	@Column(name = "name_cause")
	private String name;
	
	@NotEmpty
	@Column(name= "description_cause")
	private String description;
	
	@NotEmpty
	@Column(name = "budgetTarget")
	private Double budgetTarget;
	
	@NotEmpty
	@Column(name = "organization_name")
	private String organizationName;
	
	/*
	@ManyToOne
	@Column(name = "cause_id")
	private Cause cause;
	 */
	
	 /*
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause", fetch = FetchType.EAGER)
	@Column (name = "donations")
	private Set<Donation> donations; 
	*/ 
	
	public Cause() {
		
	}

	public String getNameCause() {
		return this.name;
	}
	
	public void setNameCause(String name) {
		this.name = name;
	}
	
	public String getDescriptionCause() {
		return this.description;
	}
	
	public void setDescriptionCause(String description) {
		this.description = description;
	}
	
	public Double getBudgetTarget() {
		return this.budgetTarget;
	}
	
	public void setBudgetTarget(Double target) {
		this.budgetTarget = target;
	}
	
	public String getOrganizationName() {
		return this.organizationName;
	}
	
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	/*
	public Cause getCause() {
		return this.get;
	}
	public void setCause(Cause cause) {
		this.cause = cause;
	}
	*/
	
	/*
	protected Set<Donation> getDonationsInternal(){
		if(this.donations == null) {
			this.donations = new HashSet<>();
		}
		return this.donations;
	}
	protected void setDonationsInternal (Set<Donation> donations) {
		this.donations = donations;
	}
	
	@XmlElement
	public List<Donation> getDonations(){
		List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
		PropertyComparator.sort(sortedDonations, new MutableSortDefinition("name",true,true));
		return Collections.unmodifiableList(sortedDonations);
	}
	*/
}
