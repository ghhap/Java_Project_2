package cars;
/** Двигун автомобіля.
     * @param volume об'єм у літрах */
public record Engine(double volume) {
    /** Перевіряє двигун.
     * @return стан двигуна */
    public String checkEngine() { return volume > 0 ? "Двигун справний" : "Невідомий об'єм"; }
}
