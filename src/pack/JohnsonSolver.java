package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author hedw1q
 */
public class JohnsonSolver {

    private static int detCount;
    private static double loadFactor;

    /**
     * input initial data of a task
     *
     * @return list of details
     */
    public ArrayList<Detail> inputDetails() {
        ArrayList<Detail> detailList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input number of details=");
        detCount = scanner.nextInt();

        for (int i = 0; i < detCount; i++) {
            System.out.print("Input detail " + (i + 1) + " processing times: ");
            detailList.add(new Detail(scanner.nextInt(), scanner.nextInt(), Integer.toString(i + 1)));
        }
        return detailList;
    }

    /**
     * print inputted data
     *
     * @param detailList
     */
    public static void printInputData(List<Detail> detailList) {
        System.out.println("===========================");
        System.out.println("Number of details:" + detCount);
        for (int i = 0; i < detCount; i++) {
            System.out.println("Detail " + detailList.get(i).getDetName() + ": " + detailList.get(i).getTimeA() + "," + detailList.get(i).getTimeB());
        }
        System.out.println("==========================");
    }

    public static int min(int a, int b) {
        if (a < b) return a;
        else return b;
    }

    /**
     * solve the task by sorting inputted list
     *
     * @param detailList
     */
    public void sort(List<Detail> detailList) {

        for (int i = 0; i < detCount; i++) {
            Collections.sort(detailList, (o1, o2) -> {
                if (min(o1.getTimeA(), o2.getTimeB()) <= min(o2.getTimeA(), o1.getTimeB())) return -1;
                else if (min(o1.getTimeA(), o2.getTimeB()) > min(o2.getTimeA(), o1.getTimeB())) return 1;
                else return 0;
            });
        }
    }

    /**
     * computes load factor
     *
     * @param detailList
     */
    public void loadFactorCompute(List<Detail> detailList) {

        detailList.get(0).setMach2StartTime(detailList.get(0).getTimeA());
        detailList.get(0).setMach2FinishTime(detailList.get(0).getMach2FinishTime() + detailList.get(0).getTimeB());
        detailList.get(0).setMach1FinishTime(detailList.get(0).getTimeA());

        for (int i = 1; i < detCount; i++) {
            detailList.get(i).setMach1StartTime(detailList.get(i - 1).getMach1FinishTime());
            detailList.get(i).setMach1FinishTime(detailList.get(i).getMach1StartTime() + detailList.get(i).getTimeA());
            if (detailList.get(i).getMach1FinishTime() <= detailList.get(i - 1).getMach2FinishTime()) {
                detailList.get(i).setMach2StartTime(detailList.get(i - 1).getMach2FinishTime());
            } else detailList.get(i).setMach2StartTime(detailList.get(i).getMach1FinishTime());
            detailList.get(i).setMach2FinishTime(detailList.get(i).getMach2StartTime() + detailList.get(i).getTimeB());
        }

        int timeBsum = 0;
        for (Detail det : detailList) {
            timeBsum += det.getTimeB();
        }
        loadFactor = (double) timeBsum / detailList.get(detCount - 1).getMach2FinishTime();
    }

    /**
     * executes methods inputDetails,printInputData,sort, loadfactorcompute
     * prints optimal process order and load factor
     *
     * @param detailList
     * @return List<Detail>
     */
    public List<Detail> getOrder(List<Detail> detailList) {
        detailList = this.inputDetails();
        this.printInputData(detailList);
        this.sort(detailList);
        this.loadFactorCompute(detailList);

        System.out.print("Optimal order to process: ");
        System.out.println(detailList.toString());
        System.out.println("Load factor=" + loadFactor);
        return detailList;
    }
    
    public static void main(String[] args) {
        JohnsonSolver task1 = new JohnsonSolver();
        task1.getOrder(new ArrayList<Detail>());

    }
}
