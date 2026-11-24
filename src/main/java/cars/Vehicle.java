package cars;
/** Абстрактний транспортний засіб. */
public abstract class Vehicle {
    private final String brand;
    private final int year;
    /** Ініціалізує транспортний засіб.
     * @param brand марка
     * @param year рік випуску */
    protected Vehicle(String brand, int year) { this.brand = brand; this.year = year; }
    /** Повертає марку.
     * @return марка */
    public String getBrand() { return brand; }
    /** Повертає рік.
     * @return рік випуску */
    public int getYear() { return year; }
    /** Запускає транспортний засіб.
     * @return повідомлення про запуск */
    public abstract String start();
}
