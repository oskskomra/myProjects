package osk.sko.budgetManager;

import java.util.ArrayList;

public interface Analyzer {

    void swap(ArrayList<Menu> menus, int i, int j);

    void analyze(ArrayList<Menu> menus);
}
