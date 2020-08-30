package pack;

/**
 * @author hedw1q
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Details {

    private Details(int timeA, int timeB, String detName) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.detName = detName;
    }

    private static int detCount;
    private static double loadFactor;
    public static ArrayList<Details> detailList = new ArrayList<>();
    // time for detail processing on A/B machine
    private String detName;
    private int timeA;
    private int timeB;
    private int mach2StartTime;
    private int mach2FinishTime;
    private int mach1StartTime;
    private int mach1FinishTime;

    public static void setDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input number of details=");
        detCount = scanner.nextInt();

        for (int i = 0; i < detCount; i++) {
            System.out.print("Input detail " + (i + 1) + " processing times: ");
            detailList.add(new Details(scanner.nextInt(), scanner.nextInt(), Integer.toString(i + 1)));
        }
    }

    public static void printInputData() {
        System.out.println("===========================");
        System.out.println("Number of details:" + detCount);
        for (int i = 0; i < detCount; i++) {
            System.out.println("Detail " + detailList.get(i).detName + ": " + detailList.get(i).timeA + "," + detailList.get(i).timeB);
        }
        System.out.println("==========================");
    }

    public static int min(int a, int b) {
        if (a < b) return a;
        else return b;
    }

    public static void sort() {
        for (int i = 0; i < detCount; i++) {
            Collections.sort(detailList, (o1, o2) -> {
                if (min(o1.timeA, o2.timeB) <= min(o2.timeA, o1.timeB)) return -1;
                else if (min(o1.timeA, o2.timeB) > min(o2.timeA, o1.timeB)) return 1;
                else return 0;
            });
        }
    }

    public static void loadFactorCompute() {

        detailList.get(0).mach2StartTime = detailList.get(0).timeA;
        detailList.get(0).mach2FinishTime = detailList.get(0).mach2StartTime + detailList.get(0).timeB;
        detailList.get(0).mach1FinishTime = detailList.get(0).timeA;

        for (int i = 1; i < detCount; i++) {
            detailList.get(i).mach1StartTime = detailList.get(i - 1).mach1FinishTime;
            detailList.get(i).mach1FinishTime = detailList.get(i).mach1StartTime + detailList.get(i).timeA;
            if (detailList.get(i).mach1FinishTime <= detailList.get(i - 1).mach2FinishTime) {
                detailList.get(i).mach2StartTime = detailList.get(i - 1).mach2FinishTime;
            } else detailList.get(i).mach2StartTime = detailList.get(i).mach1FinishTime;
            detailList.get(i).mach2FinishTime = detailList.get(i).mach2StartTime + detailList.get(i).timeB;
        }

        int timeBsum = 0;
        for (Details det : detailList) {
            timeBsum += det.timeB;
        }
        loadFactor = (double) timeBsum / detailList.get(detCount - 1).mach2FinishTime;
    }

    public static void getOrder() {
        System.out.print("Optimal order to process: ");
        System.out.println(detailList.toString());
        System.out.println("Load factor=" + loadFactor);
    }

    public static void solve(){
        setDetails();
        printInputData();
        sort();
        loadFactorCompute();
        getOrder();
    }
    @Override
    public String toString() {
        return "Detail " + detName;

    }
}