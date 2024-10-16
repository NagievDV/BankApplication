import java.sql.Array;
import java.util.*;
import java.time.LocalDate;

class Account {
    private int id;
    private double balance;
    private static double annualInterestRate = 4.5;
    private LocalDate dateCreated;
    private String name;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Account(){
        this.id = 0;
        this.balance = 0;
        this.dateCreated = LocalDate.now();
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = LocalDate.now();
    }
    public Account(int id, double balance, String name) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = LocalDate.now();
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getdateCreated() {
        return dateCreated;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void setAnnualInterestRate(double rate) {
        annualInterestRate = rate;
    }

    public double getAnnualInterestRate(){
        return annualInterestRate;
    }

    public double calculateMonthlyInterest() {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        return balance * monthlyInterestRate;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("+", amount, balance, "Пополнение на сумму " + amount + " от " + LocalDate.now(), LocalDate.now()));
        } else {
            System.out.println("Сумма должна быть положительной.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("-", amount, balance, "Снятие денег на сумму " + amount + " от " + LocalDate.now(), LocalDate.now()));
        } else {
            System.out.println("Недостаточно средств или некорректная сумма.");
        }
    }
    public void printSummary() {
        System.out.println("Сводка по счету:");
        System.out.println("Владелец: " + name);
        System.out.println("ID: " + id);
        System.out.println("Текущий баланс: " + balance + " руб.");
        System.out.println("Годовая процентная ставка: " + annualInterestRate + "%");
        System.out.println("Транзакции:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

public class BankApplication {
    public static void main(String[] args) {
        Account account = new Account(1233, 1100, "Семен");
        Account.setAnnualInterestRate(7.5);

        account.deposit(500);
        account.deposit(600);
        account.deposit(700);

        account.withdraw(700);
        account.withdraw(600);
        account.withdraw(100);

        account.printSummary();
    }

    public static void mainMenu(Account account) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nГлавное меню:");
            System.out.println("1. Просмотр баланса");
            System.out.println("2. Снять деньги");
            System.out.println("3. Внести деньги");
            System.out.println("4. Рассчитать ежемесячные проценты");
            System.out.println("5. Показать дату создания счета");
            System.out.println("6. Сводка по аккаунту");
            System.out.println("7. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Текущий баланс: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Введите сумму для снятия: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Введите сумму для внесения: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    double interest = account.calculateMonthlyInterest();
                    System.out.println("Ежемесячные проценты: " + interest);
                    break;
                case 5:
                    System.out.println("Дата создания счета: " + account.getdateCreated());
                    break;
                case 6:
                    account.printSummary();;
                    return;
                case 7:
                    System.out.println("Выход из системы.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }
    public static Account findAccountById(List<Account> accounts, int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }
}
