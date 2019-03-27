/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/causes/{causeId}")
public class DonationController {

	private static final String VIEWS_DONATION_CREATE_FORM = "donations/createDonationForm";
	private final ClinicService clinicService;

	@Autowired
	public DonationController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/donations/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		Donation donation = new Donation();
		model.put("donation", donation);
		return VIEWS_DONATION_CREATE_FORM;
	}

	@RequestMapping(value = "/donations/new", method = RequestMethod.POST)
	public String processNewCauseForm(Cause cause, @Valid Donation donation, BindingResult result, Map<String, Object> model) {
		List<Donation> donations = this.clinicService.findDonationsByCauseId(cause.getId());
		Double sum = donations.stream().mapToDouble(x -> x.getAmount()).sum();

		if (sum + donation.getAmount() > donation.getCause().getBudgetTarget()) {
			model.put("donation", donation);
			return VIEWS_DONATION_CREATE_FORM;
		} else if (result.hasErrors()) {
			model.put("donation", donation);
			return VIEWS_DONATION_CREATE_FORM;
		} else {
			donation.setDate(new Date());
			this.clinicService.saveDonation(donation);
			return "redirect:/";
		}
	}

}
