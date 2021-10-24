package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import model.DataInfo;
import model.pojo.PersonalData;
import model.pojo.Subject;
import model.pojo.User;
import view.DataTable;

import java.sql.*;

public class Database {

    public static final String URL = "jdbc:mysql://localhost:3306/etr";
    public static final String ROOT = "root";
    public static final String PASSWORD = "";
    private Connection conn = null;

    public Database() {
        this(true);
    }

    public Database(boolean connect) {
        if (connect) {
            this.connect();
        }
    }

    public void update(String sql) {

    }

    public ObservableList<Subject> getSubjects() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM targy");

            while (rs.next()) {
                System.out.println(rs.getString("elokovetelmeny_targy_kod"));

                Subject subject = new Subject(
                        rs.getString("targy_kod"),
                        rs.getString("elokovetelmeny_targy_kod"),
                        rs.getString("neptun_kod"),
                        rs.getString("nev"),
                        rs.getInt("kreditertek"),
                        rs.getInt("heti_oraszam")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public ObservableList<PersonalData> getPersonalDatas() {
        ObservableList<PersonalData> data = FXCollections.observableArrayList();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM szemelyes_adat");

            while (rs.next()) {
                PersonalData personalData = new PersonalData(
                        rs.getString("neptun_kod"),
                        rs.getString("szuletesi_hely"),
                        rs.getString("szuletesi_nev"),
                        rs.getDate("szuletesi_datum"),
                        rs.getString("szuletesi_megye"),
                        rs.getString("szuletesi_orszag"),
                        rs.getBoolean("nem")
                );
                data.add(personalData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM felhasznalo");

            while (rs.next()) {
                User user = new User(
                        rs.getString("neptun_kod"),
                        rs.getString("vezeteknev"),
                        rs.getString("keresztnev")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(URL, ROOT, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Adatbazis kapcsolodas sikertelen!");
        }
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillWithTables(TabPane tabPane) {
        if (conn != null) {
            try {
                DataInfo info = DataInfo.getInstance();
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet rs = meta.getTables("etr", null, null, null);

                for (String key : info.keys()) {
                    Tab tab = new Tab(info.getLabel(key));
                    tab.setTooltip(new Tooltip(info.getLabel(key)));
                    tab.setId(key);
                    tabPane.getTabs().add(tab);
                }

            } catch (RuntimeException | SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void createTable(Tab tab) {
        DataInfo info = DataInfo.getInstance();
        DataTable t = info.getTable(tab.getId());
        tab.setContent(t);
    }

}