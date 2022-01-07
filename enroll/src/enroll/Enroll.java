/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package enroll;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author User
 */

public class Enroll {
static int StartingID=11000;
static int lecturerID=1000;

    public static class Student{
        private String Name;
        private String Surname;
        private int ID;
        private int year;
        private String password;
        private ArrayList<String> myClasses=new ArrayList<String>();

        public Student(String Name, String Surname, int year, String password) {
            this.Name = Name;
            this.Surname = Surname;
            StartingID+=1;
            this.ID = StartingID;
            this.year = year;
            this.password=password;
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
    
    public static class Lecture{
        private String lectureName;
        private Lecturer lecturer;
        private int Credit;
        private int numberOfSection;
        private int year;
        private String Date;
        private String classroom;

        public Lecture(String lectureName, Lecturer lecturer, int Credit, int numberOfSection, int year) {
            this.lectureName = lectureName;
            this.lecturer = lecturer;
            this.lecturer.givenCourses.add(lectureName);
            this.Credit = Credit;
            this.numberOfSection = numberOfSection;
            this.year = year;
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
    
    public static class Lecturer{
        private String name;
        private String surname;
        private int ID;
        private String password;
        private ArrayList<String> FullDays=new ArrayList<String>();
        private ArrayList<String> givenCourses=new ArrayList <String>();

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
    
    public static void createSchedule(ArrayList<Lecture> Lecture){
      String[][] days=new String[2][5];
      days[0][0]="Monday Morning";
      days[0][1]="Monday Afternoon";
      days[1][0]="Tuesday Morning";
      days[1][1]="Tuesday Afternoon";
      days[2][0]="Wednesday Morning";
      days[2][1]="Wednesday Afternoon";
      days[3][0]="Thursday Morning";
      days[3][1]="Thursday Afternoon";
      days[4][0]="Friday Morning";
      days[4][1]="Friday Afternoon";

      
      
      
               
               
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList <Lecture> lecList=new ArrayList<Lecture>();
        Lecturer l1 =new Lecturer("ege","gncgl","12345");
        System.err.println(l1.getID());
        Lecturer l2 =new Lecturer("mrt","gncgl","12345");
        System.err.println(l2.getID());      
        Lecturer l3 =new Lecturer("lutfi","gncgl","12345");
        System.err.println(l3.getID());    
        Lecturer l4 =new Lecturer("sadiye","gncgl","12345");
        System.err.println(l4.getID());   
        Lecturer l5 =new Lecturer("buruşuk","gncgl","12345");
        System.err.println(l5.getID());  
        
        l1.setFullDays("Monday");
        l2.setFullDays("Tuesday");
        l3.setFullDays("Wednesday");
        l4.setFullDays("Thursday");
        l5.setFullDays("Friday");
        
        Lecture lec1=new Lecture("Math",l1,5,3,3);
        Lecture lec2=new Lecture("ceng113",l2,5,3,3);
        Lecture lec3=new Lecture("ceng114",l2,5,3,3);
        Lecture lec4=new Lecture("biology",l3,5,3,3);
        Lecture lec5=new Lecture("chemstry",l3,5,3,3);
        Lecture lec6=new Lecture("turkish",l4,5,3,3);
        Lecture lec7=new Lecture("ceng101",l5,5,3,3);
        Lecture lec8=new Lecture("ceng103",l5,5,3,3);
        Lecture lec9=new Lecture("M1",l5,5,3,3);
        Lecture lec10=new Lecture("M2",l1,5,3,3);
        Lecture lec11=new Lecture("M3",l4,5,3,3);
        Lecture lec12=new Lecture("M4",l4,5,3,3);
        Lecture lec13=new Lecture("Math II",l1,5,3,3);
        //0-1 ile 0-4 dahil random sayılar üretilecek
        Random r=new Random();
        int a=r.nextInt();
        

    }
    
}
