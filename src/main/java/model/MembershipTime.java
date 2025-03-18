package model;


import java.time.LocalTime;

public enum MembershipTime {

    DAY (40,LocalTime.of(7,0),LocalTime.of(13,59)),// стоимость абонемента в месяц: зарплата в евро за урок
    EVENING (45,LocalTime.of(14,0),LocalTime.of(21,59)),
    UNLIMITED(50,LocalTime.of(7,0),LocalTime.of(21,59));


    private double price;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    MembershipTime(double price, LocalTime timeStart, LocalTime timeEnd) {
        this.price = price;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    public LocalTime getTimeStart() {
        return timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public double getPrice() {
        return price;
    }

    }
