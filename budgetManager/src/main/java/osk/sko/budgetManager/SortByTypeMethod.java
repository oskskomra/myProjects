package osk.sko.budgetManager;

import java.util.ArrayList;

public class SortByTypeMethod implements Analyzer {

    @Override
    public void swap(ArrayList<Menu> menus, int i, int j) {
        Menu temp = menus.get(i);
        menus.set(i, menus.get(j));
        menus.set(j, temp);
    }

    @Override
    public void analyze(ArrayList<Menu> menus) {
        int n = menus.size();
        double sum = 0;
        double foodSum = 0;
        double entertainmentSum = 0;
        double clothesSum = 0;
        double otherSum = 0;

        for (Menu menu : menus) {
            switch (menu.getCategory()){
                case FOOD -> foodSum += menu.getPrice();
                case ENTERTAINMENT -> entertainmentSum += menu.getPrice();
                case CLOTHES -> clothesSum += menu.getPrice();
                case OTHER -> otherSum += menu.getPrice();
            }
            sum += menu.getPrice();
        }

        System.out.printf("""

                Food - $%.2f
                Entertainment - $%.2f
                Clothes - $%.2f
                Other - $%.2f
                """, foodSum, entertainmentSum, clothesSum, otherSum);
    }


}
