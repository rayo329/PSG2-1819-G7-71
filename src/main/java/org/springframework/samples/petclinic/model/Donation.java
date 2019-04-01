package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {
	
	@ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

	@NotEmpty
	@Column(name = "amount_donation")
	private Double amountDonation;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "donation_date")
	private Date donationDate;
	
	@NotBlank
	private String client;

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

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	
}
