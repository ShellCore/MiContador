package mx.shellcore.android.micontador.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.adapters.CategoryImageAdapter;
import mx.shellcore.android.micontador.db.DBCategory;
import mx.shellcore.android.micontador.db.DBCategoryImage;
import mx.shellcore.android.micontador.fragments.DeleteDialogFragment;
import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.model.CategoryImage;
import mx.shellcore.android.micontador.utils.Base64Images;

public class CategoryDetailActivity extends AppCompatActivity implements DeleteDialogFragment.DialogListener {

    public static final String TAG = "Debugging";
    private static final int NUM_COLUMNS = 3;

    private ArrayList<CategoryImage> categoryImages;
    private Category category;
    private Bundle args;

    // Adapters
    private CategoryImageAdapter categoryImageAdapter;

    // Services
    private DBCategory dbCategory;
    private DBCategoryImage dbCategoryImage;

    // Components
    private DeleteDialogFragment deleteDialogFragment;

    private Toolbar toolbar;
    private EditText edtName;
    private Switch swType;
    private RecyclerView recCategoryImages;
    private ImageView imgImage;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        getServices();
        getComponents();
        initializeElements();
        setListeners();


        args = getIntent().getExtras();
        if (args != null && args.containsKey("Category")) {
            getCategoryBundle();
        } else {
            createNewCategory();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_add:
                int msg;
                category.setName(edtName.getText().toString());
                Log.d(TAG, category.toString());
                if (category.getId() != 0) {
                    dbCategory.update(category, category.getId());
                    msg = R.string.confirm_update_category;
                } else {
                    dbCategory.create(category);
                    msg = R.string.confirm_save_category;
                }

                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete:
                if (category.getId() != 0) {
                    deleteDialogFragment = new DeleteDialogFragment();
                    deleteDialogFragment.show(getFragmentManager(), "Eliminar");
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dbCategory.delete(category.getId());
        deleteDialogFragment.dismiss();
        finish();
        Toast.makeText(getApplicationContext(), R.string.confirm_delete_category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        deleteDialogFragment.dismiss();
    }

    private void getComponents() {
        edtName = (EditText) findViewById(R.id.edt_name);
        swType = (Switch) findViewById(R.id.sw_type);
        recCategoryImages = (RecyclerView) findViewById(R.id.rec_category_images);
        toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        imgImage = (ImageView) findViewById(R.id.img_image);
    }

    private void getServices() {
        dbCategory = new DBCategory(getApplicationContext());
        dbCategoryImage = new DBCategoryImage(getApplicationContext());
    }

    @SuppressWarnings("ConstantConditions")
    private void initializeElements() {
        categoryImages = dbCategoryImage.getAll();
        categoryImageAdapter = new CategoryImageAdapter(getApplicationContext(), categoryImages);
        recCategoryImages.setLayoutManager(new GridLayoutManager(getApplicationContext(), NUM_COLUMNS));
        recCategoryImages.setAdapter(categoryImageAdapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setListeners() {
        swType.setOnCheckedChangeListener(new OnTypeClickListener());
        categoryImageAdapter.setOnItemClickListener(new OnImageItemClickListener());
    }

    private void getCategoryBundle() {
        category = args.getParcelable("Category");
        if (category != null) {
            if (category.getLogo() != null) {
                imgImage.setImageBitmap(Base64Images.decode(category.getLogo()));
            }
            edtName.setText(category.getName());
            if (category.getType() == Category.CAT_INCOME) {
                swType.setChecked(false);
                swType.setText(getString(R.string.income));
            } else {
                swType.setChecked(true);
                swType.setText(getString(R.string.expense));
            }
        }
    }

    private void createNewCategory() {
        category = new Category();
        if (args.containsKey("CategoryType")) {
            int categoryType = args.getInt("CategoryType");
            category.setType(categoryType);
            if (categoryType == Category.CAT_INCOME) {
                swType.setChecked(false);
                swType.setText(getString(R.string.income));
            } else {
                swType.setChecked(true);
                swType.setText(getString(R.string.expense));
            }
        }
    }

    private class OnTypeClickListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isChecked) {
                category.setType(Category.CAT_INCOME);
                swType.setChecked(false);
                swType.setText(getString(R.string.income));
            } else {
                category.setType(Category.CAT_EXPENSE);
                swType.setChecked(true);
                swType.setText(getString(R.string.expense));
            }
        }
    }

    private class OnImageItemClickListener implements CategoryImageAdapter.OnItemClickListener {
        @Override
        public void onItemClick(View v, int position) {
            category.setLogo(categoryImages.get(position).getImage());
            imgImage.setImageBitmap(Base64Images.decode(category.getLogo()));
        }
    }
}