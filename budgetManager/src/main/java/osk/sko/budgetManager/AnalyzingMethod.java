package osk.sko.budgetManager;

import java.util.ArrayList;

public class AnalyzingMethod {

    private Analyzer analyzeMethod;

    public void setAnalyzingMethod(Analyzer analyzeMethod) {
        this.analyzeMethod = analyzeMethod;
    }

    public void analyze(ArrayList<Menu> menus) {
        this.analyzeMethod.analyze(menus);
    }


}
