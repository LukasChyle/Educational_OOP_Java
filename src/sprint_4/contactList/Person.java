package sprint_4.contactList;

import java.io.Serializable;

public class Person implements Serializable {

    private final String name, phone, mail;
    private String message;

    public Person(String name, String phone, String mail) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getMessage() {
        return message;
    }
}