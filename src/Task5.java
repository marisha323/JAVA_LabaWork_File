import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Corporation corporation = new Corporation();


        System.out.println("What is the next action?");
        System.out.println("1 - введення даних \n" +
                "2 -  редагування даних співробітника корпорації\n" +
                "3 -  видалення співробітника корпорації\n" +
                "4 - пошук співробітника корпорації по прізвищy\n" +
                "5 - вивід інформації про всіх співробітників, вказання віку, або прізвища які починаються на вказану" +
                " букву\n" );
        int res = scanner.nextInt();

        switch (res)
        {
            case 1:
                String filePath = getInfo("Enter file Path: ");
                String fName = getInfo("Enter first name: ");
                String phone = getInfo("Enter phone: ");
                int age = Integer.parseInt(getInfo("Enter age: "));

                Employee employee = new Employee(fName, phone,age);
                corporation.addEmployee(employee);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
                    corporation.saveToFile(writer,corporation.employeeList);
                    System.out.println("Data was saved to the file!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                String firstName = getInfo("Enter first name: ");
                corporation.deleteEmployee(firstName);

                break;

            case 4:
                String name = getInfo("Enter first name: ");

                corporation.findEmployeeByName(name);
                break;
            case 5:
                String letter = getInfo("Enter first letter to find employee: ");
                corporation.getEmployeesStartingWithLetter(letter);
                break;

        }

    }
    private static String getInfo (String message)
    {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
    public static class Employee{

        private String firstname;
        private String phoneNumber;
        private int age;

        public Employee(String firstname, String phoneNumber, int age) {
            this.firstname = firstname;
            this.phoneNumber = phoneNumber;
            this.age = age;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "firstname='" + firstname + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class Corporation
    {
        private List<Employee> employeeList;

        public Corporation()
        {

            employeeList = new ArrayList<>();
            String filePath = getInfo("Enter file Path: ");
            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(" ");

                    String firstName = fields[0] ;
                    String phoneNumber = fields[1];
                    int age = Integer.parseInt(fields[2]);

                    Employee employee = new Employee(firstName,phoneNumber,age);

                    employeeList.add(employee);
                }
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }

            } catch (
                    FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void addEmployee(Employee employee)
        {
            employeeList.add(employee);
        }
        public void editEmployee(String lastName, Employee employee)
        {

        }

        public void deleteEmployee(String firstName)
        {
            Employee employeeToDelete=null;

            for(Employee employee : employeeList)
            {
                if(employee.getFirstname().equals(firstName))
                {
                    employeeToDelete = employee;
                    break;
                }
            }

            if(employeeToDelete!=null)
            {
                employeeList.remove(employeeToDelete);
                System.out.println("Employee " + firstName + "has deleted successfully!" );
            }else{
                System.out.println("Employee " + firstName + "was not found!" );
            }

        }

        public Employee findEmployeeByName(String firstName)
        {
            for(Employee employee : employeeList)
            {
                employee.getFirstname().equals(firstName);
                return employee;
            }
            return null;
        }
        public List<Employee>getEmployeesStartingWithLetter(String letter)
        {
            List<Employee>empWithLetters = new ArrayList<>();
            employeeList.stream().filter(e->e.getFirstname().startsWith(letter)).forEach(employee -> {empWithLetters.add(employee);
                System.out.println(employee);});

            return employeeList;
        }

        public void saveToFile(BufferedWriter writer, List<Employee>employeeList)throws IOException
        {
            for(Employee employee : employeeList)
            {
                writer.write(employee.toString());
            }
            writer.newLine();
        }


    }
}
