import java.sql.*;

class StudentDAO {
    private Connection connection;

    public StudentDAO () {
        this.connection = DBConnection.getConnection();
    }

    public Student getStudent (String id) {
        Student student = null;
        String roll_no = id.trim();
        String query = "SELECT * FROM student_info WHERE roll_no = \"" + roll_no + "\"";

        try (Statement stmt = this.connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                student = new Student(
                    rs.getString("roll_no"),
                    rs.getString("firstname"),
                    rs.getString("fathername"),
                    rs.getString("surname"),
                    rs.getString("domicile"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student[] getAllStudents () {
        Student[] students = new Student[this.getNumOfStudents()];
        String query = "SELECT * FROM student_info";
        int studentIndex = 0;

        try (Statement stmt = this.connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student(
                    rs.getString("roll_no"),
                    rs.getString("firstname"),
                    rs.getString("fathername"),
                    rs.getString("surname"),
                    rs.getString("domicile"),
                    rs.getString("email")
                );
                students[studentIndex] = student;
                studentIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public int getNumOfStudents () {
        int num = 0;
        String query = "SELECT COUNT(roll_no) FROM student_info";

        try (Statement stmt = this.connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                num = rs.getInt("COUNT(roll_no)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }
}