class Student {
    private String rollNo;
    private String firstName;
    private String fatherName;
    private String lastName;
    private String domicile;
    private String email;

    public Student (String rollno, String name, String father, String surname, String district, String mail) {
        this.rollNo = rollno;
        this.firstName = name;
        this.fatherName = father;
        this.lastName = surname;
        this.domicile = district;
        this.email = mail;
    }

    public String getRollNo () { return this.rollNo; }
    public String getFirstName () { return this.firstName; }
    public String getFatherName () { return this.fatherName; }
    public String getLastName () { return this.lastName; }
    public String getDomicile () { return this.domicile; }
    public String getEmail () { return this.email; }
}