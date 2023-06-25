import java.util.Scanner;

// Transaction class to represent individual transactions
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

// Account class to represent a user account
class Account {
    private String userId;
    private String pin;
    private double balance;
    private Transaction[] transactionHistory;
    private int transactionCount;

    public Account(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new Transaction[100]; // Limiting transaction history to 100
        this.transactionCount = 0;
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit Money", amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            addTransaction("Withdraw Money", amount);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            addTransaction("Transfer Money", amount);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void addTransaction(String type, double amount) {
        if (transactionCount < 100) {
            Transaction transaction = new Transaction(type, amount);
            transactionHistory[transactionCount] = transaction;
            transactionCount++;
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (int i = 0; i < transactionCount; i++) {
            Transaction transaction = transactionHistory[i];
            System.out.println("Type: " + transaction.getType() + ", Amount: " + transaction.getAmount());
        }
    }
}

// ATM class to handle user interactions and ATM operations
class ATM {
    private Account[] accounts;
    private int accountCount;

    public ATM() {
        this.accounts = new Account[100]; // Limiting accounts to 100
        this.accountCount = 0;
    }

    public void createAccount(String userId, String pin) {
        Account account = new Account(userId, pin);
        accounts[accountCount] = account;
        accountCount++;
    }

    public Account validateUser(String userId, String pin) {
        for (int i = 0; i < accountCount; i++) {
            Account account = accounts[i];
            if (account.getUserId().equals(userId) && account.validatePin(pin)) {
                return account;
            }
        }
        return null;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        Account account = validateUser(userId, pin);
        if (account != null) {
            System.out.println("Login Successful!");

            while (true) {
                System.out.println("\n----- ATM Menu -----");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Deposit Money");
                System.out.println("4. Transfer Money");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                double amount;

                switch (choice) {
                    case 1:
                        account.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: ");
                        amount = scanner.nextDouble();
                        account.withdraw(amount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: ");
                        amount = scanner.nextDouble();
                        account.deposit(amount);
                        break;
                    case 4:
                        System.out.print("Enter the recipient's User ID: ");
                        String recipientId = scanner.next();
                        Account recipient = validateUser(recipientId, "");
                        if (recipient != null) {
                            System.out.print("Enter the amount to transfer: ");
                            amount = scanner.nextDouble();
                            account.transfer(recipient, amount);
                        } else {
                            System.out.println("Recipient User ID not found!");
                        }
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN!");
        }
    }
}

// Main class to start the ATM system
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.createAccount("user1", "1234");
        atm.createAccount("user2", "5678");
        atm.start();
    }
}
