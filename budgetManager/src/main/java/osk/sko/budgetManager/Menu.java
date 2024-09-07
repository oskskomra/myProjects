package osk.sko.budgetManager;



public class Menu {


    private String name;
    private double price;
    private double balance;
    private Category category;

    public Menu(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Menu(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
