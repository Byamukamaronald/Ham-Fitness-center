/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package triathlonresults;

/**
 *
 * @author pc
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


class Participant {
    private final String name;
    private final String id;
    private int swimmingTime;
    private int cyclingTime;
    private int runningTime;

    
    public Participant(String name, String id, int swimmingTime, int cyclingTime, int runningTime) {
        this.name = name;
        this.id = id;

        
        if (swimmingTime < 0 || cyclingTime < 0 || runningTime < 0) {
            throw new IllegalArgumentException("Times cannot be negative.");
        }

        this.swimmingTime = swimmingTime;
        this.cyclingTime = cyclingTime;
        this.runningTime = runningTime;
    }

    
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int calculateTotalTime() {
        return swimmingTime + cyclingTime + runningTime;
    }

    
    public void displayDetails() {
        System.out.printf("Name: %s, ID: %s, Total Time: %d minutes%n", name, id, calculateTotalTime());
    }
}


class EliteParticipant extends Participant {
    private final String sponsorName;

    
    public EliteParticipant(String name, String id, int swimmingTime, int cyclingTime, int runningTime, String sponsorName) {
        super(name, id, swimmingTime, cyclingTime, runningTime);
        this.sponsorName = sponsorName;
    }

    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.printf("Sponsor: %s%n", sponsorName);
    }
}


class BeginnerParticipant extends Participant {
    // Constructor
    public BeginnerParticipant(String name, String id, int swimmingTime, int cyclingTime, int runningTime) {
        super(name, id, swimmingTime, cyclingTime, runningTime);
    }
}


public class TriathlonResults {
    public static void main(String[] args) {
        ArrayList<Participant> participants = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the number of participants:");
            int numParticipants = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numParticipants; i++) {
                System.out.printf("Enter details for Participant %d:%n", i + 1);

                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter ID: ");
                String id = scanner.nextLine();

                System.out.print("Enter swimming time (in minutes): ");
                int swimmingTime = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter cycling time (in minutes): ");
                int cyclingTime = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter running time (in minutes): ");
                int runningTime = Integer.parseInt(scanner.nextLine());

                System.out.print("Is this participant an elite participant? (yes/no): ");
                String isElite = scanner.nextLine();

                if (isElite.equalsIgnoreCase("yes")) {
                    System.out.print("Enter sponsor name: ");
                    String sponsorName = scanner.nextLine();
                    participants.add(new EliteParticipant(name, id, swimmingTime, cyclingTime, runningTime, sponsorName));
                } else {
                    participants.add(new BeginnerParticipant(name, id, swimmingTime, cyclingTime, runningTime));
                }

                System.out.println();
            }
        }

        
        System.out.println("Participants Details:");
        participants.forEach(Participant::displayDetails);

        
        participants.sort(Comparator.comparingInt(Participant::calculateTotalTime));

        
        Participant fastest = participants.get(0);
        Participant secondFastest = participants.get(1);

        
        System.out.println("\nFastest Participant:");
        fastest.displayDetails();

        System.out.println("\nSecond Fastest Participant:");
        secondFastest.displayDetails();

        
        System.out.println("\nParticipants Sorted by Total Time:");
        participants.forEach(Participant::displayDetails);
    }
}
