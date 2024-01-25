import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------");

        List<String> planA = new ArrayList<>();
        planA.add("Paris");
        planA.add("Berlin");
        planA.add("Krakow");
        List<String> planB = replan(planA, "Vienna", "Krakow");

        System.out.println("Plan A: " + planA);
        System.out.println("Plan B: " + planB);
        System.out.println("Plan A: " + planA + " <--- A が変更されていないか確認！");


        System.out.println("----------");

        ArrayList<Double> lapTimes = new ArrayList<>();
        lapTimes.add(31.0);
        lapTimes.add(20.9);
        lapTimes.add(21.1);
        lapTimes.add(21.3);

        System.out.printf("Total: %.1fs\n", totalTime(lapTimes));
        System.out.printf("Ave: %.1fs\n", avgTime(lapTimes));
    }

    public static List<String> replan(List<String> plan,
                                      String newCity,
                                      String beforeCity) {
        /*
        int newCityIndex = plan.indexOf(beforeCity);
        plan.add(newCityIndex, newCity);
        return plan;
        */
        // ↓
        int newCityIndex = plan.indexOf(beforeCity);
        List<String> replanned = new ArrayList<>(plan);
        replanned.add(newCityIndex, newCity);
        return replanned;
    }

    public static double totalTime(List<Double> lapTimes) {
        List<Double> withoutWarmUp = new ArrayList<>(lapTimes);
        withoutWarmUp.remove(0);
        double sum = 0;
        for (double x : withoutWarmUp) {
            sum += x;
        }
        return sum;
    }

    public static double avgTime(List<Double> lapTimes) {
        double time = totalTime(lapTimes);
        List<Double> withoutWarmUp = new ArrayList<>(lapTimes);
        withoutWarmUp.remove(0);
        int laps = withoutWarmUp.size();
        return time / laps;
    }
}