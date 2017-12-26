package de.chris.privateprojects.springRestMaven;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RestControllerChris {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private HeroDao heroDao;
    
    @RequestMapping("/person")
    public Person getPerson(@RequestParam(value="id") Integer id){
    		return personDao.findOne(id);	
    }
    
	@RequestMapping("/personList")
	public Iterable<Person> getPersonList() {
		return personDao.findAll();
	}
    
    
	/**
	 * e.g. http://localhost:8080/createPerson?firstName=Chris&lastName=Kienle
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@RequestMapping(value="/createPerson")
	public String createPerson(String firstName, String lastName) {
		String id = "";
		try {
			Person person = new Person(firstName, lastName, new Date(1985, 3, 11));
			personDao.save(person);
			id = String.valueOf(person.getId());
		} catch (Exception ex) {
			return "Error creating the person: " + ex.toString();
		}
		return "Person succesfully created with id = " + id;
	}
	
	@RequestMapping(value="/createPerson2")
	public ResponseEntity<Void> createPerson2(@RequestBody Person person) {
		person.setBirthday(new Date(1985, 3, 11));
		personDao.save(person);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping("/deletePerson")
	@ResponseBody
	public String delete(int id){
		try {
			Person person = personDao.findOne(id);
			personDao.delete(person);
		} catch(Exception ex){
			return "Error deleting the person: " + ex.toString();
		}
		return "Person succesfully deleted!";
	}
	
	@RequestMapping("/updatePerson")
	@ResponseBody
	public String update(int id, String firstName, String lastName){
		try {
			Person person = personDao.findOne(id);
			person.setFirstName(firstName);
			person.setLastName(lastName);
			personDao.save(person);
		} catch (Exception ex){
			return "Error updating the person: " +ex.toString();
		}
		return "Person succesfully updated!";
	}
	
	@RequestMapping("/hero")
    public Hero getHero(@RequestParam(value="id") Integer id){
    		return heroDao.findOne(id);	
    }
    
	@RequestMapping("/heroList")
	public Iterable<Hero> getHeroList() {
		return heroDao.findAll();
	}
	
	@RequestMapping("/createHero")
	public String createHero(String name) {
		String id = "";
		try {
			Hero hero = new Hero(name);
			heroDao.save(hero);
			id = String.valueOf(hero.getId());
		} catch (Exception ex) {
			return "Error creating the hero: " + ex.toString();
		}
		return "Hero succesfully created with id = " + id;
	}
	
	
	
	
	
	
}
