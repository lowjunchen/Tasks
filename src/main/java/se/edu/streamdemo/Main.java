package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

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

    public static int countDeadlines_streams(ArrayList<Task> tasksData) {
        int count = (int) tasksData.stream().filter((t) -> t instanceof Deadline).count();
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    /*** Print all data with streams in a new method ****/
    public static void printAllData_streams(ArrayList<Task> tasksData) {
        tasksData.stream().forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    /*** lambda function ****/
    public static void printDeadlines_streams(ArrayList<Task> tasksData) {
        tasksData.stream().filter(t -> t instanceof Deadline).sorted((t1,t2) -> t1.getDescription().compareTo(t2.getDescription())).forEach(System.out::println);
    }

    /*** lambda function ****/
    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterString){
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream().filter(t -> t.getDescription().contains(filterString)).collect(toList());
        return filteredList;
    }

}
    /*** Print Deadline data with streams in a new method ****/
    public static void printDeadlines_streams(ArrayList<Task> tasksData) {
        tasksData.stream().filter(t -> t instanceof Deadline).forEach(System.out::println);
    }

}