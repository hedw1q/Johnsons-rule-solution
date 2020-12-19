package pack;

/**
 * @author hedw1q
 */

public class Detail {

    Detail(int timeA, int timeB, String detName) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.detName = detName;
    }

    /**
     * Detail processing parameters
     */
    private String detName;
    private int timeA;
    private int timeB;
    private int mach2StartTime;
    private int mach2FinishTime;
    private int mach1StartTime;
    private int mach1FinishTime;

    /**
     * getters and setters
     */
    public String getDetName() {
        return detName;
    }

    public void setDetName(String detName) {
        this.detName = detName;
    }

    public int getTimeA() {
        return timeA;
    }

    public void setTimeA(int timeA) {
        this.timeA = timeA;
    }

    public int getTimeB() {
        return timeB;
    }

    public void setTimeB(int timeB) {
        this.timeB = timeB;
    }

    public int getMach2StartTime() {
        return mach2StartTime;
    }

    public void setMach2StartTime(int mach2StartTime) {
        this.mach2StartTime = mach2StartTime;
    }

    public int getMach2FinishTime() {
        return mach2FinishTime;
    }

    public void setMach2FinishTime(int mach2FinishTime) {
        this.mach2FinishTime = mach2FinishTime;
    }

    public int getMach1StartTime() {
        return mach1StartTime;
    }

    public void setMach1StartTime(int mach1StartTime) {
        this.mach1StartTime = mach1StartTime;
    }

    public int getMach1FinishTime() {
        return mach1FinishTime;
    }

    public void setMach1FinishTime(int mach1FinishTime) {
        this.mach1FinishTime = mach1FinishTime;
    }

    @Override
    public String toString() {
        return "Detail " + detName;
    }

}