package com.example.gradleairquality.Model.UserManagement;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.Sensor;
import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.Comuni;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.Threshold;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.ThresholdsMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * classe di utility per implementare l'interazione con il database
 */
public class DBService {

    public static ResultSet logIn(String username, String password) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/dashboard";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "parthenope");
        String query = "SELECT * FROM utente where CF='" + username + "'" + "and PASSWORD ='" + password + "'";

        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }

    public static void insertNewManager(Manager manager) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        int cap = 80000 + new Random().nextInt(150);
        String myUrl = "jdbc:mysql://localhost:3306/dashboard";
        String apice = "'";
        String virgola = ",";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "parthenope");
        String query = "INSERT INTO utente(CF,NAME,SURNAME,BIRTH_DATE,CAP,EMAIL,PASSWORD,TYPE,AREA_TYPE,AREA) values ( '" +
                manager.getUsername() + apice + virgola + apice + manager.getName() + apice + virgola + apice + manager.getSurname() +
                apice + virgola + apice + manager.getBirthDate() + apice + virgola + apice + cap + apice + virgola + apice + manager.getEmail() + apice + virgola +
                apice + manager.getPassword() + apice + virgola + "'MG'," + "'GA'," + apice + manager.getGovernanceArea().getNome() + apice + ")";
        conn.prepareStatement(query).execute();

    }


    public static boolean checkAreaAvailability(Comuni comuni) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/dashboard";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "parthenope");
        String query = "SELECT COUNT(CF) FROM utente WHERE AREA= '" + comuni.getNome() + "'";
        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs.getInt(1) <= 0;

        }
        return true;
    }


    public static LinkedList<Sensor> getSensors(Manager manager) throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/dashboard";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "parthenope");
        String query = "SELECT * FROM SENSOR where CF='" + manager.getUsername() + "'";
        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            LinkedList<Sensor> list = new LinkedList<>();
            rs.beforeFirst();
            while (rs.next()) {
                list.add(new Sensor(rs.getString(2), rs.getString(4), rs.getDouble(5), rs.getDouble(6)));

            }

            return list;
        } else return null;

    }

    public static ResultSet getThresholds(Manager manager) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/dashboard";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "parthenope");
        String query = "SELECT * FROM threshold where cf= '" + manager.username + "'";
        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }

    public static void saveThresholds(Manager manager) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/dashboard";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "parthenope");
        ThresholdsMap map = ThresholdsMap.getThresholdsInstance(manager);
        Map<measureType, Threshold> thresholds = map.getThresholds();

        ArrayList<PreparedStatement> queryList = new ArrayList<>();


        for (measureType measure : measureType.values()) {
            PreparedStatement temp = conn.prepareStatement("UPDATE threshold set value = ? where cf = ? AND type = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            temp.setInt(1, thresholds.get(measure).getThreshold());
            temp.setString(2, manager.username);
            temp.setString(3, measure.name());

            queryList.add(temp);

        }


        for (PreparedStatement preparedStatement : queryList) {
            //String query = preparedStatement.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ","");
            preparedStatement.executeUpdate();
        }

    }


}
