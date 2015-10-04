package mx.shellcore.android.micontador.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.builders.CategoryBuilder;
import mx.shellcore.android.micontador.db.DBCategory;
import mx.shellcore.android.micontador.model.Category;

public class CategoryDetailActivity extends AppCompatActivity {

    public static final String TAG = "Debugging";

    private Category category;

    private Toolbar toolbar;
    private DBCategory dbCategory;
    private EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        category = new Category();

        toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        edtName = (EditText) findViewById(R.id.edt_name);
        dbCategory = new DBCategory(getApplicationContext());
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
                category.setName(edtName.getText().toString());

                dbCategory.create(CategoryBuilder.createCategoryContent(category));
                setResult(RESULT_OK);
                finish();

                Toast.makeText(getApplicationContext(), R.string.confirm_save_category, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
