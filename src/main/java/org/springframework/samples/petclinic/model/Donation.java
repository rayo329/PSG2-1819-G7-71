package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {
	
	@ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

	@NotEmpty
	@Column(name = "amount_donation")
	private Double amountDonation;

	public Donation() {
		
	}
	
	public Double getAmountDonation() {
		return this.amountDonation;
	}
	public void setAmountDonation(Double amount) {
		this.amountDonation = amount;
	}

	public Cause getCause() {
		return this.cause;
	}
	public void setCause(Cause cause) {
		this.cause = cause;
	}
}
