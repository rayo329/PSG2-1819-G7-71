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
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DonationController {

	private static final String VIEWS_DONATION_CREATE_FORM = "donations/createDonationForm";
	private ClinicService clinicService;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	public DonationController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(value = "/donations/list", method=RequestMethod.GET)
	public String showAll(Map<String,Object> model){
		
		Collection<Donation> donations = this.clinicService.findAllDonations();
		model.put("donations", donations);
		return "donations/donationList";
	}

	@RequestMapping(value = "/causes/{causeId}/donations/new", method = RequestMethod.GET)
	public String initCreationForm(@PathVariable("causeId") int causeId, Map<String, Object> model) {
		Donation donation = new Donation();
		donation.setDonationDate(new Date());

		model.put("donation", donation);
		model.put("causeId", causeId);
		return VIEWS_DONATION_CREATE_FORM;
	}

	@RequestMapping(value = "/causes/{causeId}/donations/new", method = RequestMethod.POST)
	public String processNewCauseForm(@RequestParam int causeId, @Valid Donation donation, BindingResult result, Map<String, Object> model) {
		Collection <Donation> donations = this.clinicService.findAllDonationsById(causeId);
		Double sum = donations.stream().mapToDouble(x -> x.getAmountDonation()).sum();

		Cause cause = this.clinicService.findCauseById(causeId);
		donation.setCause(cause);
		donation.setDonationDate(new Date());

		this.validator.validate(donation, result);
		try {
			if (sum + donation.getAmountDonation() > donation.getCause().getBudgetTarget()) {
				model.put("donation", donation);
				model.put("causeId", causeId);
				return VIEWS_DONATION_CREATE_FORM;
			} else if (result.getAllErrors().size() > 1) {
				model.put("donation", donation);
				model.put("causeId", causeId);
				return VIEWS_DONATION_CREATE_FORM;
			} else {
				this.clinicService.saveDonation(donation);
				return "redirect:/";
			}
		} catch(Throwable oops) {
			model.put("donation", donation);
			model.put("causeId", causeId);
			return VIEWS_DONATION_CREATE_FORM;
		}
	}
}
