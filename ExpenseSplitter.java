import java.util.*;

class Member {
String name;
double balance;

Member(String name, double balance) {  
    this.name = name;  
    this.balance = balance;  
}

}

public class ExpenseSplitter {

public static void main(String[] args) {  

    Scanner sc = new Scanner(System.in);  

    System.out.print("Enter number of members: ");  
    int n = sc.nextInt();  

    String[] names = new String[n];  
    double[] paid = new double[n];  
    double total = 0;  

    // Input  
    for (int i = 0; i < n; i++) {  
        System.out.print("\nEnter name of member " + (i + 1) + ": ");  
        names[i] = sc.next();  

        System.out.print("Enter amount paid by " + names[i] + ": ");  
        paid[i] = sc.nextDouble();  

        total += paid[i];  
    }  

    double share = total / n;  
    System.out.println("\nTotal Expense: " + total);  
    System.out.println("Each person should pay: " + share);  

    List<Member> creditors = new ArrayList<>();  
    List<Member> debtors = new ArrayList<>();  

    // Balance calculation  
    for (int i = 0; i < n; i++) {  
        double balance = paid[i] - share;  

        if (balance > 0) {  
            creditors.add(new Member(names[i], balance));  
        } else if (balance < 0) {  
            debtors.add(new Member(names[i], -balance));  
        }  
    }  

    // Settlement  
    System.out.println("\n--- Settlement ---");  
    int i = 0, j = 0;  

    while (i < debtors.size() && j < creditors.size()) {  

        Member debtor = debtors.get(i);  
        Member creditor = creditors.get(j);  

        double amount = Math.min(debtor.balance, creditor.balance);  

        System.out.println(debtor.name + " pays Ruppes " + amount + " to " + creditor.name);  

        debtor.balance -= amount;  
        creditor.balance -= amount;  

        if (debtor.balance == 0) i++;  
        if (creditor.balance == 0) j++;  
    }  

    sc.close();  
}

}
