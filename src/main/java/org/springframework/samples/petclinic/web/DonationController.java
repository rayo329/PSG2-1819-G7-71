package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/donations")
public class DonationController {

	private static final String VIEWS_DONATION_CREATE_FORM = "donations/createDonationForm";
	private ClinicService clinicService;
	
	
	@Autowired
	public DonationController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String showAll(Map<String,Object> model){
		
		Collection<Donation> donations = this.clinicService.findAllDonations();
		model.put("donations", donations);
		return "donations/donationList";
	}

	@RequestMapping(value = "/donations/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		Donation donation = new Donation();
		model.put("donation", donation);
		return VIEWS_DONATION_CREATE_FORM;
	}

	@RequestMapping(value = "/donations/new", method = RequestMethod.POST)
	public String processNewCauseForm(Cause cause, @Valid Donation donation, BindingResult result, Map<String, Object> model) {
		Collection <Donation> donations = this.clinicService.findAllDonationsById(cause.getId());
		Double sum = donations.stream().mapToDouble(x -> x.getAmount()).sum();

		if (sum + donation.getAmount() > donation.getCause().getBudgetTarget()) {
			model.put("donation", donation);
			return VIEWS_DONATION_CREATE_FORM;
		} else if (result.hasErrors()) {
			model.put("donation", donation);
			return VIEWS_DONATION_CREATE_FORM;
		} else {
			donation.setDonationDate(new Date());
			this.clinicService.saveDonation(donation);
			return "redirect:/";
		}
	}

}
