package com.example.recyclerviewmultipleselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmultipleselection.databinding.RvItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MultipleSelectionAdapter extends RecyclerView.Adapter<MultipleSelectionAdapter.MultipleSelectionViewHolder> {

    private List<Contact> contactList;

    public MultipleSelectionAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public MultipleSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MultipleSelectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleSelectionViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public class MultipleSelectionViewHolder extends RecyclerView.ViewHolder {
        RvItemBinding binding;

        public MultipleSelectionViewHolder(RvItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(final Contact contact) {
            binding.imageViewIconCheck.setVisibility(contact.isChecked() ? View.VISIBLE : View.GONE);
            binding.textViewName.setText(contact.getName());
            binding.textViewNumber.setText(contact.getNumber());

            binding.constraintLayoutContainer.setOnClickListener(v -> {
                contact.setChecked(!contact.isChecked());
                binding.imageViewIconCheck.setVisibility(contact.isChecked() ? View.VISIBLE : View.GONE);
            });
        }
    }

    public List<Contact> getSelected() {
        List<Contact> selectedContactsList = new ArrayList<>();

        for (int i = 0; i < contactList.size(); ++i) {
            if (contactList.get(i).isChecked()) {
                selectedContactsList.add(contactList.get(i));
            }
        }

        return selectedContactsList;
    }
}
