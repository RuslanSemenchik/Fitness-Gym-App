package model;

import java.time.LocalDate;

public class FinanceRecord {
    private long id;
    private TypeRecord typeRecord;
    private double amount;
    private String description;
    private LocalDate date;

    public FinanceRecord(long id, TypeRecord typeRecord, double amount, String description, LocalDate date) {
        this.id = id;
        this.typeRecord = typeRecord;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public TypeRecord getTypeRecord() {
        return typeRecord;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "\n"+"FinanceRecord{" +
                "id='" + id + '\'' +
                ", typeRecord='" + typeRecord + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
