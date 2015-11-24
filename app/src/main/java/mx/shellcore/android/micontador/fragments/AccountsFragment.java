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
import mx.shellcore.android.micontador.activities.AccountDetailActivity;
import mx.shellcore.android.micontador.adapters.AdapterAccounts;
import mx.shellcore.android.micontador.db.DBAccount;
import mx.shellcore.android.micontador.model.Account;

public class AccountsFragment extends Fragment {

    // Variables
    private ArrayList<Account> accounts;

    // Adapters
    private AdapterAccounts adapterAccounts;

    // Services
    private DBAccount dbAccount;

    // Components
    private FloatingActionButton addAccount;
    private RecyclerView recAccounts;
    private TextView txtNotElements;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getServices();
        getComponents();
        initializeElements();
        setListeners();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateList();
    }

    private void getServices() {
        dbAccount = new DBAccount(getActivity().getApplicationContext());
    }

    private void getComponents() {
        addAccount = (FloatingActionButton) getActivity().findViewById(R.id.add_account);
        recAccounts = (RecyclerView) getActivity().findViewById(R.id.rec_accounts);
        txtNotElements = (TextView) getActivity().findViewById(R.id.txt_not_elements);
    }

    private void initializeElements() {
        accounts = dbAccount.getAll();
        adapterAccounts = new AdapterAccounts(getActivity().getApplicationContext(), accounts);

        recAccounts.setHasFixedSize(true);
        recAccounts.setAdapter(adapterAccounts);
        recAccounts.setLayoutManager(new LinearLayoutManager(getActivity()));
        recAccounts.setItemAnimator(new DefaultItemAnimator());

        if (accounts.isEmpty()) {
            txtNotElements.setVisibility(View.VISIBLE);
            recAccounts.setVisibility(View.GONE);
        } else {
            txtNotElements.setVisibility(View.GONE);
            recAccounts.setVisibility(View.VISIBLE);
        }
    }

    private void setListeners() {
        addAccount.setOnClickListener(new AddAccountOnClickListener());
        adapterAccounts.setOnItemClickListener(new AccountDetailOnClickListener());
    }

    private void updateList() {
        initializeElements();
        setListeners();
    }

    private class AddAccountOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity().getApplicationContext(), AccountDetailActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    private class AccountDetailOnClickListener implements AdapterAccounts.OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            Account account = accounts.get(position);
            Intent intent = new Intent(getActivity().getApplicationContext(), AccountDetailActivity.class);
            intent.putExtra("Account", account.getId());
            startActivityForResult(intent, 0);
        }
    }
}
