package osk.sko.budgetManager;

import java.util.ArrayList;

public class SortAllMethod implements Analyzer{


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

        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (menus.get(i - 1).getPrice() < menus.get(i).getPrice()) {
                    swap(menus, i - 1, i);
                    swapped = true;
                }
            }
        }

        System.out.println("\nAll:");
        for (Menu menu : menus) {
            System.out.printf("%s $%.2f\n",menu.getName(),menu.getPrice());
            sum += menu.getPrice();
        }
        System.out.printf("Total: $%.2f\n", sum);
    }
}
