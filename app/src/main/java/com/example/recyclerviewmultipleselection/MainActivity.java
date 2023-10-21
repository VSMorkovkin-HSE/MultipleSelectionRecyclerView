package com.example.recyclerviewmultipleselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.recyclerviewmultipleselection.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        var contactList = createContactList();
        MultipleSelectionAdapter adapter = new MultipleSelectionAdapter(contactList);
        binding.recyclerViewContacts.setAdapter(adapter);
        binding.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        binding.buttonShowSelected.setOnClickListener(v -> {
            List<Contact> selectedContacts = adapter.getSelected();
            StringBuffer msg = new StringBuffer();
            msg.append("selected: \n");
            if (selectedContacts != null) {
                selectedContacts.forEach(selectedContact -> {
                    msg.append(selectedContact.getName());
                    msg.append(" ");
                    msg.append(selectedContact.getNumber());
                    msg.append("\n");
                });
            } else {
                msg.append("null");
            }
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
        });
    }

    private List<Contact> createContactList() {
        List<Contact> contactList = new ArrayList<>();
        int elementsQuantity = 20;

        var names = new ArrayList<String>();
        names.add("Boris");
        names.add("Mike");
        names.add("John");
        names.add("Karl");

        var rand = new Random();

        for (int i = 0; i < elementsQuantity; ++i) {
            var name = names.get(rand.nextInt(names.size()));
            var number = "+7" + (1000 + rand.nextInt(9000));
            var contact = new Contact(name, number);
            contactList.add(contact);
        }

        return contactList;
    }
}