package cars;
import java.sql.*;
import java.util.*;
/** Операції JDBC з базою SQLite (ЛР 11). */
public class CarDatabase {
    /** Рядок таблиці автомобілів.
     *
     * @param id ідентифікатор
     *
     * @param car автомобіль
     */
    public record Row(int id, Car car) {}
    private final String url;
    /** Створює таблицю за необхідності.
     *
     * @param file шлях до бази
     *
     * @throws SQLException помилка БД
     */
    public CarDatabase(String file) throws SQLException {url="jdbc:sqlite:"+file;try(Connection c=connect();Statement s=c.createStatement()){s.executeUpdate("CREATE TABLE IF NOT EXISTS cars (id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT NOT NULL, model TEXT NOT NULL, year INTEGER NOT NULL, price REAL NOT NULL)");}}
    private Connection connect() throws SQLException{return DriverManager.getConnection(url);}
    /** Вибирає записи бази.
     * @param min мінімальна ціна або null для всіх записів
     * @return записи
     * @throws SQLException помилка БД */
    public List<Row> select(Double min) throws SQLException {List<Row> list=new ArrayList<>();try(Connection c=connect();PreparedStatement p=c.prepareStatement("SELECT * FROM cars"+(min==null?"":" WHERE price > ?")+" ORDER BY id")){if(min!=null)p.setDouble(1,min);try(ResultSet r=p.executeQuery()){while(r.next())list.add(new Row(r.getInt("id"),new Car(r.getString("brand"),r.getString("model"),r.getInt("year"),r.getDouble("price"),0)));}}return list;}
    private void bind(PreparedStatement p,Car car)throws SQLException{p.setString(1,car.getBrand());p.setString(2,car.getModel());p.setInt(3,car.getYear());p.setDouble(4,car.getPrice());}
    /** Додає запис.
     * @param car автомобіль
     * @throws SQLException помилка БД */
    public void insert(Car car)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("INSERT INTO cars(brand,model,year,price) VALUES(?,?,?,?)")){bind(p,car);p.executeUpdate();}}
    /** Оновлює запис.
     * @param id ідентифікатор
     * @param car нові дані
     * @throws SQLException помилка БД */
    public void update(int id,Car car)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("UPDATE cars SET brand=?,model=?,year=?,price=? WHERE id=?")){bind(p,car);p.setInt(5,id);p.executeUpdate();}}
    /** Видаляє запис.
     * @param id ідентифікатор
     * @throws SQLException помилка БД */
    public void delete(int id)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("DELETE FROM cars WHERE id=?")){p.setInt(1,id);p.executeUpdate();}}
}
