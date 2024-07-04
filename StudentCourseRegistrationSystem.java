import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// StudentCourseRegistrationSystem class to manage registration and other operations
public class StudentCourseRegistrationSystem {
    
    private CourseDatabase courseDatabase;
    private StudentDatabase studentDatabase;
    private Scanner scanner;

    public StudentCourseRegistrationSystem() {
        this.courseDatabase = new CourseDatabase();
        this.studentDatabase = new StudentDatabase();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. List Courses");
            System.out.println("4. List Students");
            System.out.println("5. Register for Course");
            System.out.println("6. Drop Course");
            System.out.println("7. Remove Course");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    listCourses();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    registerCourse();
                    break;
                case 6:
                    dropCourse();
                    break;
                    case 7:
                    removeCourse();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter schedule (comma-separated days): ");
        String scheduleInput = scanner.nextLine();
        ArrayList<String> schedule = new ArrayList<>();
        for (String day : scheduleInput.split(",")) {
            schedule.add(day.trim());
        }

        Course course = new Course(courseCode, title, description, capacity, schedule);
        courseDatabase.addCourse(course);
        System.out.println("Course added successfully.");
    }

    private void addStudent() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentID, name);
        studentDatabase.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void listCourses() {
        courseDatabase.listCourses();
    }

    private void listStudents() {
        studentDatabase.listStudents();
    }

    private void registerCourse() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = studentDatabase.getStudent(studentID);
        Course course = courseDatabase.getCourse(courseCode);

        if (student != null && course != null) {
            student.registerCourse(course);
            System.out.println("Registration successful.");
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private void dropCourse() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = studentDatabase.getStudent(studentID);
        Course course = courseDatabase.getCourse(courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private void removeCourse() {
        System.out.print("Enter course code to remove: ");
        String courseCode = scanner.nextLine();

        Course course = courseDatabase.getCourse(courseCode);
        if (course != null) {
            courseDatabase.removeCourse(courseCode);
            System.out.println("Course removed successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public static void main(String[] args) {
        StudentCourseRegistrationSystem system = new StudentCourseRegistrationSystem();
        system.run();
    }
}


// Course class to store course details
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private ArrayList<String> schedule;
    private ArrayList<Student> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, ArrayList<String> schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<String> getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }


    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Course is full.");
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return courseCode + ": " + title + "\nDescription: " + description + 
               "\nCapacity: " + capacity + "\nAvailable Slots: " + getAvailableSlots() + 
               "\nSchedule: " + String.join(", ", schedule);
    }
}

// Student class to store student details
class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.getAvailableSlots() > 0) {
            registeredCourses.add(course);
            course.enrollStudent(this);
        } else {
            System.out.println("Course is either full or already registered.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentID).append("\nName: ").append(name).append("\nRegistered Courses:\n");
        for (Course course : registeredCourses) {
            sb.append(course.getCourseCode()).append(": ").append(course.getTitle()).append("\n");
        }
        return sb.toString();
    }
}

// CourseDatabase class to store and manage all courses
class CourseDatabase {
    private HashMap<String, Course> courses;

    public CourseDatabase() {
        this.courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public void removeCourse(String courseCode) {
        courses.remove(courseCode);
    }

    public void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
            System.out.println("---------------");
        }
    }
}

// StudentDatabase class to store and manage all students
class StudentDatabase {
    private HashMap<String, Student> students;

    public StudentDatabase() {
        this.students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentID(), student);
    }

    public Student getStudent(String studentID) {
        return students.get(studentID);
    }

    public void listStudents() {
        for (Student student : students.values()) {
            System.out.println(student);
            System.out.println("---------------");
        }
    }
}


