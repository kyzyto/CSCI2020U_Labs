package sample;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignment;
    private float finalExam;
    private float finalMark;
    private String letter;

    public StudentRecord(String studentID, float assignment, float midterm, float finalExam) {
        this.studentID = studentID;
        this.assignment = assignment;
        this.midterm = midterm;
        this.finalExam = finalExam;

        finalMark = 0.2f * getAssignment() + 0.3f * getMidterm() + 0.5f * getFinalExam();

        if (finalMark >= 80 && finalMark <= 100) {
            letter = "A";
        }
        else if (finalMark >= 70 && finalMark < 80) {
            letter = "B";
        }
        else if (finalMark >= 60 && finalMark < 70) {
            letter = "C";
        }
        else if (finalMark >= 50 && finalMark < 60) {
            letter = "D";
        }
        else if (finalMark >= 0 && finalMark < 50) {
            letter = "F";
        }
        else {
            letter = "N/A";
        }
    }

    public String getStudentID() {
        return this.studentID;
    }

    public float getAssignment() {
        return this.assignment;
    }

    public float getMidterm() {
        return this.midterm;
    }

    public float getFinalExam() {
        return this.finalExam;
    }

    public float getFinalMark() {
        return this.finalMark;
    }

    public String getLetter() {
        return this.letter;
    }

}
