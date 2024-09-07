package osk.sko.budgetManager;

import java.util.ArrayList;
import java.util.Scanner;

public class SortCertainTypeMethod implements Analyzer {

    @Override
    public void swap(ArrayList<Menu> menus, int i, int j) {
        Menu temp = menus.get(i);
        menus.set(i, menus.get(j));
        menus.set(j, temp);
    }

    @Override
    public void analyze(ArrayList<Menu> menus) {
        Scanner sc = new Scanner(System.in);
        String choice = "";
        int userChoice = -1;

        while (userChoice != 4) {

            System.out.println("\nChoose the type of purchase" +
                    "\n1) Food" +
                    "\n2) Clothes" +
                    "\n3) Entertainment" +
                    "\n4) Other");

            userChoice = sc.nextInt();

            if (menus.isEmpty()) {
                System.out.println("\nThe purchase list is empty!");
                break;
            }

            switch (userChoice) {
                case 1 -> choice = "Food";
                case 2 -> choice = "Clothes";
                case 3 -> choice = "Entertainment";
                case 4 -> choice = "Other";
            }
            ArrayList<Menu> menulist = new ArrayList<>();

            for (Menu menu : menus) {
                if (menu.getCategory().getName().equals(choice)){
                    menulist.add(menu);
                }
            }

            int n = menulist.size();
            double sum = 0;

            boolean swapped = true;

            while (swapped) {
                swapped = false;
                for (int i = 1; i < n; i++) {
                    if (menulist.get(i - 1).getPrice() < menulist.get(i).getPrice()) {
                        swap(menulist, i - 1, i);
                        swapped = true;
                    }
                }
            }

            System.out.println("\n" + choice + ":");
            for (Menu menu : menulist) {
                System.out.printf("%s $%.2f\n",menu.getName(),menu.getPrice());
                sum += menu.getPrice();
            }
            System.out.printf("Total: $%.2f\n", sum);
            break;
        }
    }

}
