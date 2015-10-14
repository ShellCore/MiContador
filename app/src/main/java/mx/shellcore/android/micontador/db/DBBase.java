package mx.shellcore.android.micontador.db;

import java.util.ArrayList;

public interface DBBase<BO> {

    void create(BO bo);
    ArrayList<BO> getAll();
    BO getById(int id);
    void update(BO bo);
    void delete(BO bo);
}
