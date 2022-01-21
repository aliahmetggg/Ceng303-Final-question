/*
    Code written by
    18050111028 EGE GENCOGLU
    18050111022 ALI AHMET TASKESEN
 */
package enroll;

import java.util.ArrayList;
import java.util.Random;

public class Enroll {

    static int StartingID = 11000;
    static int lecturerID = 1000;

    public static class Student {

        private String Name;
        private String Surname;
        private int ID;
        private int year;
        private String password;
        private ArrayList<String> myClasses = new ArrayList<String>();

        public Student(String Name, String Surname, int year, String password) {
            this.Name = Name;
            this.Surname = Surname;
            StartingID += 1;
            this.ID = StartingID;
            this.year = year;
            this.password = password;
        }

        public String getName() {
            return Name;
        }

        public String getSurname() {
            return Surname;
        }

        public int getID() {
            return ID;
        }

        public int getYear() {
            return year;
        }
        //arraylistin toStringi yazılacak

    }

    public static class Lecture {

        private String lectureName;
        private Lecturer lecturer;
        private int Credit;
        private int numberOfSection;
        private int year;
        private String Date;
        private String classroom;
        private String Hour;
        private String department;

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getClassroom() {
            return classroom;
        }

        public void setClassroom(String classroom) {
            this.classroom = classroom;
        }

        public String getHour() {
            return Hour;
        }

        public void setHour(String Hour) {
            this.Hour = Hour;
        }

        public Lecture(String lectureName, Lecturer lecturer, int Credit, int year) {
            this.lectureName = lectureName;
            this.lecturer = lecturer;
            this.lecturer.givenCourses.add(lectureName);
            this.Credit = Credit;
            this.year = year;
        }

        @Override
        public String toString() {
            return getLectureName() + "\n" + getLecturer().getName() + "\n" + getDate() + "\n" + getClassroom() + "\n" + getHour() + "\n" + getDepartment() + "\n";
        }

        public String getLectureName() {
            return lectureName;
        }

        public void setLectureName(String lectureName) {
            this.lectureName = lectureName;
        }

        public Lecturer getLecturer() {
            return lecturer;
        }

        public void setLecturer(Lecturer lecturer) {
            this.lecturer = lecturer;
        }

        public int getCredit() {
            return Credit;
        }

        public void setCredit(int Credit) {
            this.Credit = Credit;
        }

        public int getNumberOfSection() {
            return numberOfSection;
        }

        public void setNumberOfSection(int numberOfSection) {
            this.numberOfSection = numberOfSection;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getclassroom() {
            return classroom;
        }

        public void setClass(String classroom) {
            this.classroom = classroom;
        }
    }

    public static class Lecturer {

        private String name;
        private String surname;
        private int ID;
        private String password;
        private ArrayList<String> FullDays = new ArrayList<String>();
        private ArrayList<String> givenCourses = new ArrayList<String>();

        public Lecturer(String name, String surname, String password) {
            this.name = name;
            this.surname = surname;
            lecturerID++;
            this.ID = lecturerID;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public int getID() {
            return ID;
        }

        public String getPassword() {
            return password;
        }

        public ArrayList<String> getFullDays() {
            return FullDays;
        }

        public ArrayList<String> getGivenCourses() {
            return givenCourses;
        }
        //iki fonksiyon değişecek

        public void setFullDays(String Day) {
            this.FullDays.add(Day);
        }

        public void setGivenCourses(String givenCourse) {
            this.givenCourses.add(givenCourse);
        }

    }

    public static Lecture[][] createSchedule(ArrayList<Lecture> lectureList) {
        String[] hours = {"09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00"};//10
        String[] classroomCode = {"111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125"};//15
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        Lecture[][][] schedule = new Lecture[6][9][5];
        Lecture[][] schedule2 = new Lecture[9][75];//[0-14]monday-[15-29]tueday-[30-44]wednesday-[45-59]thursday-[60-74]friday
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 75; k++) {
                schedule2[j][k] = null;
            }
        }
        ArrayList<Lecture> temp = new ArrayList<Lecture>();
        int counter = 0;
        int class_counter = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 75; j++) {
                if (counter < 180) {
                    if (schedule2[i][j] == null) {
                        if (lectureList.get(counter).getLecturer().getFullDays().get(0) == days[j % 5]) {
                            lectureList.get(counter).setDate(days[(j % 4) + 1]);
                            lectureList.get(counter).setHour(hours[i]);
                            lectureList.get(counter).setClassroom(classroomCode[class_counter]);
                            if ((j + 15) >= 75) {
                                i++;
                                j = (j + 15) - 75;
                            }
                            schedule2[i][j + 15] = lectureList.get(counter);
                            class_counter++;
                            counter++;
                            if (class_counter == 15) {
                                class_counter = 0;
                            }
                        } else {
                            lectureList.get(counter).setDate(days[j % 4]);
                            lectureList.get(counter).setHour(hours[i]);
                            lectureList.get(counter).setClassroom(classroomCode[class_counter]);
                            if ((j + 15) >= 75) {
                                i++;
                                j = (j + 15) - 75;
                            }
                            schedule2[i][j + 15] = lectureList.get(counter);
                            class_counter++;
                            counter++;
                            if (class_counter == 15) {
                                class_counter = 0;
                            }
                        }
                    }
                }
            }
        }
        return schedule2;
    }

    public static void printSchedule(Lecture schedule[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 75; j++) {
                /*if (j == 0&&schedule[i][j] != null) {
                    System.out.println("---MONDAY---");
                    System.out.println(schedule[i][j].toString());
                } else if (j == 15 &&schedule[i][j] != null) {
                    System.out.println("---TUESDAY---");
                    System.out.println(schedule[i][j].toString());
                } else if (j == 30&&schedule[i][j] != null) {
                    System.out.println("---WEDNESDAY---");
                    System.out.println(schedule[i][j].toString());
                } else if (j == 45&&schedule[i][j] != null) {
                    System.out.println("---THURSDAY---");
                    System.out.println(schedule[i][j].toString());
                } else if (j == 60&&schedule[i][j] != null) {
                    System.out.println("---FRIDAY---");
                    System.out.println(schedule[i][j].toString());
                }*/
                if (schedule[i][j] != null) {
                    System.out.println(schedule[i][j].toString());
                }
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
        ArrayList<Lecturer> lecturerList = new ArrayList<Lecturer>();

        String nameLecturer = "TeacherName";
        String surnameLecturer = "TeacherSurname";
        int LecturerPassword = 12345;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int keeper;
        int counter = 0;
        for (int i = 0; i < 60; i++) {
            keeper = i;
            Lecturer l1 = new Lecturer(nameLecturer.concat(String.valueOf(keeper + 1)), surnameLecturer.concat(String.valueOf(keeper + 1)), String.valueOf(i + LecturerPassword));
            if (counter == 5) {
                counter = 0;
            }
            l1.setFullDays(days[counter]);
            lecturerList.add(l1);
            counter++;
        }
        // Lecture(String lectureName, Lecturer lecturer, int Credit, int year)
        String lectureName = "LectureName";
        Random r = new Random();
        int a = r.nextInt(7);
        int b = r.nextInt(5);
        counter = 0;
        for (int i = 0; i < 180; i++) {
            keeper = i;
            if (counter == 59) {
                counter = 0;
            }
            Lecture lec1 = new Lecture(lectureName.concat(String.valueOf(keeper + 1)), lecturerList.get(counter), a, b);
            a = r.nextInt(5);
            b = r.nextInt(5);
            if ((i + 1) % 6 == 0) {
                lec1.setDepartment("Computer Engineering");
            } else if ((i + 1) % 6 == 1) {
                lec1.setDepartment("Electric & Electronic Engineering");
            } else if ((i + 1) % 6 == 2) {
                lec1.setDepartment("Civil Engineering");
            } else if ((i + 1) % 6 == 3) {
                lec1.setDepartment("Mechanical Engineering");
            }
            if ((i + 1) % 6 == 5) {
                lec1.setDepartment("Geology Engineering");
            } else {
                lec1.setDepartment("Industry Engineering");
            }
            lectureList.add(lec1);
            counter++;
        }
        Lecture arr[][] = createSchedule(lectureList);
        printSchedule(arr);
    }

}
