package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Specialty;

public interface SpecialtyRepository {

	Collection<Specialty> findAll() throws DataAccessException;
	
	//Collection<Specialty> findSpecialtyByVetId(int vetId) throws DataAccessException;
	
	Specialty findById(int id) throws DataAccessException;
	
	Specialty save(Specialty specialty) throws DataAccessException;
	
}
