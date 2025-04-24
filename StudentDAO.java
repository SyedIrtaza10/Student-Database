import java.sql.*;

class StudentDAO {
    private Connection connection;

    public StudentDAO () {
        this.connection = DBConnection.getConnection();
    }

    // Get the Students with the given ID
    public Student[] getStudentsByID (String id) {
        String roll_no = id.trim();
        Student[] students = new Student[this.getNumOfStudentsWith(roll_no)];
        String query = "SELECT * FROM student_info WHERE roll_no LIKE \"%" + roll_no + "%\"";
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

    // Get Number of Students with the given ID
    private int getNumOfStudentsWith (String id) {
        int num = 0;
        String query = "SELECT COUNT(roll_no) FROM student_info WHERE roll_no LIKE \"%" + id + "%\"";

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