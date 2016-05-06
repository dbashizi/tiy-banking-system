package tiy.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * Created by Dominique on 5/3/2016.
 */
@Controller
public class SampleSpringAppController {
    @RequestMapping(path = "/personxyz", method = RequestMethod.GET)
    public String getPerson(Model model, String name, String city, int age) {
        Person p = new Person(name, city, age);
        model.addAttribute("person", p);
        Person someoneElse = new Person("Programmer", "Atlanta", 23);
        model.addAttribute("secondPerson", someoneElse);
        return "personabc";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
//        session.removeAttribute("userName");
        return "home";
    }

    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String getChatInput(Model model, HttpSession session) {
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("statusMessage", session.getAttribute("statusMessage"));
        model.addAttribute("messageHistory", session.getAttribute("messageHistory"));
        return "chat";
    }

    @RequestMapping(path = "/send-message", method = RequestMethod.POST)
    public String sendMessage(HttpSession session, String userMessage) {
        ChatClient chatClient = new ChatClient();
        String serverResponse = chatClient.sendMessage(userMessage);
        if (serverResponse != null) {
            session.setAttribute("statusMessage", serverResponse);
        } else {
            session.setAttribute("statusMessage", "Unable to send message");
        }

        return sendCommand(session, "history");

//        return "redirect:/chat";
    }

    @RequestMapping(path = "/send-command", method = RequestMethod.POST)
    public String sendCommand(HttpSession session, String userCommand) {
        ChatClient chatClient = new ChatClient();
        System.out.println("Sending this command to the server: " + userCommand);
        ArrayList<String> messageHistory = chatClient.sendCommand(userCommand);

        System.out.println("Retrieved " + messageHistory.size() + " messages in the archive");
        for (String message : messageHistory) {
            System.out.println(message);
        }

        session.setAttribute("messageHistory", messageHistory);

        return "redirect:/chat";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/chat";
    }

    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public String getPersons(Model model, HttpSession session) {
        Person firstPerson = new Person("Sam", "Paris", 23);
        Person secondPerson = new Person("David", "London", 27);
        PersonList personList = new PersonList();
        personList.addPerson(firstPerson);
        personList.addPerson(secondPerson);

        model.addAttribute("personArray", personList.getPersonArray());
        return "persons";
    }

    @RequestMapping(path = "/personList", method = RequestMethod.GET)
    public String getPersonList(Model model, HttpSession session) {
        Person firstPerson = new Person("Sam", "Paris", 23);
        Person secondPerson = new Person("David", "London", 27);
        PersonList personList = new PersonList();
        personList.addPerson(firstPerson);
        personList.addPerson(secondPerson);

        model.addAttribute("personList", personList);
        return "personList";
    }
}


