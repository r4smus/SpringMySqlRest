package de.chris.privateprojects.springRestMaven;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerChris {

	@Autowired
	private PersonDao personDao;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/person")
    public Person getPerson(@RequestParam(value="id") Integer id){
    		return personDao.findOne(id);	
    }
    
	@RequestMapping("/create")
	public String create(String firstName, String lastName) {
		String id = "";
		try {
			Person person = new Person(firstName, lastName);
			personDao.save(person);
			id = String.valueOf(person.getId());
		} catch (Exception ex) {
			return "Error creating the person: " + ex.toString();
		}
		return "Person succesfully created with id = " + id;
	}
	
	@RequestMapping("/delete")
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
	
	@RequestMapping("/update")
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
	
	
	
	
	
	
}
