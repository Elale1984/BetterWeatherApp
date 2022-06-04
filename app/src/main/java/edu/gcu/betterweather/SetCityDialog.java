package edu.gcu.betterweather;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;



public class SetCityDialog extends AppCompatDialogFragment {

    private EditText etCityZip;
    private SetCityDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_get_city, null);

        builder.setView(view).setTitle("Add City").setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String zipCode = etCityZip.getText().toString();
                listener.passZipCode(zipCode);
            }
        });

        etCityZip = view.findViewById(R.id.etCityZipCode);


        return builder.create();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (SetCityDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ");
        }
    }

    public interface SetCityDialogListener {
        void passZipCode(String zipCode);
    }
}
