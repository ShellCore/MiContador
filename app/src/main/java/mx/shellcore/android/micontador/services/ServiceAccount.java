package mx.shellcore.android.micontador.services;

import mx.shellcore.android.micontador.model.Account;
import mx.shellcore.android.micontador.utils.UtilsCurrency;

public class ServiceAccount {

    public String getAccountBalance(Account account) {
        Long val = UtilsCurrency.getIntegerPart(account.getBeginningBalance());
        return (val.toString());
    }

    public String getAccountCents(Account account) {
        Long val = UtilsCurrency.getFractionalPart(account.getBeginningBalance());
        if ((val == 0)) {
            return "00";
        }
        return val.toString();
    }
}
