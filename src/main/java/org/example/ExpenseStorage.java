package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ExpenseStorage {
    private HashMap<Integer, HashMap<Integer, Expense>> userExpenses = new HashMap<>();
/*använde mig av "nested" hashmap, när jag försökte lista ut hur jag skulle länka/lagra samman en int med t.ex ID fast i en
annan hashmap, kollade mycket på den här sidan, https://www.baeldung.com/java-nested-hashmaps och även på stackoverflow
när det kom till errors jag fick när jag skapa de och hur man "ska" använda de.

Använde mig en mycket utav enhanced for loop efter att du visat det på kod genomgångar och tycker det ser snyggt
ut och fungerar bra. Tycker det är lite svårt att få de att fungera ibland och jobbigare att läsa koden för mig i alla fall

Använde mig en del av ternary operator också efter jag kollat igen på videos på Klaar och det räddade många timmar
utav tänkande för innan blev min kod väldigt lång för samma uppgift som ternary gjorde.
*/


    public void loadFile(String filename) {

        File file = new File(filename);
        System.out.println("Reading expense data from "+ filename);
        if (file.length() == 0){
            System.out.println("File is empty");
            return;
        }
        //Nästan exakt tagit från ditt exempel gson_typetoken på github
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type type = new TypeToken<HashMap<Integer, HashMap<Integer, Expense>>>(){}.getType();
            userExpenses = gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Could not load expenses from file. " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("JSON syntax error: " + e.getMessage());
        }
    } /*Laddar filer från JSON, detta var mer användbart när jag hårdkodade min meny men nu syns det inte
    detta är bara så JSON filen laddas när programmet startas*/
    public void saveFile(String filename) {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userExpenses, writer);
        } catch (IOException e) {
            System.out.println("Could not save expenses to file" + e.getMessage());
        }
    } /*sparar min data från konsol programmet in till JSON fil */
    public void addExpense(int userID, Expense expense) {
        userExpenses.putIfAbsent(userID, new HashMap<>());
        userExpenses.get(userID).put(expense.hashCode(), expense);
    } /*skapar/lägger en ny utgift & id i en HashMap för denna transaktionen */

    public void removeExpense(int userID, int expenseID) {
        HashMap<Integer, Expense> expense1 = userExpenses.get(userID);
        if (expense1 != null) {
            expense1.remove(expenseID);
        }
    } /*ta bort en existerade utgift ifrån HashMapen m.h.a expenseID */

    public void listExpense(int userID) {
        HashMap<Integer, Expense> expenses = userExpenses.get(userID);
        if (expenses != null) {
            for (Integer id : expenses.keySet()) {
                Expense expensePrintList = expenses.get(id);
                System.out.println("ID: "+id + "\nAmount: "+ expensePrintList.getAmount() +"\n------------" );
            }
        }else {
            System.out.println("No expenses found");
        }
    }/*denna listar upp alla utgifter som ligger i hashmapen och visar upp information kring dessa utgifter
    såsom ID för transaktion, ENUM kategori, summa och datum*/

    public Expense getExpense(int userID, int expenseID) {
        HashMap<Integer, Expense> expense1 = userExpenses.get(userID);
        return expense1 != null ? expense1.get(expenseID) : null;
    }

    public void editExpense(int userID, Expense editExpense) {
        HashMap<Integer, Expense> expense1 = userExpenses.get(userID);
        if (expense1 != null && expense1.containsKey(editExpense.hashCode())) {
            expense1.put(editExpense.hashCode(), editExpense);
        }
    }/*ändra utgift / skriv över nuvarande värde. Denna hade jag jättemycket problem med då jag aldrig fick den
    att fungera. vet inte om det var tack vare "fel" i denna metoden eller om det var att jag skrivit fel i menyn
    på editexpense i min switch i Main.*/

    public double totalBudget(int userID) {
        double budget = 0;
        HashMap<Integer, Expense> expense1 = userExpenses.get(userID);
        if (expense1 != null) {
            for (Expense expense : expense1.values()) {
                budget += expense.getAmount();
            }
        }
        return budget;
    } /* skapa en variabel för budget och lägg till värdet av transaktionerna till denna*/
}
