import java.time.LocalDate;

class Transaction {
    private String type;
    private double amount;
    private LocalDate date;
    private double balance;
    private String description;

    public Transaction(String type, double amount, double balance, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return type + ": " + amount + " руб.";
    }
}
