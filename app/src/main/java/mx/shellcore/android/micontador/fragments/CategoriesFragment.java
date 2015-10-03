package mx.shellcore.android.micontador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import mx.shellcore.android.micontador.R;

public class CategoriesFragment extends Fragment {

    private FloatingActionButton addcategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addcategory = (FloatingActionButton) getActivity().findViewById(R.id.add_category);
        addcategory.setOnClickListener(new AddCategoryOnClickListener());
    }

    private class AddCategoryOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Add clicked", Toast.LENGTH_SHORT)
            .show();
        }
    }
}
