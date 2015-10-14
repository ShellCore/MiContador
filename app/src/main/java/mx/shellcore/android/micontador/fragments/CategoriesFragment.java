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

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.activities.CategoryDetailActivity;
import mx.shellcore.android.micontador.adapters.CategoriesAdapter;
import mx.shellcore.android.micontador.db.DBCategory;
import mx.shellcore.android.micontador.model.Category;

public class CategoriesFragment extends Fragment {

    private static final String TAG = "Debug";
    private ArrayList<Category> categories;

    private FloatingActionButton addcategory;
    private RecyclerView recCategories;
    private DBCategory dbCategory;
    private CategoriesAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addcategory = (FloatingActionButton) getActivity().findViewById(R.id.add_category);
        addcategory.setOnClickListener(new AddCategoryOnClickListener());

        dbCategory = new DBCategory(getActivity().getApplicationContext());
        categories = dbCategory.getAll();

        categoryAdapter = new CategoriesAdapter(getActivity().getApplicationContext(), categories);
        categoryAdapter.setOnItemClickListener(new CategoryDetailOnClickListener());

        recCategories = (RecyclerView) getActivity().findViewById(R.id.rec_categories);
        recCategories.setHasFixedSize(true);
        recCategories.setAdapter(categoryAdapter);
        recCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
        recCategories.setItemAnimator(new DefaultItemAnimator());

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
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateList();
    }

    private void updateList() {
        categories = dbCategory.getAll();
        categoryAdapter = new CategoriesAdapter(getActivity().getApplicationContext(), categories);
        categoryAdapter.setOnItemClickListener(new CategoryDetailOnClickListener());
        recCategories.setAdapter(categoryAdapter);
    }
}
