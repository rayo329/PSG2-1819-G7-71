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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.RoomRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    private PetRepository petRepository;
    private VetRepository vetRepository;
    private OwnerRepository ownerRepository;
    private VisitRepository visitRepository;
    private RoomRepository roomRepository;
    private CauseRepository causeRepository;
    private DonationRepository donationRepository;
    
    @Autowired
    public ClinicServiceImpl(PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository,
            VisitRepository visitRepository, RoomRepository roomRepository, CauseRepository causeRepository, DonationRepository donationRepository) {
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.visitRepository = visitRepository;
        this.roomRepository = roomRepository;
        this.causeRepository = causeRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findPetTypes() throws DataAccessException {
        return petRepository.findPetTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) throws DataAccessException {
        return ownerRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Vet findVetById(int id) throws DataAccessException {
        return vetRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Visit findVisitById(int id) throws DataAccessException {
        return visitRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Room findRoomById(int id) throws DataAccessException {
        return roomRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) throws DataAccessException {
        ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public void saveVisit(Visit visit) throws DataAccessException {
        visitRepository.save(visit);
    }

    @Override
    @Transactional
    public void saveRoom(Room room) throws DataAccessException {
        roomRepository.save(room);
    }

    @Override
    @Transactional(readOnly = true)
    public Pet findPetById(int id) throws DataAccessException {
        return petRepository.findById(id);
    }

    // @Override
    // @Transactional(readOnly = true)
    // public Specialty findSpecialtyById(int id) throws DataAccessException {
    // return vetRepository.findByIdd(id);
    // }

    @Override
    @Transactional
    public void savePet(Pet pet) throws DataAccessException {
        petRepository.save(pet);
    }

    // @Override
    // @Transactional
    // public void saveSpecialty(Specialty specialty) throws DataAccessException {
    // vetRepository.savee(specialty);
    // }

    @Override
    @Transactional
    public void deletePet(Pet pet) throws DataAccessException {
        petRepository.deleteById(pet.getId());
    }

    @Override
    @Transactional
    public void saveVeterinarian(Vet veterinarian) throws DataAccessException {
        vetRepository.save(veterinarian);
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "vets")
    public Collection<Vet> findVets() throws DataAccessException {
        return vetRepository.findAll();
    }

    @Override
    @Transactional
    public Collection<Visit> findVisitsByPetId(int petId) {
        return visitRepository.findByPetId(petId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Specialty> findSpecialties() throws DataAccessException {
        return vetRepository.findSpecialties();

    }

    @Override
    @Transactional
    public Collection<Room> findRoomsByPetId(int petId) {
        return roomRepository.findByPetId(petId);
    }
    
    @Override
    @Transactional
    public Collection<Room> findAllRooms() throws DataAccessException{
    	return this.roomRepository.findAll();
    }


    @Override
    @Transactional
    public void deleteVet(int vetId) throws DataAccessException {
        vetRepository.deleteById(vetId);
    }

    @Override
    @Transactional
    public void saveVet(Vet vet) throws DataAccessException {
        this.vetRepository.save(vet);
    }

    @Override
    @Transactional
    public void deleteOwner(Owner owner) throws DataAccessException {
        this.ownerRepository.deleteById(owner.getId());
    }

    public void deleteRoom(int roomId) throws DataAccessException {
        this.roomRepository.deleteById(roomId);
    }

    @Override
    @Transactional
    public void deleteVisit(int visitId) throws DataAccessException {
        this.visitRepository.deleteById(visitId);
    }

    @Override
    @Transactional
    public Cause findCauseById(int causeId) throws DataAccessException {
        return this.causeRepository.findById(causeId);
    }

    @Override
    @Transactional
    public void saveCause(Cause cause) throws DataAccessException {
        this.causeRepository.save(cause);
    }

    @Override
    @Transactional
    public void deleteCauseById(int causeId) throws DataAccessException {
        this.causeRepository.deleteById(causeId);
    }

    @Override
    @Transactional
    public Collection<Cause> findAllCauses() throws DataAccessException {
        return this.causeRepository.findAll();
    }
    
    @Override
    @Transactional
    public Donation findDonationById(int donationId) throws DataAccessException {
        return this.donationRepository.findById(donationId);
    }
    

    @Override
    @Transactional
    public void saveDonation(Donation donation) throws DataAccessException {
        this.donationRepository.save(donation);
    }

    @Override
    @Transactional
    public Collection<Donation> findAllDonations() throws DataAccessException {
        return this.donationRepository.findAll();
	}

	@Override
	public Collection<Donation> findAllDonationsById(int causeId) throws DataAccessException {
		return this.donationRepository.findAllById(causeId);
	}

}