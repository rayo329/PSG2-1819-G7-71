package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {
	
	@NotEmpty
	@Column(name = "donation")
	private Double amountDonation;

	public Donation() {
		
	}
	
	public Double getAmountDonation() {
		return this.amountDonation;
	}
	public void setAmountDonation(Double amount) {
		this.amountDonation = amount;
	}
}
