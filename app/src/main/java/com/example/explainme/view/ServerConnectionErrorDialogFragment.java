package com.example.explainme.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.explainme.R;

public class ServerConnectionErrorDialogFragment extends DialogFragment {

    public interface ServerConnectionErrorDialogListener {
        void onServerConnectionErrorDialogPositiveClick(DialogFragment dialog);

        void onServerConnectionErrorDialogNegativeClick(DialogFragment dialog);
    }

    private ServerConnectionErrorDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ServerConnectionErrorDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().toString()
                    + " must implement ServerConnectionErrorDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.serverconnectionerrordialog_message)
                .setPositiveButton(R.string.serverconnectionerrordialog_tryagain, (dialog, id) -> listener.onServerConnectionErrorDialogPositiveClick(ServerConnectionErrorDialogFragment.this))
                .setNegativeButton(R.string.serverconnectionerrordialog_cancel, (dialog, id) -> listener.onServerConnectionErrorDialogNegativeClick(ServerConnectionErrorDialogFragment.this));
        return builder.create();
    }
}
