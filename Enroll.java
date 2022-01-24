/*
    Code written by
    18050111028 EGE GENCOGLU
    18050111022 ALI AHMET TASKESEN
 */
package enroll;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Enroll {

    static int StartingID = 11000;
    static int lecturerID = 1000;

    public static class Student {

        private String Name;
        private String Surname;
        private int stdID;
        private int year;
        private String password;
        private String myDepartment;
        private ArrayList<Lecture> myClasses = new ArrayList<Lecture>();

        public Student(String Name, String Surname, int year, String password, String myDepartment) {
            this.Name = Name;
            this.Surname = Surname;
            StartingID += 1;
            this.stdID = StartingID;
            this.year = year;
            this.password = password;
            this.myDepartment = myDepartment;
        }

        public String getName() {
            return Name;
        }

        public String getSurname() {
            return Surname;
        }

        public int getID() {
            return stdID;
        }

        public int getYear() {
            return year;
        }

        //arraylistin toStringi yazılacak
        public String getPassword() {
            return password;
        }

        public String getMyDepartment() {
            return myDepartment;
        }

        public ArrayList<Lecture> getMyClasses() {
            return myClasses;
        }

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
            return getLectureName() + "\n" + getLecturer().getName() + "\nDate: " + getDate() + "\nClassroom: " + getClassroom() + "\nHour: " + getHour() + "\n" + getDepartment() + "\n";
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
        //System.out.println(schedule2);
        return schedule2;
    }

    public static void printSchedule(Lecture schedule[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 75; j++) {/*
                if (j == 0&&schedule[i][j] != null) {
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
        ArrayList<Student> AllStudents = new ArrayList<Student>();
        //Student[] AllStudents = new Student[1300];
        ArrayList<String> canAddLecture = new ArrayList<String>();

        String nameLecturer = "TeacherName";
        String surnameLecturer = "TeacherSurname";
        int LecturerPassword = 12345;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int keeper;
        int counter = 0;
        for (int i = 0; i < 60; i++) {
            keeper = i;
            Lecturer l1 = new Lecturer(nameLecturer.concat(String.valueOf(keeper + 1)), surnameLecturer.concat(String.valueOf(keeper + 1)), String.valueOf(LecturerPassword));
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
            if (i % 6 == 0) {
                lec1.setDepartment("Computer Engineering");
            } else if (i % 6 == 1) {
                lec1.setDepartment("Electric & Electronic Engineering");
            } else if (i % 6 == 2) {
                lec1.setDepartment("Civil Engineering");
            } else if (i % 6 == 3) {
                lec1.setDepartment("Mechanical Engineering");
            } else if (i % 6 == 4) {
                lec1.setDepartment("Geology Engineering");
            } else if (i % 6 == 5) {
                lec1.setDepartment("Industry Engineering");
            }
            //System.out.println(lec1);
            lectureList.add(lec1);
            counter++;
        }
        Lecture arr[][] = createSchedule(lectureList);
        //printSchedule(arr);

        String[] departments = {"Industry Engineering", "Geology Engineering", "Mechanical Engineering", "Civil Engineering", "Electric & Electronic Engineering", "Computer Engineering"};
        String Name = "StudentName";
        String Surname = "StudentSurname";
        String StudentPassword = "12345";

        //String password;
        for (int k = 0; k < 1200; k++) {
            //String keeper = String.valueOf(k); 
            keeper = k;
            //public Student(String Name, String Surname, int year, String password)

            Student s = new Student(Name.concat(String.valueOf(k)), Surname.concat(String.valueOf(k)), r.nextInt(4) + 1, StudentPassword, departments[r.nextInt(6)]);
            //Lecturer l1 = new Lecturer(nameLecturer.concat(String.valueOf(keeper + 1)), surnameLecturer.concat(String.valueOf(keeper + 1)), String.valueOf(i + LecturerPassword));
            //l1.setFullDays(days[counter]);
            //System.out.println(s.getName() + ' ' + s.getYear() + ' ' + s.myDepartment);
            AllStudents.add(s);
        }

        System.out.println("Welcome to Enroll MENU !");
        System.out.println("Press 1 for show all schedule");
        System.out.println("Press 2 for sign in Students");
        System.out.println("Press 3 for sign in Teachers");
        System.out.println("Press 4 for exit");
        System.out.print("What is your choice: ");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        while (choice != 4) {
            //1347 
            if (choice == 1) {
                
                printSchedule(arr);
                System.out.println("Press 2 for sign in Students");
                System.out.println("Press 3 for sign in Teachers");
                System.out.println("Press 4 for exit");
                System.out.print("What is your choice: ");
                choice = s.nextInt();
            }
            if (choice == 2) {
                Scanner s1 = new Scanner(System.in);
                System.out.println("ID: ");
                int temp_id = s1.nextInt();

                Scanner s2 = new Scanner(System.in);
                System.out.println("Password: : ");
                String temp_pw = s2.nextLine();

                //System.out.println(AllStudents.get(0).Name);
                //System.out.println(AllStudents.get(0).password);
                int flag = 1;
                for (int iter = 0; iter < AllStudents.size() ; iter++) {
                    
                    if (AllStudents.get(iter).getID() == temp_id && AllStudents.get(iter).getPassword().equals(temp_pw)) {
                        System.out.println("Sign in SUCCESSFUL!");
                        Student currentStd = AllStudents.get(iter);

                        System.out.println("Press 1 for add course");
                        System.out.println("Press 5 for show your current courses");
                        System.out.println("Press 4 for exit");

                        Scanner scan = new Scanner(System.in);
                        int choice2 = scan.nextInt();

                        if (choice2 == 1) {

                            System.out.println("mydepertment: " + currentStd.myDepartment);
                            for (int i = 0; i < lectureList.size(); i++) {
                                /*System.out.println(lectureList.get(i).department);
                                System.out.println("çekdeneme");
                                System.out.println(currentStd.myDepartment);*/
                                //System.out.println("lecture: " + i + lectureList.get(i).getDepartment());
                                if (lectureList.get(i).getDepartment().equals(currentStd.getMyDepartment()) &&
                                        (
                                        currentStd.getYear() == lectureList.get(i).getYear() ||
                                        currentStd.getYear()+1 == lectureList.get(i).getYear() || 
                                        currentStd.getYear()-1 == lectureList.get(i).getYear() )
                                        ){
                                    
                                    canAddLecture.add(lectureList.get(i).lectureName);
                                }

                            }

                            System.out.println(canAddLecture);
                            System.out.println("choose you want ");
                            Scanner scan2 = new Scanner(System.in);
                            String willAdd = scan2.nextLine();

                            for (int i = 0; i < lectureList.size(); i++) {
                                if (lectureList.get(i).getLectureName().equals(willAdd)) {

                                    currentStd.myClasses.add(lectureList.get(i));
                                }
                            }

                            System.out.println("Your current courses and schedules: ");

                            for (Lecture l1 : currentStd.myClasses) {
                                System.out.println(l1.toString());
                            }

                            System.out.println("if you want to add more lecture press 2");
                            System.out.println("For Exit Press 4");
                            choice2 = scan.nextInt();
                        }

                        if (choice2 == 2) {
                            while (choice2 == 2) {

                                System.out.println(canAddLecture);
                                System.out.println("choose you want ");
                                Scanner scan2 = new Scanner(System.in);
                                String willAdd = scan2.nextLine();

                                for (int i = 0; i < lectureList.size(); i++) {
                                    if (lectureList.get(i).getLectureName().equals(willAdd)) {

                                        currentStd.myClasses.add(lectureList.get(i));
                                    }
                                }

                                System.out.println("Your current courses: ");

                                for (Lecture l1 : currentStd.myClasses) {
                                    System.out.println(l1.toString());
                                }

                                System.out.println("if you want to add more lecture press 2");
                                System.out.println("For Exit Press 4");
                                choice2 = scan.nextInt();

                            }
                        }
                        if (choice2 == 5) {
                            System.out.println("Your current courses: ");

                                for (Lecture l1 : currentStd.myClasses) {
                                    System.out.println(l1.toString());
                                }
                                
                            System.out.println("if you want to add lecture press 2");
                            System.out.println("For Exit Press 4");
                            choice2 = scan.nextInt();
                        }
                        if (choice2 == 4) {
                            flag = 0;
                            break;
                        }
                    }
                }

                if (flag == 1) {
                    System.out.println("ERROR: you UNSUCCESS or valid choice!");
                    System.out.println("Press 2 for try again");
                    System.out.println("Press 4 for exit");
                    break;
                }
                break;

            }
            if (choice == 3){
                Scanner s1 = new Scanner(System.in);
                System.out.println("ID: ");
                int temp_id = s1.nextInt();

                Scanner s2 = new Scanner(System.in);
                System.out.println("Password: : ");
                String temp_pw = s2.nextLine();
                
                for (int iter = 0; iter < lecturerList.size() ; iter++) {
                    if (lecturerList.get(iter).getID() == temp_id && lecturerList.get(iter).getPassword().equals(temp_pw)){
                        System.out.println("Sign in SUCCESSFUL!");
                        Lecturer currentTeacher = lecturerList.get(iter);
                        
                        System.out.println("Press 1 for your given courses ");
                        //System.out.println("Press 5 for show your current courses");
                        //System.out.println("Press 4 for exit");

                        Scanner scan = new Scanner(System.in);
                        int choice2 = scan.nextInt();
                        
                        if (choice2 == 1){
                            
                            System.out.println(currentTeacher.givenCourses.toString());

                            break;
                            
                        }
                        if(choice2 == 4){
                            break;
                        }
                    } 
                }
                break;
            }
            if (choice == 4) {
                break;
            }

        }
    }

}
