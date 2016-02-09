package mx.shellcore.android.micontador.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.db.DBAccount;
import mx.shellcore.android.micontador.db.DBCreditAccount;
import mx.shellcore.android.micontador.db.DBCurrency;
import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.CreditAccount;
import mx.shellcore.android.micontador.model.Currency;
import mx.shellcore.android.micontador.model.Image;
import mx.shellcore.android.micontador.ui.dialogs.AccountImageSelectionDialogFragment;

public class AccountDetailActivity extends AppCompatActivity {

    // Constants


    // Variables
    private ArrayList<Currency> currencies;
    private Account account;
    private CreditAccount creditAccount;
    private Bundle args;

    // Adapters


    // Services
    private DBAccount dbAccount;
    private DBCreditAccount dbCreditAccount;
    private DBCurrency dbCurrency;


    // Components
    private Toolbar accountToolbar;
    private CheckBox chkCredit;
    private TextInputLayout tilAccount;
    private EditText edtAccount;
    private TextInputLayout tilBeginningBalance;
    private EditText edtBeginningBalance;
    private TextInputLayout tilCourtDay;
    private EditText edtCourtDay;
    private TextInputLayout tilCreditLimit;
    private EditText edtCreditLimit;
    private TextInputLayout tilLimitDay;
    private EditText edtLimitDay;
    private ImageView imgAccount;
    private LinearLayout linCredit;
    private Spinner spnCurrencies;
    private TextView txtCurrencySymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        getServices();
        getComponents();
        initializeElements();
        setListeners();
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
                if (validAccount()) {
                    int msg;
                    if (account.getId() != 0) {
                        dbAccount.update(account, account.getId());
                        if (chkCredit.isChecked()) {
                            creditAccount.setAccount(account);
                            dbCreditAccount.update(creditAccount, creditAccount.getId());
                        }
                        msg = R.string.confirm_save_account;
                    } else {
                        long idAccount = dbAccount.create(account);
                        if (chkCredit.isChecked()) {
                            account.setId((int) idAccount);
                            creditAccount.setAccount(account);
                            creditAccount.setId(account.getId());
                            dbCreditAccount.update(creditAccount, creditAccount.getId());
                        }
                        msg = R.string.confirm_save_account;
                    }
                    setResult(RESULT_OK);
                    finish();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_delete:
                if (account.getId() != 0) {
                    Toast.makeText(getApplicationContext(), "Delete pressed", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getServices() {
        dbAccount = new DBAccount(getApplicationContext());
        dbCreditAccount = new DBCreditAccount(getApplicationContext());
        dbCurrency = new DBCurrency(getApplicationContext());
    }

    private void getComponents() {
        accountToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        chkCredit = (CheckBox) findViewById(R.id.chk_credit);
        tilAccount = (TextInputLayout) findViewById(R.id.til_account);
        edtAccount = (EditText) findViewById(R.id.edt_account);
        tilBeginningBalance = (TextInputLayout) findViewById(R.id.til_beginning_balance);
        edtBeginningBalance = (EditText) findViewById(R.id.edt_beginning_balance);
        tilCourtDay = (TextInputLayout) findViewById(R.id.til_court_day);
        edtCourtDay = (EditText) findViewById(R.id.edt_court_day);
        tilCreditLimit = (TextInputLayout) findViewById(R.id.til_credit_limit);
        edtCreditLimit = (EditText) findViewById(R.id.edt_credit_limit);
        tilLimitDay = (TextInputLayout) findViewById(R.id.til_limit_day);
        edtLimitDay = (EditText) findViewById(R.id.edt_limit_day);
        imgAccount = (ImageView) findViewById(R.id.img_account);
        linCredit = (LinearLayout) findViewById(R.id.lin_credit);
        spnCurrencies = (Spinner) findViewById(R.id.spn_currencies);
        txtCurrencySymbol = (TextView) findViewById(R.id.txt_currency_symbol);
    }

    public void initializeElements() {
        setCurrencyAdapter();
        setToolbar();
        setAccount();
    }

    private void setListeners() {
        imgAccount.setOnClickListener(new OnAccountImageClickListener());
        chkCredit.setOnClickListener(new OnCreditCheckClickListener());
        spnCurrencies.setOnItemSelectedListener(new OnCurrencySpinnerItemSelected());
    }

    @SuppressWarnings("unchecked")
    private void setCurrencyAdapter() {
        currencies = dbCurrency.getAllByCurrency();
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), R.layout.currency_spinner_item, currencies.toArray());
        spnCurrencies.setAdapter(spinnerAdapter);
    }


    @SuppressWarnings("ConstantConditions")
    private void setToolbar() {
        setSupportActionBar(accountToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    public void setAccount() {
        args = getIntent().getExtras();
        if (args != null && args.containsKey("Account")) {
            getAccount();
        } else {
            createAccount();
        }
    }

    private void getAccount() {
        account = dbAccount.getByIdComplete(args.getInt("Account"));
        if (account != null) {
            edtAccount.setText(account.getName());
            edtBeginningBalance.setText(String.format("%f", account.getBeginningBalance()));
            imgAccount.setImageURI(Uri.parse(account.getImage().getPath()));
            setSelectedCurrency(account.getCurrency());

            creditAccount = dbCreditAccount.getByAccountId(account.getId());
            if (creditAccount != null) {
                chkCredit.setChecked(true);
                setCreditAccount();
            } else {
                chkCredit.setChecked(false);
                setNoCreditAccount();
            }
        }
    }

    private void createAccount() {
        account = new Account();
        creditAccount = new CreditAccount();
        account.setCurrency((Currency) spnCurrencies.getSelectedItem());
        setNoCreditAccount();
    }

    private void setSelectedCurrency(Currency currency) {
        spnCurrencies.setSelection(currencies.indexOf(currency));
    }

    private void setCreditAccount() {
        linCredit.setVisibility(View.VISIBLE);
        creditAccount.setAccount(account);
//        edtCourtDay.setText(String.format("%d", creditAccount.getCourtDay()));
//        edtCreditLimit.setText(String.format("%f", creditAccount.getCreditLimit()));
//        edtLimitDay.setText(String.format("%d", creditAccount.getLimitPayDay()));
//        chkCredit.setChecked(true);
    }

    private void setNoCreditAccount() {
        linCredit.setVisibility(View.GONE);
        edtCourtDay.setText("");
        edtCreditLimit.setText("");
        edtLimitDay.setText("");
        creditAccount.setAccount(null);
    }

    public void setImageSelected(Image img) {
        account.setImage(img);
        imgAccount.setImageURI(Uri.parse(account.getImage().getPath()));
    }

    private boolean validAccount() {
        boolean res = true;
        if (!edtAccount.getText().toString().isEmpty()) {
            account.setName(edtAccount.getText().toString());
            tilAccount.setError(null);
        } else {
            tilAccount.setError(getString(R.string.error_account_name));
            res = false;
        }
        account.setCurrency((Currency) spnCurrencies.getSelectedItem());
        if (!edtBeginningBalance.getText().toString().isEmpty()) {
            account.setBeginningBalance(Double.parseDouble(edtBeginningBalance.getText().toString()));
            tilBeginningBalance.setError(null);
        } else {
            tilBeginningBalance.setError(getString(R.string.error_beginning_balance));
            res = false;
        }
        if (account.getImage() == null) {
            Toast.makeText(getApplicationContext(), R.string.account_empty_image, Toast.LENGTH_SHORT).show();
            res = false;
        }
        if (chkCredit.isChecked()) {
            account.setType(Account.CREDIT);
            if (!edtCourtDay.getText().toString().isEmpty()) {
                creditAccount.setCourtDay(Integer.parseInt(edtCourtDay.getText().toString()));
                tilCourtDay.setError(null);
            } else {
                tilCourtDay.setError(getString(R.string.error_court_day));
                res = false;
            }
            if (!edtLimitDay.getText().toString().isEmpty()) {
                creditAccount.setLimitPayDay(Integer.parseInt(edtLimitDay.getText().toString()));
                tilLimitDay.setError(null);
            } else {
                tilLimitDay.setError(getString(R.string.error_limit_day));
                res = false;
            }
            if (!edtCreditLimit.getText().toString().isEmpty()) {
                creditAccount.setCreditLimit(Double.valueOf(edtCreditLimit.getText().toString()));
                tilCreditLimit.setError(null);
            } else {
                tilCreditLimit.setError(getString(R.string.error_credit_limit));
                res = false;
            }
            creditAccount.setAccount(account);
        } else {
            account.setType(Account.OTHER);
        }
        return res;
    }

    private class OnAccountImageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AccountImageSelectionDialogFragment dialogFragment = new AccountImageSelectionDialogFragment();
            dialogFragment.show(getFragmentManager(), "imageDialog");

        }
    }

    private class OnCreditCheckClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (chkCredit.isChecked()) {
                setCreditAccount();
            } else {
                setNoCreditAccount();
            }
        }
    }


    private class OnCurrencySpinnerItemSelected implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            account.setCurrency(currencies.get(position));
            txtCurrencySymbol.setText(account.getCurrency().getSymbol());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
