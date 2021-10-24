package model;

import model.pojo.Subject;
import view.DataTable;
import view.tables.PersonalDataTable;
import view.tables.SubjectTable;
import view.tables.UserTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DataInfo {

    private static final HashMap<String, String> tableLabels = new HashMap<>();
    private static final HashMap<String, DataTable> tables = new HashMap<>();
    private static DataInfo instance = null;

    private DataInfo() {
        tableLabels.put("felhasznalo", "Felhasználó");
//        tableLabels.put("nev_elotag", "Név előtag");
//        tableLabels.put("okmany", "Okmányok");
//        tableLabels.put("hallgato", "Hallgatók");
//        tableLabels.put("belepesi_adat", "Belépési adat");
        tableLabels.put("szemelyes_adat", "Személyes adatok");
//        tableLabels.put("felev", "Félév");
//        tableLabels.put("felevet_elvegez", "Elvégzett félévek");
//        tableLabels.put("kepzes", "Képzés");
//        tableLabels.put("kepzest_felvesz", "Felvett képzések");
//        tableLabels.put("oktato", "Oktatók");
        tableLabels.put("targy", "Tárgy");
//        tableLabels.put("kurzus", "Kurzus");
//        tableLabels.put("kurzust_felvesz", "Felvett kurzusok");
//        tableLabels.put("targyat_felvesz", "Felvett tárgyak");
//        tableLabels.put("felvetelek", "Felvételek");
//        tableLabels.put("terem", "Terem");
//        tableLabels.put("vizsga", "Vizsga");
//        tableLabels.put("vizsgazik", "Vizsgák");

        tables.put("felhasznalo", new UserTable());
        tables.put("szemelyes_adat", new PersonalDataTable());
//        tables.put("targy", new SubjectTable());
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
