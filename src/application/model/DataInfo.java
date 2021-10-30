package application.model;

import application.model.pojo.Exam;
import application.view.DataTable;
import application.view.tables.*;

import java.util.HashMap;
import java.util.Set;

public class DataInfo {

    private static final HashMap<String, String> tableLabels = new HashMap<>();
    private static final HashMap<String, DataTable> tables = new HashMap<>();
    private static DataInfo instance = null;

    private DataInfo() {
        tableLabels.put("felhasznalo", "Felhasználó");
        tableLabels.put("szemelyes_adat", "Személyes adatok");
        tableLabels.put("targy", "Tárgy");
        tableLabels.put("vizsga", "Vizsga");
        tableLabels.put("vizsgazik", "Vizsgaeredmények");
//        tableLabels.put("nev_elotag", "Név előtag");
//        tableLabels.put("okmany", "Okmányok");
//        tableLabels.put("hallgato", "Hallgatók");
//        tableLabels.put("belepesi_adat", "Belépési adat");
//        tableLabels.put("felev", "Félév");
//        tableLabels.put("felevet_elvegez", "Elvégzett félévek");
//        tableLabels.put("kepzes", "Képzés");
//        tableLabels.put("kepzest_felvesz", "Felvett képzések");
//        tableLabels.put("oktato", "Oktatók");
//        tableLabels.put("kurzus", "Kurzus");
//        tableLabels.put("kurzust_felvesz", "Felvett kurzusok");
//        tableLabels.put("targyat_felvesz", "Felvett tárgyak");
//        tableLabels.put("felvetelek", "Felvételek");
//        tableLabels.put("terem", "Terem");
//        tableLabels.put("vizsgazik", "Vizsgák");

        tables.put("felhasznalo", new UserTable());
        tables.put("szemelyes_adat", new PersonalDataTable());
        tables.put("targy", new SubjectTable());
        tables.put("vizsga", new ExamTable());
        tables.put("vizsgazik", new ExamResultTable());
    }

    public DataTable getTable(String key) {
        if (!tables.containsKey(key)) {
            return null;
        }
        return tables.get(key);
    }

    public String getLabel(String key) {
        if (!tableLabels.containsKey(key)) {
            return null;
        }
        return tableLabels.get(key);
    }

    public Set<String> keys() {
        return tableLabels.keySet();
    }

    public static DataInfo getInstance() {
        return instance == null ? new DataInfo() : instance;
    }
}
