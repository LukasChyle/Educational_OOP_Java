package sprint_4.contactList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Databas {

    private static List<Person> personList;
    private static final Person dummy = new Person("", "", "");

    protected static Person objectHandler(String[] message) {
        return switch (message[0]) {
            case "add" -> add(message);
            case "search" -> search(message);
            case "delete" -> delete(message);
            case "close" -> close();
            default -> null;
        };
    }

    private static synchronized Person add(String[] s) {
        Person p = new Person(s[1], s[2], s[3]);
        if (findPerson(s[1]) != null || findPerson(s[2]) != null || findPerson(s[3]) != null) {
            p.setMessage("exists");
        } else {
            personList.add(p);
            save();
            p.setMessage("added");
        }
        return p;
    }

    private static Person search(String[] s) {
        Person p = findPerson(s[1]);
        if (p != null) {
            p.setMessage("found");
            return p;
        }
        return null;
    }

    private static synchronized Person delete(String[] s) {
        Person p = findPerson(s[1]);
        if (p != null) {
            personList.remove(p);
            save();
            p.setMessage("deleted");
            return p;
        }
        return null;
    }

    private static Person findPerson(String s) {
        for (Person p : personList) {
            if (p.getName().equals(s) || p.getPhone().equals(s) || p.getMail().equals(s)) {
                return p;
            }
        }
        return null;
    }

    private static Person close(){
        dummy.setMessage("close");
        return dummy;
    }

    private static synchronized void save(){
        try (ObjectOutputStream objOPS = new ObjectOutputStream(new FileOutputStream("contactList.ser", false))) {
            objOPS.writeObject(personList);
            System.out.println("personList updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void load(){
        List<Person> list = null;
        try (ObjectInputStream objIPS = new ObjectInputStream(new FileInputStream("contactList.ser"))) {
            list = (List<Person>) objIPS.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (list != null){
            personList = list;
        } else {
            personList = new ArrayList<>();
        }
    }
}
