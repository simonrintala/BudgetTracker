package org.example;

public class Income extends Transaction {
    private EIncomeCategory category;


    public Income(double amount, String date, EIncomeCategory category) {
        super(amount, date);
        this.category = category;
    }
    //konstruktor på hur man får skapa en inkomst

//    public EIncomeCategory getCategory() {
//        return category;
//    }

//    @Override
//    public String toString() {
//        return "Income{" +
//                "category=" + category +
//                "} " + super.toString();
//    }
}
