package de.chris.privateprojects.springRestMaven;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    
    
	@RequestMapping("/createPerson")
	public String createPerson(String firstName, String lastName, Date birthday) {
		String id = "";
		try {
			Person person = new Person(firstName, lastName, birthday);
			personDao.save(person);
			id = String.valueOf(person.getId());
		} catch (Exception ex) {
			return "Error creating the person: " + ex.toString();
		}
		return "Person succesfully created with id = " + id;
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
