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
    private boolean[] selected;

    public MultipleSelectionAdapter(List<Contact> contactList) {
        this.contactList = contactList;
        selected = new boolean[contactList.size()];
        for (int i = 0; i < contactList.size(); ++i) {
            selected[i] = false;
        }
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

        public void bind(Contact contact) {
            binding.textViewName.setText(contact.name);
            binding.textViewNumber.setText(contact.number);

            binding.constraintLayoutContainer.setOnClickListener(v -> {
                var adapterPosition = getAdapterPosition();
                selected[adapterPosition] = !selected[adapterPosition];
                binding.imageViewIconCheck.setVisibility(
                        selected[adapterPosition] ? View.VISIBLE : View.GONE
                );
                notifyItemChanged(adapterPosition);
            });
        }
    }

    public List<Contact> getSelected() {
        List<Contact> selectedContactsList = new ArrayList<>();

        for (int i = 0; i < contactList.size(); ++i) {
            if (selected[i]) {
                selectedContactsList.add(contactList.get(i));
            }
        }

        return selectedContactsList;
    }
}
