package com.example.spvenka.jenieofimages.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.spvenka.jenieofimages.R;

public class EditSettingsDialog extends DialogFragment {

//    private EditText mEditText;

    public EditSettingsDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditSettingsDialog newInstance(String title) {
        EditSettingsDialog frag = new EditSettingsDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_settings, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner(view, R.id.colorSpinner, R.array.color_array);
        setSpinner(view, R.id.sizeSpinner, R.array.size_array);
        setSpinner(view, R.id.tvImageType, R.array.type_array);

        //mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle(R.string.edit_settings);
        // Show soft keyboard automatically and request focus to field
        //mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void setSpinner(View view, int viewId, int resourceId) {
        // Get field from view
        Spinner spinner = (Spinner) view.findViewById(viewId);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                resourceId, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}