import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Account {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        logTransaction("Account Created", initialBalance);
    }

    private void logTransaction(String type, double amount) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String entry = String.format("[%s] %s: $%.2f. New Balance: $%.2f", 
                                     dtf.format(now), type, amount, this.balance);
        this.transactionHistory.add(entry);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            logTransaction("Deposit", amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            logTransaction("Withdrawal", amount);
            System.out.println("Withdrawn: $" + amount);
        } else {
            logTransaction("Failed Withdrawal", amount);
            System.out.println("Error: Insufficient funds. Current Balance: $" + this.balance);
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void printHistory() {
        System.out.println("\n--- Transaction History for Account " + this.accountNumber + " ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("----------------------------------------------");
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        System.out.println("--- Starting Bank Account Simulation ---");
        
        Account myAccount = new Account("123456789", 500.00);

        System.out.println("Current Balance: $" + myAccount.getBalance());

        myAccount.deposit(150.75);
        System.out.println("New Balance: $" + myAccount.getBalance());

        myAccount.withdraw(200.00);
        System.out.println("New Balance: $" + myAccount.getBalance());
        
        myAccount.withdraw(1000.00); 
        System.out.println("New Balance: $" + myAccount.getBalance());

        myAccount.printHistory();
    }
}
