package mx.shellcore.android.micontador.dialogs;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;

public class AccountSelectionDialogFragment extends DialogFragment {

    // Constants
    private static final int NUM_COLUMNS = 3;

    // Variables
    private ArrayList<String> accountImages;

    // Components
    private RecyclerView recAccountImages;

    public AccountSelectionDialogFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_selection_dialog, container, false);
    }
}
