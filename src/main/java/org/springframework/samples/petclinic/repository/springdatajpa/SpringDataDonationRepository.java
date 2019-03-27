package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;

public interface SpringDataDonationRepository extends DonationRepository, Repository<Donation, Integer> {
	
	@Override
	@Query("select d from Donation d where d.cause.id = :causeId")
	public Collection<Donation> findAllById(@Param("causeId") int causeId);
	

}
