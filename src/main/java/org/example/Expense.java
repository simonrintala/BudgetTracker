package org.example;

public class Expense extends Transaction{
    private EExpenseCategory category;


    public Expense(double amount, String date, EExpenseCategory category){
        super(amount, date);
        this.category = category;
    }


    //konstruktor på hur man får skapa en utgift

//    public EExpenseCategory getCategory() {
//        return category;
//    }

//    @Override
//    public String toString() {
//        return "Expense{" +
//                "category=" + category +
//                "} " + super.toString();
//    }
}
