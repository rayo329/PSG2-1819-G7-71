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

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "vets/createOrUpdateOwnerForm";
	private final ClinicService clinicService;

	@Autowired
	public VetController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@ModelAttribute("specialties")
	public Collection<Specialty> populateSpecialties() {
		return this.clinicService.findSpecialties();
	}



	@RequestMapping(value = "/vets/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		//Specialty spec = new Specialty();
		Vet veterinarian = new Vet();
		//    veterinarian.addSpecialty(spec);
		model.put("veterinarian", veterinarian);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@RequestMapping(value = "/vets/{vetId}/edit", method = RequestMethod.GET)
	public String initEditForm(@PathVariable("vetId") int vetId, ModelMap model) {
		Vet veterinarian = this.clinicService.findVetById(vetId);

		model.put("veterinarian", veterinarian);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@RequestMapping(value = "/vets/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Vet veterinarian, BindingResult result, @RequestParam(value="spec", required=false, defaultValue="none") String spec, ModelMap model) {
		veterinarian.deleteSepecialities();

		if(!spec.equals("none")) {
			String[] ss = spec.split(",");
			List<String> selectedSpecs = new ArrayList<> ();
			for(int i = 0; i < ss.length; i++) {
				selectedSpecs.add(ss[i].trim());
			}

			List<Specialty> allSpecs = new ArrayList<Specialty> (this.clinicService.findSpecialties());

			for(Specialty allSpec:allSpecs) {
				for(String selectedSpec:selectedSpecs) {
					if(allSpec.getName().equals(selectedSpec)) {
						veterinarian.addSpecialty(allSpec);
					}
				}
			}
		}

		if (result.getAllErrors().size()>1) {
			model.put("veterinarian", veterinarian);
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			//	Specialty spec = new Specialty();
			//veterinarian.addSpecialty(spec);
			this.clinicService.saveVet(veterinarian);
			//            return "redirect:/vets/" + veterinarian.getId();
			Vets vets = new Vets();
			vets.getVetList().addAll(this.clinicService.findVets());
			model.put("vets",vets);
			return "vets/vetList";
		}
	}

	@RequestMapping(value = "/vets/{vetId}/edit", method = RequestMethod.POST)
	public String processUpdateOwnerForm(@Valid Vet vet, BindingResult result, @PathVariable("vetId") int vetId, @RequestParam(value="spec", required=false, defaultValue="none") String spec) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			
			if(!spec.equals("none")) {
				String[] ss = spec.split(",");
				List<String> selectedSpecs = new ArrayList<> ();
				for(int i = 0; i < ss.length; i++) {
					selectedSpecs.add(ss[i].trim());
				}

				List<Specialty> allSpecs = new ArrayList<Specialty> (this.clinicService.findSpecialties());

				for(Specialty allSpec:allSpecs) {
					for(String selectedSpec:selectedSpecs) {
						if(allSpec.getName().equals(selectedSpec)) {
							vet.addSpecialty(allSpec);
						}
					}
				}
			}
			
			vet.setId(vetId);
			this.clinicService.saveVet(vet);
			return "redirect:/vets.html";
		}
	}

	@RequestMapping(value = { "/vets.html"})
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@RequestMapping(value = { "/vets.json", "/vets.xml"})
	public
	@ResponseBody
	Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		return vets;
	}

	@RequestMapping(value = "/vets/{vetId}/delete", method = RequestMethod.GET)
	public String initDeleteForm(@PathVariable("vetId") int vetId, ModelMap model) {
		this.clinicService.deleteVet(vetId);

		return "redirect:/vets.html";
	}
=======
	
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "vets/createOrUpdateOwnerForm";
	private final ClinicService clinicService;

    @Autowired
    public VetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    
    @ModelAttribute("specialties")
    public Collection<Specialty> populateSpecialties() {
        return this.clinicService.findSpecialties();
    }
    
//    @ModelAttribute("veterinarian")
//    public Vet findVet(@PathVariable("veterinarianId") int veterinarianId) {
//        return this.clinicService.findVetById(veterinarianId);
//    }

    @InitBinder("veterinarian")
    public void initVetBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @InitBinder("specialty")
    public void initSpecialtyBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new SpecialtyValidator());
    }
    
    
    
//    @RequestMapping(value = "/vets/new", method = RequestMethod.GET)
//    public String initCreationForm(Map<String, Object> model) {
//        Vet veterinarian = new Vet();
//        model.put("veterinarian", veterinarian);
//        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//    }
    
    @RequestMapping(value = "/vets/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
    	//Specialty spec = new Specialty();
         Vet veterinarian = new Vet();
     //    veterinarian.addSpecialty(spec);
        model.put("veterinarian", veterinarian);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    
    @RequestMapping(value = "/vets/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Vet veterinarian, BindingResult result, @RequestParam(value="spec", required=false, defaultValue="none") String spec, ModelMap model) {
        veterinarian.deleteSepecialities();
        
        if(!spec.equals("none")) {
            String[] ss = spec.split(",");
        List<String> selectedSpecs = new ArrayList<> ();
        for(int i = 0; i < ss.length; i++) {
            selectedSpecs.add(ss[i].trim());
        }

        List<Specialty> allSpecs = new ArrayList<Specialty> (this.clinicService.findSpecialties());

        for(Specialty allSpec:allSpecs) {
            for(String selectedSpec:selectedSpecs) {
                if(allSpec.getName().equals(selectedSpec)) {
                    veterinarian.addSpecialty(allSpec);
                }
            }
        }
        }

        if (result.getAllErrors().size()>1) {
            model.put("veterinarian", veterinarian);
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
        //	Specialty spec = new Specialty();
        	//veterinarian.addSpecialty(spec);
            this.clinicService.saveVeterinarian(veterinarian);
//            return "redirect:/vets/" + veterinarian.getId();
            Vets vets = new Vets();
            vets.getVetList().addAll(this.clinicService.findVets());
            model.put("vets",vets);
            return "vets/vetList";
        }
    }

    @RequestMapping(value = { "/vets.html"})
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/vetList";
    }

    @RequestMapping(value = { "/vets.json", "/vets.xml"})
    public
    @ResponseBody
    Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }

    @RequestMapping(value = "/vets/{vetId}/delete", method = RequestMethod.GET)
    public String initDeleteForm(@PathVariable("vetId") int vetId, ModelMap model) {
        this.clinicService.deleteVet(vetId);

        return "redirect:/vets.html";
    }
>>>>>>> feature/createVet

}
