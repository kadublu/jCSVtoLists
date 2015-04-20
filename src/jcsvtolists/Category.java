/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcsvtolists;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Category {

    /**
     * Category's name.
     */
    private String name;

    /**
     * List of contacts.
     */
    private final List<Contact> contacts;

    public Category() {
        this.contacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    @Override
    public String toString() {
        String ret = "";
        for (Contact contact : contacts) {
            ret += contact + "\n";
        }
        return ret;
    }
    
}