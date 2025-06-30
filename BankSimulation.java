import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created for: " + accountHolder);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void printBalance() {
        System.out.println("Current balance: ₹" + balance);
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account myAccount = new Account("Ritik");

        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Show History\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    myAccount.deposit(deposit);
                }
                case 2 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawal = scanner.nextDouble();
                    myAccount.withdraw(withdrawal);
                }
                case 3 -> myAccount.printBalance();
                case 4 -> myAccount.showTransactionHistory();
                case 5 -> {
                    exit = true;
                    System.out.println("Exiting. Thank you!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
