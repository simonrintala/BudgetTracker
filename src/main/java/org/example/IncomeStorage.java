package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class IncomeStorage {
    private HashMap<Integer, HashMap<Integer, Income>> userIncome = new HashMap<>();



    public void loadFile(String filename) {

        File file = new File(filename);
        System.out.println("Reading income data from " + filename);
        if (file.length() == 0) {
            System.out.println("File is empty");
            return;
        }

        try (Reader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type type = new TypeToken<HashMap<Integer, HashMap<Integer, Income>>>() {
            }.getType();
            userIncome = gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Could not load income from file. " + e.getMessage());
        }  /*catch (JsonSyntaxException e) {
            System.out.println("JSON syntax error:  " + e.getMessage());
        }   */
    }

    public void saveFile(String filename) {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userIncome, writer);
        } catch (Exception e) {
            System.out.println("Could not save income to file. " + e.getMessage());
        }
    }

    public void addIncome(int userID, Income income) {
        userIncome.putIfAbsent(userID, new HashMap<>());
        userIncome.get(userID).put(income.hashCode(), income);

    }

    public void removeIncome(int userID, int incomeID) {
        HashMap<Integer, Income> income1 = userIncome.get(userID);
        if (income1 != null) {
            income1.remove(incomeID);
        }
    }

        public void listIncome ( int userID){
            HashMap<Integer, Income> income = userIncome.get(userID);
            if (income != null) {
                for (Integer id : income.keySet()) {
                    Income incomePrintList = income.get(id);
                    System.out.println("ID: "+id + "\nAmount: " + incomePrintList.getAmount() + "\n------------");
                }
            } else {
                System.out.println("No income found for userID " + userID);
            }
        }

        public Income getIncome(int userID, int incomeID) {
        HashMap<Integer, Income> income1 = userIncome.get(userID);
        return income1 != null ? income1.get(incomeID) : null;
        }

        public void editIncome(int userID, Income editIncome) {
            HashMap<Integer, Income> income1 = userIncome.get(userID);
            if (income1 != null && income1.containsKey(editIncome.hashCode())) {
                income1.put(editIncome.hashCode(), editIncome);
            }
        }

        public double totalBudget(int userID) {
        double budget = 0;
        HashMap<Integer, Income> income1 = userIncome.get(userID);
        if (income1 != null) {
            for ( Income income : income1.values()) {
                budget += income.getAmount();
            }
        }
        return budget;
        }
    }
    /*incomestorage är inprincip likadan som expensestorage*/
