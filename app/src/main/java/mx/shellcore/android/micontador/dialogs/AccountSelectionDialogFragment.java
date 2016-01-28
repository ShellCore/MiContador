package mx.shellcore.android.micontador.dialogs;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.adapters.AdapterCategoryImage;
import mx.shellcore.android.micontador.db.DBImage;
import mx.shellcore.android.micontador.model.Image;

public class AccountSelectionDialogFragment extends DialogFragment {

    // Constants
    private static final int NUM_COLUMNS = 3;

    // Variables
    private ArrayList<Image> accountImages;

    // Adapters
    private AdapterCategoryImage adapterCategoryImage;

    // Services
    private DBImage dbImage;

    // Components
    private RecyclerView recAccountImages;

    public AccountSelectionDialogFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_selection_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSetvices();
        getComponents();
        initializeElements();
        setListeners();
    }

    private void getSetvices() {
        // TODO
    }

    private void getComponents() {
        // TODO
    }

    private void initializeElements() {
        // TODO
    }

    private void setListeners() {
        // TODO
    }
}
