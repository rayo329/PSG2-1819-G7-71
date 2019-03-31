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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class CauseController {

	private static final String VIEWS_CAUSE_CREATE_FORM = "causes/createCauseForm";
	private final ClinicService clinicService;

	@Autowired
	public CauseController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/causes/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		Cause cause = new Cause();

		model.put("cause", cause);
		return VIEWS_CAUSE_CREATE_FORM;
	}

	@RequestMapping(value = "/causes/new", method = RequestMethod.POST)
    public String processNewCauseForm(@Valid Cause cause, BindingResult result, Map<String, Object> model) {

        if(result.hasErrors()) {
            model.put("cause", cause);
            return VIEWS_CAUSE_CREATE_FORM;
        } else {
            this.clinicService.saveCause(cause);
            return "redirect:/";
        }
    }
	
	@RequestMapping("/causes/{causeId}/details")
    public ModelAndView showCause(@PathVariable("causeId") int causeId) {
        ModelAndView mav = new ModelAndView("causes/causeDetails");

        Cause causa = this.clinicService.findCauseById(causeId);
        Collection<Donation> donations = clinicService.findAllDonationsById(causa.getId());
       
        mav.addObject("cause",causa);
        mav.addObject("donations", donations);
        return mav;
    }

	@RequestMapping(value ="/causes/list", method = RequestMethod.GET)
	public String showCauseList( Map<String, Object> model) {
		Collection<Cause> causes = this.clinicService.findAllCauses();
		model.put("causes", causes);
		return "causes/causeList";
	}

}
