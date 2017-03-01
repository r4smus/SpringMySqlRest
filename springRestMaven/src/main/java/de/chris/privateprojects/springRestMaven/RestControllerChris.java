package de.chris.privateprojects.springRestMaven;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    	try{
    		return personDao.findOne(id);
    	}
    	catch (Exception ex){
    		//TODO: add a logger
    		System.out.println("Person with the id=" +id +" not found");
    		return null;
    	}
    	
    }
}
