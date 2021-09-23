package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        //System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        //printWithStreams(tasksData);
        //printDeadlinesUsingStreams(tasksData);
        //System.out.println("Total number of deadlines using stream " +
        //        countDeadlinesWithStream(tasksData));
        printDataWithStreams(tasksData);
        printDeadlinesUsingStreams(tasksData);
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing Deadlines with streams");
        tasks.stream()
                .filter(t -> t instanceof Deadline) //filtering using lambda
                .forEach(System.out::println);
    }

    public static void printWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data with streams");
        tasks.stream() //convert data to stream
                .forEach(System.out::println); //terminal operator
    }

    private static int countDeadlinesWithStream(ArrayList<Task> tasks) {
        //System.out.println("Printing deadline count with streams");
        int count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
        return count;
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return filteredList;

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }


    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams");
        tasks.forEach(System.out::println); //terminal operator
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase(Locale.ROOT)))
                .forEach(System.out::println);


    }
}
