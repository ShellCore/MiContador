package mx.shellcore.android.micontador.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.db.DBAccount;
import mx.shellcore.android.micontador.db.DBCreditAccount;
import mx.shellcore.android.micontador.db.DBCurrency;
import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.model.CreditAccount;
import mx.shellcore.android.micontador.model.Currency;

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
    private EditText edtAccount;
    private EditText edtBeginningBalance;
    private EditText edtCourtDay;
    private EditText edtCreditLimit;
    private EditText edtLimitDay;
    private ImageView imgAccount;
    private LinearLayout linCredit;
    private Spinner spnCurrencies;


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

    private void getServices() {
        dbAccount = new DBAccount(getApplicationContext());
        dbCreditAccount = new DBCreditAccount(getApplicationContext());
        dbCurrency = new DBCurrency(getApplicationContext());
    }

    private void getComponents() {
        accountToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        chkCredit = (CheckBox) findViewById(R.id.chk_credit);
        edtAccount = (EditText) findViewById(R.id.edt_account);
        edtBeginningBalance = (EditText) findViewById(R.id.edt_beginning_balance);
        edtCourtDay = (EditText) findViewById(R.id.edt_court_day);
        edtCreditLimit = (EditText) findViewById(R.id.edt_credit_limit);
        edtLimitDay = (EditText) findViewById(R.id.edt_limit_day);
        imgAccount = (ImageView) findViewById(R.id.img_account);
        linCredit = (LinearLayout) findViewById(R.id.lin_credit);
        spnCurrencies = (Spinner) findViewById(R.id.spn_currencies);
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
        account = dbAccount.getById(args.getInt("Account"));
        if (account != null) {
            edtAccount.setText(account.getName());
            edtBeginningBalance.setText(String.format("%f", account.getBeginningBalance()));
            imgAccount.setImageURI(Uri.parse(account.getImage()));
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

    private class OnAccountImageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Falta implementar array de imagenes a seleccionar
            Toast.makeText(getApplicationContext(), "Image clicked", Toast.LENGTH_SHORT)
                    .show();
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
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
