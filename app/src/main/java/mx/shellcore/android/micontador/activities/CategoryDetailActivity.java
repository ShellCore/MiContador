package mx.shellcore.android.micontador.activities;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.db.DBCategory;
import mx.shellcore.android.micontador.db.DBCategoryImage;
import mx.shellcore.android.micontador.fragments.DeleteDialogFragment;
import mx.shellcore.android.micontador.model.Category;
import mx.shellcore.android.micontador.model.CategoryImage;

public class CategoryDetailActivity extends AppCompatActivity implements DeleteDialogFragment.DialogListener {

    public static final String TAG = "Debugging";

    private Category category;
    private boolean catExpense = false;

    private DBCategory dbCategory;
    private DBCategoryImage dbCategoryImage;

    private DeleteDialogFragment deleteDialogFragment;

    private Toolbar toolbar;
    private EditText edtName;
    private Switch swType;
    private ImageView imgLogo;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        dbCategory = new DBCategory(getApplicationContext());
        dbCategoryImage = new DBCategoryImage(getApplicationContext());

        edtName = (EditText) findViewById(R.id.edt_name);
        swType = (Switch) findViewById(R.id.sw_type);
        swType.setOnCheckedChangeListener(new OnTypeClickListener());

        imgLogo = (ImageView) findViewById(R.id.img_logo);

        CategoryImage image = dbCategoryImage.getById(1);

        byte[] imgbyte = Base64.decode(image.getImage(), Base64.DEFAULT);
        Bitmap img = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
        if (img != null) {
            imgLogo.setImageBitmap(img);
        } else {
            Log.e(TAG, "Error de bitmap");
        }


        toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Bundle args = getIntent().getExtras();
        if (args != null && args.containsKey("Category")) {
            category = args.getParcelable("Category");
            edtName.setText(category.getName());
            if (category.getType() == Category.CAT_EXPENSE) {
                catExpense = true;
                swType.setText(getString(R.string.expense));
                swType.setChecked(catExpense);
            }
        } else {
            category = new Category();
            category.setType(Category.CAT_INCOME);
            if (args != null && args.containsKey("CategoryType")) {
                int categoryType = args.getInt("CategoryType");
                category.setType(categoryType);
                if (category.getType() == Category.CAT_EXPENSE) {
                    catExpense = true;
                    swType.setText(getString(R.string.expense));
                    swType.setChecked(catExpense);
                }
            }
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

    private class OnTypeClickListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            catExpense = isChecked;
            swType.setChecked(catExpense);
            swType.setText(catExpense ? getString(R.string.expense) : getString(R.string.income));
            category.setType(catExpense ? Category.CAT_EXPENSE : Category.CAT_INCOME);
            Log.d(TAG, catExpense ? "Category.CAT_EXPENSE" : "Category.CAT_INCOME");
        }
    }
}