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
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {

    Collection<PetType> findPetTypes() throws DataAccessException;

    Owner findOwnerById(int id) throws DataAccessException;
    
    Vet findVetById(int id) throws DataAccessException;
    
//    Specialty findSpecialtyById(int id) throws DataAccessException;

    Pet findPetById(int id) throws DataAccessException;

    Visit findVisitById(int id) throws DataAccessException;

    void savePet(Pet pet) throws DataAccessException;

    void saveVisit(Visit visit) throws DataAccessException;

    Collection<Vet> findVets() throws DataAccessException;

    void saveOwner(Owner owner) throws DataAccessException;

    Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException;

	Collection<Visit> findVisitsByPetId(int petId);

	void deletePet(Pet pet) throws DataAccessException;
	
	void saveVeterinarian(Vet veterinarian) throws DataAccessException;
	
//	void saveSpecialty(Specialty specialty) throws DataAccessException;

    Collection<Specialty> findSpecialties() throws DataAccessException;
    
    void deleteVet(int vetId) throws DataAccessException;

    void saveVet(Vet vet) throws DataAccessException;

    void deleteOwner(Owner owner) throws DataAccessException;

    void deleteVisit(int vetId) throws DataAccessException;
    
    Room findRoomById(int id) throws DataAccessException;

    void saveRoom(Room room) throws DataAccessException;

    Collection<Room> findRoomsByPetId(int petId);

    void deleteRoom(int roomId) throws DataAccessException;

    Cause findCauseById(int causeId) throws DataAccessException;

    void saveCause(Cause cause) throws DataAccessException;
    
    void deleteCauseById(int causeId) throws DataAccessException;

    Collection<Cause> findAllCauses() throws DataAccessException;
}