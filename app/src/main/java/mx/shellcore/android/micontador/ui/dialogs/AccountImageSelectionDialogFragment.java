package mx.shellcore.android.micontador.ui.dialogs;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.adapters.AdapterImage;
import mx.shellcore.android.micontador.db.DBImage;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.ui.activities.AccountDetailActivity;

public class AccountImageSelectionDialogFragment extends DialogFragment {

    // Constants
    private static final int NUM_COLUMNS = 3;

    // Variables
    private ArrayList<Image> accountImages;

    // Adapters
    private AdapterImage adapterImage;

    // Services
    private DBImage dbImage;

    // Components
    private AccountImageSelectionDialogFragment dialog;
    private RecyclerView recAccountImages;

    public AccountImageSelectionDialogFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_selection_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.dialog = this;
        getServices();
        getComponents(view);
        initializeElements();
        setListeners();
    }

    private void getServices() {
        dbImage = new DBImage(getActivity().getApplicationContext());
    }

    private void getComponents(View view) {
        recAccountImages = (RecyclerView) view.findViewById(R.id.rec_account_images);
    }

    private void initializeElements() {
        accountImages = dbImage.getAllByType(Image.IMG_ACCOUNT);
        adapterImage = new AdapterImage(getActivity().getApplicationContext(), accountImages);
        recAccountImages.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), NUM_COLUMNS));
        recAccountImages.setAdapter(adapterImage);
    }

    private void setListeners() {
        adapterImage.setOnItemClickListener(new OnImageItemClickListener());
    }

    private class OnImageItemClickListener implements AdapterImage.OnItemClickListener {

        @Override
        public void onItemClick(View v, int position) {
            Image img = accountImages.get(position);
            AccountDetailActivity activity = (AccountDetailActivity) getActivity();
            activity.setImageSelected(img);
            dialog.dismiss();
        }
    }
}
