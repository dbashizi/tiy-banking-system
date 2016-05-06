package tiy.webapp;

import java.util.ArrayList;

/**
 * Created by Dominique on 5/3/2016.
 */
public class PersonList {
    ArrayList<Person> personArray = new ArrayList<Person>();
    int numOfPeople = 0;

    public int getNumOfPeople() {
        numOfPeople = personArray.size();
        return personArray.size();
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public ArrayList<Person> getPersonArray() {
        return personArray;
    }

    public void setPersonArray(ArrayList<Person> personArray) {
        this.personArray = personArray;
    }

    public void addPerson(Person person) {
        personArray.add(person);
    }
}
