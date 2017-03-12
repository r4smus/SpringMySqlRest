package de.chris.privateprojects.springRestMaven;

import org.springframework.data.repository.CrudRepository;

public interface HeroDao extends CrudRepository<Hero, Integer>  {

}
