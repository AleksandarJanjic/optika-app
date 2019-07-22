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
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getPhoneNums() {
        if(contacts.size() >= 1) {
            System.out.println("Found " + contacts.size() + " contact(s)");
            StringBuilder stringBuilder = new StringBuilder();
            for (Contact c:contacts
                 ) {
                stringBuilder.append(c.getPhoneNum());
                stringBuilder.append(" - ");
            }
            return stringBuilder.toString();
        }
        return " ";
    }

    public Buyer(String name, String lastname, Contact contact) {
        this.name = name;
        this.lastname = lastname;
        this.contacts.add(contact);
    }
}
