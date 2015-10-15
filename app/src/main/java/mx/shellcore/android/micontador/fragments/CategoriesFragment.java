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
    private ArrayList<Category> incomes;
    private ArrayList<Category> expenses;

    private DBCategory dbCategory;
    private CategoriesAdapter incomeAdapter;

    private FloatingActionButton addcategory;
    private RecyclerView recIncome;
    private TextView txtNotIncome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addcategory = (FloatingActionButton) getActivity().findViewById(R.id.add_category);
        addcategory.setOnClickListener(new AddCategoryOnClickListener());

        txtNotIncome = (TextView) getActivity().findViewById(R.id.txt_not_income);

        dbCategory = new DBCategory(getActivity().getApplicationContext());

        updateIncomeList();
    }

    private class CategoryDetailOnClickListener implements CategoriesAdapter.OnItemClickListener {

        @Override
        public void onItemClick(View v, int position) {
            Category category = incomes.get(position);
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
        updateIncomeList();
    }

    private void updateIncomeList() {
        incomes = dbCategory.getAllByType(Category.CAT_INCOME);
        incomeAdapter = new CategoriesAdapter(getActivity().getApplicationContext(), incomes);
        incomeAdapter.setOnItemClickListener(new CategoryDetailOnClickListener());

        recIncome = (RecyclerView) getActivity().findViewById(R.id.rec_income);
        recIncome.setHasFixedSize(true);
        recIncome.setAdapter(incomeAdapter);
        recIncome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recIncome.setItemAnimator(new DefaultItemAnimator());

        if (incomes.isEmpty()) {
            txtNotIncome.setVisibility(View.VISIBLE);
            recIncome.setVisibility(View.GONE);
        } else {
            txtNotIncome.setVisibility(View.GONE);
            recIncome.setVisibility(View.VISIBLE);
        }
    }
}
