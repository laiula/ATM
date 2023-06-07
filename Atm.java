import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Atm {
    private static double balance = 1000;
    private static List<Transaction> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("**** ATM Menu ****");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdrawal");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactionHistory) {
                System.out.println("Type: " + transaction.getType() + ", Amount: " + transaction.getAmount());
            }
        }
    }

    private static void performWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add(new Transaction("Withdrawal", amount));
                System.out.println("Amount withdrawn successfully.");
            } else {
                System.out.println("Insufficient balance. Withdrawal operation failed.");
            }
        } else {
            System.out.println("Invalid amount. Withdrawal operation failed.");
        }
    }

    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid amount. Deposit operation failed.");
        }
    }

    private static void performTransfer(Scanner scanner) {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add(new Transaction("Transfer (To Another Account)", amount));
                System.out.println("Amount transferred successfully.");
            } else {
                System.out.println("Insufficient balance. Transfer operation failed.");
            }
        } else {
            System.out.println("Invalid amount. Transfer operation failed.");
        }
    }
}
