package mx.shellcore.android.micontador.fragments;

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
import mx.shellcore.android.micontador.activities.CategoryDetailActivity;
import mx.shellcore.android.micontador.adapters.CategoriesAdapter;
import mx.shellcore.android.micontador.db.DBCategory;
import mx.shellcore.android.micontador.model.Category;

public class CategoriesFragment extends Fragment {

    private static final String TAG = "Debug";
    private ArrayList<Category> categories;
    private int categoryType;

    private DBCategory dbCategory;
    private CategoriesAdapter categoriesAdapter;

    private FloatingActionButton addcategory;
    private RecyclerView recCategories;
    private TextView txtNotElements;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("CategoryType")) {
            categoryType = bundle.getInt("CategoryType");

            addcategory = (FloatingActionButton) getActivity().findViewById(R.id.add_category);
            addcategory.setOnClickListener(new AddCategoryOnClickListener());

            txtNotElements = (TextView) getActivity().findViewById(R.id.txt_not_elements);

            dbCategory = new DBCategory(getActivity().getApplicationContext());

            updateList();
        }
    }

    private class CategoryDetailOnClickListener implements CategoriesAdapter.OnItemClickListener {

        @Override
        public void onItemClick(View v, int position) {
            Category category = categories.get(position);
            Intent intent = new Intent(getActivity().getApplicationContext(), CategoryDetailActivity.class);
            intent.putExtra("Category", category);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateList();
    }

    private void updateList() {
        categories = dbCategory.getAllByType(categoryType);
        categoriesAdapter = new CategoriesAdapter(getActivity().getApplicationContext(), categories);
        categoriesAdapter.setOnItemClickListener(new CategoryDetailOnClickListener());

        recCategories = (RecyclerView) getActivity().findViewById(R.id.rec_income);
        recCategories.setHasFixedSize(true);
        recCategories.setAdapter(categoriesAdapter);
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
}
