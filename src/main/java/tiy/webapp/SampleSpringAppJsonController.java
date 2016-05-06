package tiy.webapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Dominique on 5/3/2016.
 */
@RestController
public class SampleSpringAppJsonController{
    @RequestMapping(path = "/person.json", method = RequestMethod.GET)
    public ArrayList<Person> jsonHome(String name, String city, int age) {
        Person first = new Person(name, city, age);
        Person second = new Person("Programmer", "Atlanta", 23);
        ArrayList<Person> myPeople = new ArrayList<Person>();
        myPeople.add(first);
        myPeople.add(second);

        return myPeople;
    }
}