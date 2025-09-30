import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class schedule {

    static class Task {
        String desc;
        LocalTime start;
        LocalTime end;
        String priority;

        Task(String d, LocalTime s, LocalTime e, String p) {
            this.desc = d;
            this.start = s;
            this.end = e;
            this.priority = p;
        }

        @Override
        public String toString() {
            return String.format("%s - %s: %s [%s]", start.toString(), end.toString(), desc, priority);
        }
    }

    // Very simple in-memory list (global, mutable) — poor style but intentionally basic
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== BadSchedule (Minimal Astronaut Schedule) ===");
        while (true) { // intentionally crude loop
            System.out.println("\nChoose: 1=Add, 2=Remove, 3=View, 4=Exit");
            System.out.print("-> ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                addFlow(sc);
            } else if (choice.equals("2")) {
                removeFlow(sc);
            } else if (choice.equals("3")) {
                viewFlow();
            } else if (choice.equals("4")) {
                System.out.println("Bye.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    // minimal add: will throw DateTimeParseException for bad time formats (no graceful handling)
    static void addFlow(Scanner sc) {
        try {
            System.out.print("Description: ");
            String desc = sc.nextLine().trim();
            System.out.print("Start time (HH:mm): ");
            LocalTime s = LocalTime.parse(sc.nextLine().trim()); // brittle
            System.out.print("End time (HH:mm): ");
            LocalTime e = LocalTime.parse(sc.nextLine().trim()); // brittle
            System.out.print("Priority (High/Medium/Low): ");
            String p = sc.nextLine().trim();

            if (e.isBefore(s) || e.equals(s)) {
                System.out.println("Error: end must be after start.");
                return;
            }

            Task t = new Task(desc, s, e, p);

            Task conflict = findConflict(t);
            if (conflict != null) {
                // intentionally print minimal info and do not offer resolution
                System.out.println("Error: Task conflicts with existing task \"" + conflict.desc + "\".");
                return;
            }

            tasks.add(t);
            System.out.println("Task added successfully.");
        } catch (DateTimeParseException ex) {
            // intentionally minimal: print and return (not robust)
            System.out.println("Invalid time format. Use HH:mm");
        } catch (Exception ex) {
            // swallow weird exceptions (bad practice)
            System.out.println("Something went wrong.");
        }
    }

    static Task findConflict(Task n) {
        // naive overlap check — inclusive boundaries; note: this logic is simplistic
        for (Task t : tasks) {
            boolean overlap = !(n.end.isBefore(t.start) || n.start.isAfter(t.end));
            if (overlap) return t;
        }
        return null;
    }

    static void removeFlow(Scanner sc) {
        System.out.print("Enter exact description to remove: ");
        String desc = sc.nextLine().trim();
        Iterator<Task> it = tasks.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Task t = it.next();
            if (t.desc.equals(desc)) {
                it.remove();
                removed = true;
                break; // remove only first match (poor)
            }
        }
        if (removed) {
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    static void viewFlow() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        // sort in-place by start time
        tasks.sort(new Comparator<Task>() {
            public int compare(Task a, Task b) {
                return a.start.compareTo(b.start);
            }
        });
        System.out.println("Scheduled tasks:");
        for (Task t : tasks) {
            System.out.println("  " + formatDisplay(t));
        }
    }

    static String formatDisplay(Task t) {
        // print minutes always two-digit; LocalTime.toString gives "HH:MM[:ss]" so keep simple
        return String.format("%s - %s: %s [%s]", t.start.toString(), t.end.toString(), t.desc, t.priority);
    }
}
