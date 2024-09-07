package osk.sko.budgetManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuService {

    private ArrayList<Menu> menus = new ArrayList<>();
    private Menu menu = new Menu(0);
    private Category userCategory;
    Scanner sc = new Scanner(System.in);
    AnalyzingMethod analyze = new AnalyzingMethod();


    public void startMenu(){
        int userChoice = -1;

        while(userChoice != 0){
            System.out.println("\nChoose your action:" +
                    "\n1) Add income" +
                    "\n2) Add purchase" +
                    "\n3) Show list of purchases" +
                    "\n4) Balance" +
                    "\n5) Save" +
                    "\n6) Load" +
                    "\n7) Analyze (Sort)" +
                    "\n0) Exit");

            userChoice = sc.nextInt();

            switch(userChoice){
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    showPurchaseList();
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                    savePurchaseList();
                    break;
                case 6:
                    loadPurchaseList();
                    break;
                case 7:
                    sortPurchaseList();
                    break;
                case 0:
                    System.out.print("\nBye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }


    public void addIncome() {
        System.out.println("\nEnter income:");
        double balance = sc.nextDouble();
        menu.setBalance(balance);
        System.out.println("Income was added!");
    }

    public void addPurchase() {
        int userChoice = -1;

        while (userChoice != 5) {
            System.out.println("\nChoose the type of purchase" +
                    "\n1) Food" +
                    "\n2) Clothes" +
                    "\n3) Entertainment" +
                    "\n4) Other" +
                    "\n5) Back");

            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    menu.setCategory(Category.FOOD);
                    break;
                case 2:
                    menu.setCategory(Category.CLOTHES);
                    break;
                case 3:
                    menu.setCategory(Category.ENTERTAINMENT);
                    break;
                case 4:
                    menu.setCategory(Category.OTHER);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            if(userChoice == 5){
                break;
            }


            System.out.println("\nEnter purchase name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.println("Enter its price: ");
            double price = sc.nextDouble();

            menus.add(new Menu(name, price, menu.getCategory()));
            menu.setBalance(menu.getBalance() - price);

            System.out.println("Purchase was added!");
        }
    }

    public void showPurchaseList(){

        int userChoice = -1;


        while (userChoice != 6) {
            if (menus.isEmpty()) {
                System.out.printf("\nThe purchase list is empty!\n");
                break;
            }

            System.out.println("\nChoose the type of purchases" +
                    "\n1) Food" +
                    "\n2) Clothes" +
                    "\n3) Entertainment" +
                    "\n4) Other" +
                    "\n5) All" +
                    "\n6) Back");

            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    userCategory = Category.FOOD;
                    break;
                case 2:
                    userCategory = Category.CLOTHES;
                    break;
                case 3:
                    userCategory = Category.ENTERTAINMENT;
                    break;
                case 4:
                    userCategory = Category.OTHER;
                    break;
                case 5:
                    userCategory = Category.ALL;
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            if(userChoice == 6){
                break;
            }


            double sum = 0;

            System.out.println("\n" + userCategory.getName() + ":");
            if (!containsCategory(userCategory) && userCategory != Category.ALL) {
                System.out.println("The purchase list is empty!");
            } else if (userCategory == Category.ALL) {
                for (Menu menu : menus) {
                    System.out.printf("%s $%.2f\n",menu.getName(), menu.getPrice());
                    sum += menu.getPrice();
                }
            } else {
                for (Menu menu : menus) {
                    if (menu.getCategory() == userCategory) {
                        System.out.printf("%s $%.2f\n",menu.getName(), menu.getPrice());
                        sum += menu.getPrice();
                    }
                }
            }
            if (sum > 0) {
                System.out.printf("Total sum: $%.2f\n", sum);
            }
        }
    }

    public void savePurchaseList(){
        File savedFile = new File("purchases.txt");

        try (PrintWriter writeToFile = new PrintWriter(savedFile)) {
            writeToFile.printf("Balance:%.2f\n", menu.getBalance());
            for (Menu menu : menus) {
                writeToFile.printf("%s,%s,%.2f\n",menu.getCategory().getName(), menu.getName(), menu.getPrice());
            }
            System.out.println("\nPurchases were saved!");
        } catch (IOException e) {
            System.out.println("File not saved");
        }
    }

    public void loadPurchaseList() {
        File savedFile = new File("purchases.txt");

        try (Scanner scanner = new Scanner(savedFile)){
            String firstLine = scanner.nextLine();
            int index = firstLine.indexOf(":");
            double balance = Double.parseDouble(firstLine.substring(index + 1));
            menu.setBalance(balance);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String category = parts[0].toUpperCase();
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                userCategory = Category.valueOf(category);

                menus.add(new Menu(name, price, userCategory));
            }
            System.out.println("\nPurchases were loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void sortPurchaseList() {
        int userChoice = -1;

        while (userChoice != 4) {

            System.out.println("\nHow do you want to sort?" +
                    "\n1) Sort all purchases" +
                    "\n2) Sort by type" +
                    "\n3) Sort certain type" +
                    "\n4) Back");

            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    if(menus.isEmpty()){
                        System.out.println("\nThe purchase list is empty!");
                    } else {
                        analyze.setAnalyzingMethod(new SortAllMethod());
                        analyze.analyze(menus);
                    }
                    break;
                case 2:
                    analyze.setAnalyzingMethod(new SortByTypeMethod());
                    analyze.analyze(menus);
                    break;
                case 3:
                    analyze.setAnalyzingMethod(new SortCertainTypeMethod());
                    analyze.analyze(menus);
                    break;
            }
        }
    }


    public void showBalance(){
        System.out.printf("\nBalance: $%.2f\n", menu.getBalance());
    }

    public boolean containsCategory(Category category){
        for(Menu menu : menus){
            if(menu.getCategory() == category){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }
}