package cars;
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;
/** Операції над масивом автомобілів, рядками та файлами (ЛР 8–10). */
public class CarManager {
    private Car[] cars = {};
    /** Створює менеджер із початковими даними. */
    public CarManager() { cars = samples(); }
    /** Формує тестові дані.
     * @return десять демонстраційних автомобілів */
    public static Car[] samples() { return new Car[]{new Car("Toyota","Corolla",2020,18000,1.8),new Car("BMW","X5",2022,52000,3),new Car("Audi","A4",2019,23000,2),new Car("Tesla","Model 3",2023,35000,0),new Car("Honda","Civic",2021,21000,1.5),new Car("Ford","Focus",2018,12000,1.6),new Car("Skoda","Octavia",2020,17000,1.4),new Car("Volkswagen","Golf",2019,16000,1.6),new Car("Hyundai","Tucson",2022,28000,2),new Car("Mazda","CX-5",2021,26000,2.5)}; }
    /** Повертає поточні дані.
     * @return копія масиву */
    public Car[] all() { return cars.clone(); }
    /** Замінює елемент масиву.
     * @param index індекс
     * @param car новий автомобіль */
    public void edit(int index, Car car) { cars[index] = car; }
    /** Розширює масив.
     * @param car новий автомобіль */
    public void add(Car car) { cars = Arrays.copyOf(cars,cars.length+1); cars[cars.length-1]=car; }
    /** Сортує за ціною.
     * @param descending спадний порядок
     * @return відсортована копія */
    public Car[] sortPrice(boolean descending) { return sorted(Comparator.comparingDouble(Car::getPrice),descending); }
    /** Сортує за маркою.
     * @param descending спадний порядок
     * @return відсортована копія */
    public Car[] sortBrand(boolean descending) { return sorted(Comparator.comparing(Car::getBrand,String.CASE_INSENSITIVE_ORDER).thenComparing(Car::getModel),descending); }
    private Car[] sorted(Comparator<Car> c, boolean desc) { Car[] copy=all(); Arrays.sort(copy,desc?c.reversed():c); return copy; }
    /** Фільтрує за ціною.
     * @param price нижня межа
     * @return автомобілі дорожчі за межу */
    public Car[] filterPrice(double price) { return Arrays.stream(cars).filter(c->c.getPrice()>price).toArray(Car[]::new); }
    /** Шукає за регулярним виразом.
     * @param regex регулярний вираз
     * @return відповідні автомобілі */
    public Car[] filterRegex(String regex) { Pattern p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE); return Arrays.stream(cars).filter(c->p.matcher(c.getBrand()+" "+c.getModel()).find()).toArray(Car[]::new); }
    /** Визначає крайні значення ціни.
     * @return автомобілі з мінімальною та максимальною ціною, включно з рівними значеннями */
    public Car[] minMax() { if(cars.length==0)return all(); Car[] s=sortPrice(false); double min=s[0].getPrice(),max=s[s.length-1].getPrice(); return Arrays.stream(cars).filter(c->c.getPrice()==min||c.getPrice()==max).toArray(Car[]::new); }
    /** Завантажує текстовий файл, поля розділено крапкою з комою.
     *
     * @param path шлях до файлу
     *
     * @throws IOException помилка читання або формату
     */
    public void loadFromText(Path path) throws IOException { ArrayList<Car> list=new ArrayList<>(); int n=0; for(String line:Files.readAllLines(path)){ n++; if(line.isBlank())continue; try{String[] p=line.split(";",-1); if(p.length!=5)throw new IllegalArgumentException(); list.add(new Car(p[0].trim(),p[1].trim(),Integer.parseInt(p[2].trim()),Double.parseDouble(p[3].trim()),Double.parseDouble(p[4].trim())));}catch(RuntimeException e){throw new IOException("Помилка у рядку "+n,e);} } cars=list.toArray(Car[]::new); }
    /** Записує відсортовані за ціною дані у двійковий файл.
     *
     * @param path шлях
     *
     * @throws IOException помилка запису
     */
    public void saveSortedToBinary(Path path) throws IOException { try(DataOutputStream out=new DataOutputStream(Files.newOutputStream(path))){out.writeInt(cars.length); for(Car c:sortPrice(false)){out.writeUTF(c.getBrand());out.writeUTF(c.getModel());out.writeInt(c.getYear());out.writeDouble(c.getPrice());out.writeDouble(c.getEngine().volume());}} }
    /** Читає двійковий файл.
     *
     * @param path шлях
     *
     * @return масив автомобілів
     *
     * @throws IOException помилка читання
     */
    public Car[] showFromBinary(Path path) throws IOException { try(DataInputStream in=new DataInputStream(Files.newInputStream(path))){int n=in.readInt();if(n<0||n>1000000)throw new IOException("Некоректна кількість записів");Car[] result=new Car[n];for(int i=0;i<n;i++)result[i]=new Car(in.readUTF(),in.readUTF(),in.readInt(),in.readDouble(),in.readDouble());return result;} }
}
