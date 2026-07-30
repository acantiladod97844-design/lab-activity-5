// Name: Dennis, Acantilado
// LAB ACTIVITY 5 – Exception Handling & Debugging
// Program: Mini ATM (Command-Line Interface)

import java.util.Scanner;

public class MiniATM {

    static double balance = 1000.00;
    static Scanner input = new Scanner(System.in);

    static void main() {

        System.out.println("====================================");
        System.out.println("       WELCOME TO THE MINI ATM");
        System.out.println("====================================");

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("[1] Deposit");
            System.out.println("[2] Withdraw");
            System.out.println("[3] Check balance");
            System.out.println("[4] Exit");
            System.out.print("Enter your choice: ");

            String choice = input.nextLine().trim();

            switch (choice) {

                case "1":
                    deposit();
                    break;

                case "2":
                    withdraw();
                    break;

                case "3":
                    checkBalance();
                    break;

                case "4":
                    running = false;
                    System.out.println("Thank you for using the Mini ATM. Goodbye!");
                    break;

                default:
                    System.out.println("[!] Please choose a number from 1 to 4.");
            }
        }
    }

    // ---------------- Deposit ----------------
    static void deposit() {

        System.out.print("Enter amount to deposit: ");
        String line = input.nextLine();

        try {

            double amount = Double.parseDouble(line);

            validateAmount(amount);

            balance += amount;

            System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n",
                    amount, balance);

        } catch (NumberFormatException | InvalidAmountException e) {

            System.out.println("[!] " + e.getMessage());

        } finally {

            System.out.println("-- transaction finished --");
        }
    }

    // ---------------- Withdraw ----------------
    static void withdraw() {

        System.out.print("Enter amount to withdraw: ");
        String line = input.nextLine();

        try {

            double amount = Double.parseDouble(line);

            validateAmount(amount);

            checkBalanceEnough(amount);

            balance -= amount;

            System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n",
                    amount, balance);

        } catch (NumberFormatException | InvalidAmountException e) {

            System.out.println("[!] " + e.getMessage());

        } catch (InsufficientFundsException e) {

            System.out.printf("[!] %s You are short by PHP %.2f%n",
                    e.getMessage(), e.getShortfall());

        } finally {

            System.out.println("-- transaction finished --");
        }
    }

    // ---------------- Balance ----------------
    static void checkBalance() {

        System.out.printf("Current Balance: PHP %.2f%n", balance);
    }

    // uses throws
    static void validateAmount(double amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive.");
        }
    }

    // uses throws
    static void checkBalanceEnough(double amount)
            throws InsufficientFundsException {

        if (amount > balance) {

            double shortfall = amount - balance;

            throw new InsufficientFundsException(
                    "Insufficient funds.",
                    shortfall);
        }
    }
}

//==================== Custom Exceptions ====================

class InvalidAmountException extends Exception {

    public InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {

    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}