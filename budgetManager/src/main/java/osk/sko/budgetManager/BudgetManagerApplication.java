package osk.sko.budgetManager;


import java.util.Locale;

public class BudgetManagerApplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		MenuService menuService = new MenuService();
		menuService.startMenu();
	}

}
