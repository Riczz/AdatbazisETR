package application;

import application.controller.QueriesController;
import application.model.DataInfo;
import application.model.pojo.*;
import application.view.dialog.AlertDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;

import java.sql.*;

public final class Database {

    public static final String URL = "jdbc:mysql://localhost:3306/etr";
    public static final String ROOT = "root";
    public static final String PASSWORD = "";
    public static final String NULLSTR = "-";
    private Connection conn = null;

    public Database(boolean connect) {
        if (connect) {
            this.connect();
        }
    }

    public Database() {
        this(true);
    }

    public boolean insert(ExamResult examResult) {
        try {
            connect();
            String sql = "INSERT INTO vizsgazik VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, examResult.getNo());
            st.setString(2, examResult.getNeptun());
            st.setString(3, examResult.getSubCode());
            st.setInt(4, examResult.getResult());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Vizsgaeredmény hozzáadása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean insert(Exam exam) {
        try {
            connect();
            String sql = "INSERT INTO vizsga VALUES(?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, exam.getSubCode());
            st.setTimestamp(2, Timestamp.valueOf(exam.getTime()));
            st.setInt(3, exam.getRoomNum());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Tárgy módosítása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean insert(Subject subject) {
        try {
            connect();
            String sql = "INSERT INTO targy VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, subject.getSub_code());
            if (isNull(subject.getReqSub_code())) {
                st.setNull(2, Types.VARCHAR);
            } else {
                st.setString(2, subject.getReqSub_code());
            }
            st.setString(3, subject.getNeptun());
            st.setString(4, subject.getName());
            st.setInt(5, subject.getCredits());
            st.setInt(6, subject.getHours());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Tárgy hozzáadás sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean insert(PersonalData personalData) {
        try {
            connect();
            String sql = "INSERT INTO szemelyes_adat VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, personalData.getNeptun());
            st.setString(2, personalData.getBirthPlace());
            st.setString(3, personalData.getBirthName());
            st.setDate(4, Date.valueOf(personalData.getBirthDate()));
            st.setString(5, personalData.getBirthCounty());
            st.setString(6, personalData.getBirthCountry());
            st.setBoolean(7, personalData.getGender().equalsIgnoreCase("nő"));
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Személyes adat hozzáadás sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean insert(User user) {
        try {
            connect();
            String sql = "INSERT INTO felhasznalo VALUES(?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getNeptun());
            st.setString(2, user.getLastname());
            st.setString(3, user.getFirstname());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Felhasználó hozzáadás sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean update(ExamResult examResult, ExamResult newExamResult) {
        try {
            connect();
            String sql = "UPDATE vizsgazik SET sorszam = ?, neptun_kod = ?, targy_kod = ?, eredmeny = ? WHERE sorszam = ? AND neptun_kod = ? AND targy_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, newExamResult.getNo());
            st.setString(2, newExamResult.getNeptun());
            st.setString(3, newExamResult.getSubCode());
            st.setInt(4, newExamResult.getResult());
            st.setInt(5, examResult.getNo());
            st.setString(6, examResult.getNeptun());
            st.setString(7, examResult.getSubCode());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Vizsgaeredmény módosítása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean update(Exam exam, Exam newExam) {
        try {
            connect();
            String sql = "UPDATE vizsga SET targy_kod = ?, idopont = ?, teremszam = ? WHERE targy_kod = ? AND idopont = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newExam.getSubCode());
            st.setTimestamp(2, Timestamp.valueOf(exam.getTime()));
            st.setInt(3, newExam.getRoomNum());
            st.setString(4, exam.getSubCode());
            st.setTimestamp(5, Timestamp.valueOf(exam.getTime()));
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Vizsga módosítása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean update(Subject subject, Subject newSubject) {
        try {
            connect();
            String sql = "UPDATE targy SET targy_kod = ?, elokovetelmeny_targy_kod = ?, neptun_kod = ?, nev = ?, kreditertek = ?, " +
                    "heti_oraszam = ? WHERE targy_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newSubject.getSub_code());
            if (isNull(newSubject.getReqSub_code())) {
                st.setNull(2, Types.VARCHAR);
            } else {
                st.setString(2, newSubject.getReqSub_code());
            }
            st.setString(3, newSubject.getNeptun());
            st.setString(4, newSubject.getName());
            st.setInt(5, newSubject.getCredits());
            st.setInt(6, newSubject.getHours());
            st.setString(7, subject.getSub_code());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Tárgy módosítása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean update(PersonalData personalData, PersonalData newPersonalData) {
        try {
            connect();
            String sql = "UPDATE szemelyes_adat SET neptun_kod = ?, szuletesi_hely = ?, szuletesi_nev = ?, " +
                    "szuletesi_datum = ?, szuletesi_megye = ?, szuletesi_orszag = ?, nem = ? WHERE neptun_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newPersonalData.getNeptun());
            st.setString(2, newPersonalData.getBirthPlace());
            st.setString(3, newPersonalData.getBirthName());
            st.setDate(4, Date.valueOf(newPersonalData.getBirthDate()));
            st.setString(5, newPersonalData.getBirthCounty());
            st.setString(6, newPersonalData.getBirthCountry());
            st.setBoolean(7, newPersonalData.getGender().equalsIgnoreCase("nő"));
            st.setString(8, personalData.getNeptun());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Személyes adat módosítása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public boolean update(User user, User newUser) {
        try {
            connect();
            String sql = "UPDATE felhasznalo SET neptun_kod = ?, vezeteknev = ?, keresztnev = ? WHERE neptun_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newUser.getNeptun());
            st.setString(2, newUser.getLastname());
            st.setString(3, newUser.getFirstname());
            st.setString(4, user.getNeptun());
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Felhasználó módosítása sikertelen!");
            return false;
        } finally {
            close();
        }
    }

    public void delete(ExamResult examResult) {
        try {
            connect();
            String sql = "DELETE FROM vizsgazik WHERE sorszam = ? AND neptun_kod = ? AND targy_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, examResult.getNo());
            st.setString(2, examResult.getNeptun());
            st.setString(3, examResult.getSubCode());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Vizsgaeredmény törlése sikertelen!");
        } finally {
            close();
        }
    }

    public void delete(Exam exam) {
        try {
            connect();
            String sql = "DELETE FROM vizsga WHERE targy_kod = ? AND idopont = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, exam.getSubCode());
            st.setTimestamp(2, Timestamp.valueOf(exam.getTime()));
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Tárgy törlése sikertelen!");
        } finally {
            close();
        }
    }

    public void delete(Subject subject) {
        try {
            connect();
            String sql = "DELETE FROM targy WHERE targy_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, subject.getSub_code());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Tárgy törlése sikertelen!");
        } finally {
            close();
        }
    }

    public void delete(PersonalData personalData) {
        try {
            connect();
            String sql = "DELETE FROM szemelyes_adat WHERE neptun_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, personalData.getNeptun());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Személyes adat törlése sikertelen!");
        } finally {
            close();
        }
    }

    public void delete(User user) {
        try {
            connect();
            String sql = "DELETE FROM felhasznalo WHERE neptun_kod = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getNeptun());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Felhasználó törlése sikertelen!");
        } finally {
            close();
        }
    }

    public ObservableList<ExamResult> getExamResults() {
        ObservableList<ExamResult> exams = FXCollections.observableArrayList();
        try {
            connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM vizsgazik");

            while (rs.next()) {
                ExamResult exam = new ExamResult(
                        rs.getInt("sorszam"),
                        rs.getString("neptun_kod"),
                        rs.getString("targy_kod"),
                        rs.getInt("eredmeny")
                );
                exams.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return exams;
    }

    public ObservableList<Exam> getExams() {
        ObservableList<Exam> exams = FXCollections.observableArrayList();
        try {
            connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM vizsga");

            while (rs.next()) {
                Exam exam = new Exam(
                        rs.getString("targy_kod"),
                        rs.getTimestamp("idopont"),
                        rs.getInt("teremszam")
                );
                exams.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return exams;
    }

    public ObservableList<Subject> getSubjects() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try {
            connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM targy");

            while (rs.next()) {
                String preReq = rs.getString("elokovetelmeny_targy_kod");
                Subject subject = new Subject(
                        rs.getString("targy_kod"),
                        preReq == null ? NULLSTR : rs.getString("elokovetelmeny_targy_kod"),
                        rs.getString("neptun_kod"),
                        rs.getString("nev"),
                        rs.getInt("kreditertek"),
                        rs.getInt("heti_oraszam")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return subjects;
    }

    public ObservableList<PersonalData> getPersonalDatas() {
        ObservableList<PersonalData> data = FXCollections.observableArrayList();
        try {
            connect();
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
                        rs.getBoolean("nem") ? "nő" : "férfi"
                );
                data.add(personalData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return data;
    }

    public ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            connect();
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
        } finally {
            close();
        }

        return users;
    }

    public ObservableList<QueriesController.Query3> extraQuery3() {
        ObservableList<QueriesController.Query3> items = FXCollections.observableArrayList();
        String sql = "SELECT targy.nev, targy.kreditertek FROM targy WHERE kreditertek = (SELECT MIN(targy.kreditertek) FROM targy)";

        try {
            connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                items.add(new QueriesController.Query3(
                        rs.getString(1),
                        rs.getInt(2)
                ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return items;
        } finally {
            close();
        }
    }

    public ObservableList<QueriesController.Query2> extraQuery2() {
        ObservableList<QueriesController.Query2> items = FXCollections.observableArrayList();
        String sql = "SELECT vizsgazik.targy_kod, AVG(vizsgazik.eredmeny) AS jegy FROM vizsgazik " +
                "INNER JOIN hallgato ON vizsgazik.neptun_kod = hallgato.neptun_kod " +
                "GROUP BY vizsgazik.targy_kod HAVING jegy <= 3";

        try {
            connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                items.add(new QueriesController.Query2(
                        rs.getString(1),
                        rs.getInt(2)
                ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return items;
        } finally {
            close();
        }
    }

    public ObservableList<QueriesController.Query1> extraQuery1() {
        ObservableList<QueriesController.Query1> items = FXCollections.observableArrayList();
        String sql = "SELECT szemelyes_adat.szuletesi_megye, COUNT(szemelyes_adat.neptun_kod) AS letszam FROM felhasznalo " +
                "INNER JOIN szemelyes_adat ON felhasznalo.neptun_kod = szemelyes_adat.neptun_kod " +
                "GROUP BY szemelyes_adat.szuletesi_megye ORDER BY letszam DESC";

        try {
            connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                items.add(new QueriesController.Query1(
                        rs.getString(1),
                        rs.getInt(2)
                ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return items;
        } finally {
            close();
        }
    }

    public void connect() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, ROOT, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Adatbázis kapcsolódás sikertelen!");
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
                //ResultSet rs = meta.getTables("etr", null, null, null);

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

    public static boolean isNull(String s) {
        return (s.equalsIgnoreCase("-") ||
                s.equalsIgnoreCase("null") ||
                s.equalsIgnoreCase("")
        );
    }
}