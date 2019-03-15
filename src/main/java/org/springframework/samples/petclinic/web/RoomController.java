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

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class RoomController {

    private final ClinicService clinicService;

    @Autowired
    private Validator validator;

    @Autowired
    public RoomController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */

    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/rooms/new", method = RequestMethod.GET)
    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        Room room = new Room();

        Pet pet = this.clinicService.findPetById(petId);
        
        Collection<Room> roomsPet = this.clinicService.findRoomsByPetId(petId);

        model.put("pet", pet);
        model.put("petId", petId);
        model.put("room", room);
        model.put("roomsPet", roomsPet);
 
        return "pets/createOrUpdateRoomForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/rooms/new", method = RequestMethod.POST)
    public String processNewRoomForm(@Valid Room room, BindingResult result, @RequestParam int petId, Map<String, Object> model) {
        Pet pet = this.clinicService.findPetById(petId);
        
        Collection<Room> roomsPet = this.clinicService.findRoomsByPetId(petId);

        if(result.getAllErrors().size()>1) {
            model.put("pet", pet);
            model.put("petId", petId);
            model.put("room", room);
            model.put("roomsPet", roomsPet);
 
            return "pets/createOrUpdateRoomForm";
        } else {
            room.setPet(pet);

            this.clinicService.saveRoom(room);
            return "redirect:/owners";
        }
    }

}
