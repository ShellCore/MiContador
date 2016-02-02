package mx.shellcore.android.micontador.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import mx.shellcore.android.micontador.R;

public class DeleteDialogFragment extends DialogFragment {

    private DialogListener listener;

    public DeleteDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (DialogListener) activity;
        } catch (ClassCastException e) {
            Log.e("Error", Log.getStackTraceString(e));
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.delete))
                .setMessage(R.string.msg_delete_confirm)
                .setPositiveButton("Aceptar", new PositiveDialogListener())
                .setNegativeButton("Cancelar", new NegativeDialogListener());

        return builder.create();
    }

    public interface DialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private class PositiveDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.onDialogPositiveClick(DeleteDialogFragment.this);
        }
    }

    private class NegativeDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.onDialogNegativeClick(DeleteDialogFragment.this);
        }
    }
}
