package cars;
/** Автомобіль із маркою, моделлю, роком, ціною та двигуном. */
public final class Car extends Vehicle implements Serviceable {
    private final String model;
    private final double price;
    private final Engine engine;
    /** Створює автомобіль.
     *
     * @param brand марка
     *
     * @param model модель
     *
     * @param year рік
     *
     * @param price ціна
     *
     * @param volume об'єм двигуна (0 — невідомий)
     */
    public Car(String brand, String model, int year, double price, double volume) {
        super(brand, year);
        if (brand.isBlank() || model.isBlank() || year < 1886 || year > 2100 || !Double.isFinite(price) || price < 0 || !Double.isFinite(volume) || volume < 0) throw new IllegalArgumentException("Некоректні дані автомобіля");
        this.model = model; this.price = price; this.engine = new Engine(volume);
    }
    /** Повертає модель.
     * @return модель */
    public String getModel() { return model; }
    /** Повертає ціну.
     * @return ціна */
    public double getPrice() { return price; }
    /** Повертає двигун.
     * @return двигун */
    public Engine getEngine() { return engine; }
    /** {@inheritDoc} */
    public String start() { return getBrand() + " " + model + " запущено"; }
    /** {@inheritDoc} */
    public String service() { return engine.checkEngine(); }
}
