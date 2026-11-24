import cars.*;
import java.nio.file.*;
/** Перевірка функцій лабораторних 8–10 та опційно SQLite. */
public class SmokeTest {
    /** Запускає перевірки. @param args передайте db для перевірки SQLite @throws Exception помилка тесту */
    public static void main(String[] args) throws Exception {
        CarManager m=new CarManager();
        check(m.all().length==10); check(m.sortPrice(false)[0].getPrice()==12000); check(m.sortPrice(true)[0].getPrice()==52000); check(m.filterPrice(30000).length==2);check(m.filterRegex("^T").length==2);check(m.minMax().length==2);check(m.sortBrand(false)[0].getBrand().equals("Audi"));check(m.sortBrand(true)[0].getBrand().equals("Volkswagen"));
        m.loadFromText(Path.of("cars.txt"));check(m.all().length==10);Path tmp=Files.createTempFile("cars", ".dat");try{m.saveSortedToBinary(tmp);Car[] a=m.showFromBinary(tmp);check(a.length==10&&a[0].getPrice()==12000&&a[9].getPrice()==52000);}finally{Files.deleteIfExists(tmp);}
        if(args.length>0&&args[0].equals("db")){Path p=Files.createTempFile("cars", ".db");try{CarDatabase d=new CarDatabase(p.toString());d.insert(m.all()[0]);check(d.select(null).size()==1);int id=d.select(null).get(0).id();d.update(id,m.all()[1]);check(d.select(30000.0).size()==1);d.delete(id);check(d.select(null).isEmpty());}finally{Files.deleteIfExists(p);}}
        System.out.println("Smoke tests passed");
    }
    private static void check(boolean result){if(!result)throw new AssertionError("Test failed");}
}
