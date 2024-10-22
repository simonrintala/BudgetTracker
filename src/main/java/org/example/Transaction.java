package org.example;

public class Transaction {
    private double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private String date;

    public Transaction(double amount, String date) {
        this.amount = amount;
        this.date = date;
    } //Konstruktor med vilkor för att skapa en transaktion

    public double getAmount() {
        return amount;
    }

//    public void setAmount(double amount) {
//        this.amount = amount;
//    }

//    public String getDate() {
//        return date;
//    }

//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }

//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "amount=" + amount +
//                ", date=" + date +
//                '}';
//    }
}
