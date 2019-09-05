package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("contacts")
    private List<Contact> contacts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Contact> getContacts() {
        List<Contact> res = new ArrayList<>();
        for (Contact c: contacts
             ) {
            System.out.println("Contact is deleted: " + c.getDeleted());
            if(c.getDeleted() == false) {
                res.add(c);
            }
        }
        return res;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<String> getPhoneNums() {
        ArrayList<String> phoneNums = new ArrayList<>();
        List<Contact> available = getContacts();
        if(available.size() >= 1) {
            System.out.println("Found " + available.size() + " contact(s)");
            for (Contact c:available
                 ) {
                System.out.println(c.getDeleted());
                phoneNums.add(c.getPhoneNum());
            }
            return phoneNums;
        } else {
            phoneNums.add(" ");
            return phoneNums;
        }
    }

    public List<Contact> contacts() {
        return contacts;
    }

    public Buyer(String name, String lastname, Contact contact) {
        this.name = name;
        this.lastname = lastname;
        this.contacts.add(contact);
    }
}
