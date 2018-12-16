package msk.android.academy.javatemplate;

import java.util.List;

import msk.android.academy.javatemplate.DTO.Contact;

interface ContactListListener {
    void addContacts(List<Contact> contacts);
}
