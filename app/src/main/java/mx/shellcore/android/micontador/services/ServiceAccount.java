package mx.shellcore.android.micontador.services;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.utils.UtilsCurrency;

public class ServiceAccount {

    public String getAccountBalance(Account account) {
        Long val = UtilsCurrency.getIntegerPart(account.getBeginningBalance());
        return ("$" + val.toString() + ".");
    }

    public String getAccountCents(Account account) {
        Double val = UtilsCurrency.getFractionalPart(account.getBeginningBalance());
        return val.toString();
    }
}
