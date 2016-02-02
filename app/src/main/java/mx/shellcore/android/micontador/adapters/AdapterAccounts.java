package mx.shellcore.android.micontador.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.shellcore.android.micontador.R;
import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.services.ServiceAccount;

public class AdapterAccounts extends RecyclerView.Adapter<AdapterAccounts.ViewHolder> {

    private Context context;
    private ArrayList<Account> accounts;
    private ServiceAccount serviceAccount;

    OnItemClickListener onItemClickListener;

    public AdapterAccounts(Context context, ArrayList<Account> accounts) {
        this.context = context;
        this.accounts = accounts;
        serviceAccount = new ServiceAccount();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_account, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Account account = accounts.get(position);

        holder.setImgAccount(account.getImage().getImage());
        holder.setTxtAccount(account.getName());
        holder.setTxtBalance(serviceAccount.getAccountBalance(account));
        holder.setTxtCents(serviceAccount.getAccountCents(account));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgAccount;
        private TextView txtAccount;
        private TextView txtBalance;
        private TextView txtCents;

        public ViewHolder(View itemView) {
            super(itemView);

            imgAccount = (ImageView) itemView.findViewById(R.id.img_account);
            txtAccount = (TextView) itemView.findViewById(R.id.txt_account);
            txtBalance = (TextView) itemView.findViewById(R.id.txt_balance);
            txtCents = (TextView) itemView.findViewById(R.id.txt_cents);


        }

        public void setImgAccount(String imgUrl) {
            imgAccount.setImageURI(Uri.parse(imgUrl));
        }

        public void setTxtAccount(String account) {
            txtAccount.setText(account);
        }

        public void setTxtBalance(String currency) {
            txtBalance.setText(currency);
        }

        public void setTxtCents(String cents) {
            txtCents.setText(cents);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
