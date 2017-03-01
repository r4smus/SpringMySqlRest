package de.chris.privateprojects.springRestMaven;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface PersonDao extends CrudRepository<Person, Integer> {
	

}
