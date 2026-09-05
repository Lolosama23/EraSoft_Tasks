package com.lo2ay;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem","Nada", "Nour", "Samy", "", null);


        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );


        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );
        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        //************************** THE ANSWERS ***************************

        //(1)🔹 Basic Stream Operations

        // (1)Filter even numbers from a list of integers.
        // Predicate<Integer>ISeven= number->number%2==0;
        List<Integer> nums=numbers.stream().filter(number->number%2==0).collect(Collectors.toList());

        //(2)Find names starting with a specific letter from a list of strings.
        List<String> Names=names.stream().filter(n->n.startsWith("A")).collect(Collectors.toList());

        //(3)Convert all strings to uppercase using stream.
        //  Objects::nonNull;== names3->names3!=null &&&& String::toUpperCase== names-> names.toUpperCase();
        List<String> Names3=names.stream().filter(Objects::nonNull ).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(Names3);

        //(4)Sort a list of integers in descending order using streams.
        //علشان ال sorted ترتب تصاعدي وانا عايز تنازلي
        List<Integer>nums1= numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        //(5)Remove duplicate elements from a list using distinct().
        List<String> Names5=names.stream().distinct().collect(Collectors.toList());

        //-------------------------------------------------------------------------

        //(2)🔹 Intermediate Stream Tasks

        //(1)Count the number of strings longer than 5 characters.
        // count ===> long
        Long count =names.stream().filter(Objects::nonNull).filter(n->n.length()>5).count();

        //(2)Find the first element in a stream that matches a given condition.
        Optional<Integer>firstlement= numbers.stream().filter(n->n >6).findFirst();

        //(3)Check if any number is divisible by 5 in a list.
        //any match===>boolean
        boolean anynumber=numbers.stream().anyMatch(n -> n%5==0);

        //(4)Collect elements into a Set instead of a List
        Set<String>names1=names.stream().collect(Collectors.toSet());

        //(5)Skip the first 3 elements and return the rest.
        List<Integer> n=numbers.stream().skip(3).collect(Collectors.toList());
        //--------------------------------------------------------------------------

        //(3)🔹 Numeric Streams & Reductions

        //(1)Calculate the sum of a list of integers using reduce.
        int sum=numbers.stream().reduce(0,(a,b)->a+b);

        //(2)Find the maximum and minimum value in a list.
        // Integer::compareTo;===>((a,b)->a.compareTo(b)
        Optional<Integer> exist=numbers.stream().max((a,b)->a.compareTo(b));
        Optional<Integer> exist1=numbers.stream().min((a,b)->a.compareTo(b));

        //(3)Calculate the average of a list of doubles.
        double average= numbers.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

        //(4)Multiply all integers in a list together using reduce
        int multipli = numbers.stream().reduce(1,(a,b)->a*b);

        //(5)Count how many numbers are positive in a list.
        Long count1= numbers.stream().filter(na -> na>0).count();
        //-----------------------------------------------------------------

        //(4)🔹 Collectors & Grouping
        //(1)Group a list of students by their department. اكتر من كجموعه
        //student -> student.getDepartment()
        Map<String,List<Student>> groupedId=students.stream().collect(Collectors.groupingBy(Student::getDepartment));

        //(2)Partition a list of numbers into even and odd using partitioningBy. مجموعتين بس
        Map<Boolean,List<Integer>> twogroups= numbers.stream().collect(Collectors.partitioningBy(pn-> pn%2==0));

        //(3)Create a comma-separated string from a list of strings.
        String comma=names.stream().filter(Objects::nonNull).filter(nom -> !nom.isEmpty())
                .collect(Collectors.joining(","));

        //(4)Group employees by age and count how many per age.
        Map<Integer,Long> groupbyAge= employees.stream().collect(Collectors.
                groupingBy(Employee::getAge,Collectors.counting()));

        //(5)Find the average salary per department in a list of employees.
        Map<String,Double> avSalary = employees.stream().collect(Collectors.
                groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
//------------------------------------------------------------------------------

        //5/🔹 Optional, Map, FlatMap
        //(1)Flatten a list of lists into a single list
        List<String> flatWords = nestedWords.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //(2)Extract all unique characters from a list of words
        List<String> uniqueChars = nestedWords.stream()
                .flatMap(List::stream) // فكينا القوائم لكلمات
                .flatMap(word -> Arrays.stream(word.split(""))) // فكينا الكلمات لحروف
                .distinct()
                .collect(Collectors.toList());

        //(3)Filter a list of Optionals and collect non-empty values
        // List<Optional<String>> optionals = ...;
// List<String> presentValues = optionals.stream()
//     .flatMap(Optional::stream) // دي بتطلع القيمة لو موجودة وتتجاهلها لو فاضية
//     .collect(Collectors.toList());

        //(4)Map a list of strings to their lengths
        List<Integer> nameLengths = names.stream()
                .filter(Objects::nonNull)
                .map(String::length)
                .collect(Collectors.toList());

        //(5)Return a list of uppercased words that start with
        List<String> upperA_Names = names.stream()
                .filter(name -> name != null && name.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        //-----------------------------------------------------------

        //(5)🔹 Advanced Operations
        //(1)Sort a list of employees by salary then by name
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());

        //(2)Find the second highest number in a list
        Optional<Integer> secondHighest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        //(3)Find duplicate elements in a list of integers
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = numbers.stream()
                .filter(nn -> !seen.add(nn)) // لو الـ add رجعت false يعني الرقم متكرر
                .distinct() // عشان لو الرقم اتكرر 3 مرات ميطلعش مرتين في النتيجة
                .collect(Collectors.toList());

        //(4)Remove null or empty strings from a list
        List<String> validNames = names.stream()
                .filter(name -> name != null && !name.trim().isEmpty())
                .collect(Collectors.toList());

        //(5)Partition students into pass/fail groups based on grade
        // true -> الناجحين, false -> الساقطين
        Map<Boolean, List<Student>> passFailStudents = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGrade() >= 50));



    }
}