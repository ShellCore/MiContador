package mx.shellcore.android.micontador.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.adapters.AdapterCategories;
import mx.shellcore.android.micontador.db.DBCategory;
import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.ui.activities.CategoryDetailActivity;

public class CategoriesFragment extends Fragment {

    // Variables
    private ArrayList<Category> categories;
    private int categoryType;

    // Adapters
    private AdapterCategories adapterCategories;

    // Services
    private DBCategory dbCategory;

    // Components
    private FloatingActionButton addCategory;
    private RecyclerView recCategories;
    private TextView txtNotElements;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getServices();
        getComponents();

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("CategoryType")) {
            categoryType = bundle.getInt("CategoryType");
        }

        initializeElements();
        setListeners();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateList();
    }

    private void getServices() {
        dbCategory = new DBCategory(getActivity().getApplicationContext());
    }

    private void getComponents() {
        addCategory = (FloatingActionButton) getActivity().findViewById(R.id.add_category);
        recCategories = (RecyclerView) getActivity().findViewById(R.id.rec_income);
        txtNotElements = (TextView) getActivity().findViewById(R.id.txt_not_elements);
    }

    private void initializeElements() {
        categories = dbCategory.getAllByTypeFull(categoryType);
        adapterCategories = new AdapterCategories(getActivity().getApplicationContext(), categories);

        recCategories.setHasFixedSize(true);
        recCategories.setAdapter(adapterCategories);
        recCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
        recCategories.setItemAnimator(new DefaultItemAnimator());

        if (categories.isEmpty()) {
            txtNotElements.setVisibility(View.VISIBLE);
            recCategories.setVisibility(View.GONE);
        } else {
            txtNotElements.setVisibility(View.GONE);
            recCategories.setVisibility(View.VISIBLE);
        }
    }

    private void setListeners() {
        addCategory.setOnClickListener(new AddCategoryOnClickListener());
        adapterCategories.setOnItemClickListener(new CategoryDetailOnClickListener());
    }

    private void updateList() {
        initializeElements();
        setListeners();
    }

    private class CategoryDetailOnClickListener implements AdapterCategories.OnItemClickListener {

        @Override
        public void onItemClick(View v, int position) {
            Category category = categories.get(position);
            Intent intent = new Intent(getActivity().getApplicationContext(), CategoryDetailActivity.class);
            intent.putExtra("Category", category.getId());
            startActivityForResult(intent, 0);
        }
    }

    private class AddCategoryOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity().getApplicationContext(), CategoryDetailActivity.class);
            intent.putExtra("CategoryType", categoryType);
            startActivityForResult(intent, 0);
        }
    }
}
