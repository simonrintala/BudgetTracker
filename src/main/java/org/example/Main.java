package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

/*IntelliJ "hälp/föreslagna" kod hjälpte mig mycket i denna uppgiften. speciellt i meny bygget som dock är väldigt
* likt föregående uppgift. Den var även till en väldigt bra hjälp när jag använde hashmaps (& nested) då jag inte
* är bra på det alls och behövde ganska mycket hjälp och förslag på hur man kan/ska skriva ut metoderna
* i expenseStorage/incomeStorage. */


public class Main {
    private static ExpenseStorage expenseStorage = new ExpenseStorage();
    private static IncomeStorage incomeStorage = new IncomeStorage();
    //Fungerade endast som static?


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        expenseStorage.loadFile("expense.json");
        incomeStorage.loadFile("income.json");

        boolean run = true;
        while(run){
            System.out.println("-----------------------------");
            System.out.println("Welcome to your Budget Tracker");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expens");
            System.out.println("3. List Income");
            System.out.println("4. List Expense");
            System.out.println("5. Remove Income");
            System.out.println("6. Remove Expense");
            System.out.println("7. Edit Income");
            System.out.println("8. Edit Expense");
            System.out.println("9. Show current budget");
            System.out.println("0. Exit" + "\n--------------------");
            System.out.println("Please enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    try {
                        System.out.print("Enter amount: ");
                        double incomeAmount = scanner.nextDouble();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String incomeDate = scanner.next();
                        System.out.print("Enter category (SALARY, OTHER): ");
                        EIncomeCategory incomeCategory = EIncomeCategory.valueOf(scanner.next().toUpperCase());
                        Income income = new Income(incomeAmount, incomeDate, incomeCategory);
                        incomeStorage.addIncome(0, income);
                        incomeStorage.saveFile("income.json");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Try again.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input. Try again.");
                    }
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double expenseAmount = scanner.nextDouble();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String expenseDate = scanner.next();
                    System.out.print("Enter category (FOOD, BILLS, PERSONAL): ");
                    EExpenseCategory expenseCategory = EExpenseCategory.valueOf(scanner.next().toUpperCase());
                    Expense expense = new Expense(expenseAmount, expenseDate, expenseCategory);
                    expenseStorage.addExpense(0, expense);
                    incomeStorage.saveFile("expense.json");
                    scanner.nextLine();
                    break;
                case 3:
                    incomeStorage.listIncome(0);
                    break;
                case 4:
                    expenseStorage.listExpense(0);
                    break;
                case 5:
                    System.out.println("Enter Income ID to remove a specific income: ");
                    int incomeID = scanner.nextInt();
                    incomeStorage.removeIncome(0, incomeID);
                    System.out.println("Income with the Income ID: " +incomeID + " has been removed from budget.");
                    incomeStorage.saveFile("income.json");
                    break;
                case 6:
                    System.out.println("Enter Expense ID to remove a specific expense: ");
                    int expenseID = scanner.nextInt();
                    expenseStorage.removeExpense(0, expenseID);
                    System.out.println("Expense with the Expense ID: " +expenseID + " has been removed from budget.");
                    expenseStorage.saveFile("expense.json");
                    break;
                case 7:
                    System.out.println("Enter Income ID to edit the amount of a specific income: ");
                    int editIncomeID = scanner.nextInt();
                    Income availableIncome = incomeStorage.getIncome(0, editIncomeID);
                    if (availableIncome != null) {
                        System.out.println("Enter the new amount:  (" + availableIncome.getAmount() + " is the current amount)");
                        double newAmount = scanner.nextDouble();
                        //incomeStorage.editIncome(0, availableIncome);
                        if (newAmount != 0){
                            availableIncome.setAmount(newAmount);
                        }
                        System.out.println("Income has been updated with the new amount of " + newAmount);
                        incomeStorage.saveFile("income.json");
                    }else {
                        System.out.println("Income with the Income ID: " + editIncomeID + " does not exist.");
                    }
                    break;
                case 8:
                    System.out.println("Enter Expense ID to edit the amount of a specific expense: ");
                    int editExpenseID = scanner.nextInt();
                    Expense availableExpense = expenseStorage.getExpense(0, editExpenseID);
                    if (availableExpense != null) {
                        System.out.println("Enter the new amount:  (" + availableExpense.getAmount() + " is the current amount)");
                        double newAmount = scanner.nextDouble();
                        //expenseStorage.editExpense(0, availableExpense);
                        if (newAmount != 0){
                            availableExpense.setAmount(newAmount);
                        }
                        System.out.println("Expense has been updated with the new amount of " + newAmount);

                        expenseStorage.saveFile("expense.json");
                    } else {
                        System.out.println("Expense with the Expense ID: " + editExpenseID + " does not exist.");
                    }
                    break;
                case 9:
                    double incomeAmountBudget = incomeStorage.totalBudget(0);
                    double expenseAmountBudget = expenseStorage.totalBudget(0);
                    double budget = incomeAmountBudget - expenseAmountBudget;
                    System.out.println("Your total income is: " + incomeAmountBudget);
                    System.out.println("Your total expense is: " + expenseAmountBudget);
                    System.out.println("Your total budget is: " + budget);
                    break;
                case 0:
                    run = false;
                    break;
                    default:
                        System.out.println("Invalid choice, try again");
            }

        }
        expenseStorage.saveFile("expense.json");
        incomeStorage.saveFile("income.json");
        scanner.close();




//hårdkodning från innan jag hade min meny.
//        ExpenseStorage expenseStorage = new ExpenseStorage();
//        IncomeStorage incomeStorage = new IncomeStorage();
//
//        User user = new User("Simon", "Rintala");
//
//        expenseStorage.loadFile("expense.json");
//        incomeStorage.loadFile("income.json");
//
//        Expense expense = new Expense(1399.0, "2024-10-22", EExpenseCategory.PERSONAL);
//        expenseStorage.addExpense(user.getUserID(), expense);
//        expenseStorage.saveFile("expense.json");
//
//
//        expenseStorage.listExpense(user.getUserID());
//
//        Income income = new Income(812.0, "2024-10-19", EIncomeCategory.OTHER);
//        incomeStorage.addIncome(user.getUserID(), income);
//        incomeStorage.saveFile("income.json");
//
//        incomeStorage.listIncome(user.getUserID());

    }
}