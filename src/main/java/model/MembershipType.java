package model;

import java.time.LocalDate;


public enum MembershipType {
    MONTHLY(50,LocalDate.now(),LocalDate.now().plusMonths(1)),
    ANNUAL ( 420,LocalDate.now(),LocalDate.now().plusYears(1));

private double price;
private LocalDate dateStart;
private LocalDate dateEnd;


    MembershipType(double price, LocalDate dateStart, LocalDate dateEnd) {
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }
}




