package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;

public interface SpringDataSpecialtyRepository extends SpecialtyRepository, Repository<Specialty, Integer>{
	

    //@Query("SELECT DISTINCT s FROM Specialty s where s.vet.id = :vetId")
    //public Collection<Specialty> findSpecialtyByVetId(@Param("vetId") int vetId);

}
