public class MultithreadArray {
    private static final int size = 10000000;
    private static final int halfSize = size / 2;

    public float[] calculateNewValue(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
        return arr;
    }

    public void firstMethod() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long a = System.currentTimeMillis();
        calculateNewValue(arr);
        System.out.println("Duration of method excecution is : " + (System.currentTimeMillis() - a));
    }

    public void secondMethod() {
        float[] arr = new float[size];
        float[] arr1 = new float[halfSize];
        float[] arr2 = new float[halfSize];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, halfSize);
        System.arraycopy(arr, halfSize, arr2, 0, halfSize);

        new Thread() {
            public void run() {
                float[] a1 = calculateNewValue(arr1);
                System.arraycopy(a1, 0, arr1, 0, a1.length);
            }
        }.start();

        new Thread() {
            public void run() {
                float[] a2 = calculateNewValue(arr2);
                System.arraycopy(a2, 0, arr2, 0, a2.length);
            }
        }.start();

        System.arraycopy(arr1, 0, arr, 0, halfSize);
        System.arraycopy(arr2, 0, arr, halfSize, halfSize);
        System.out.println("Duration of method excecution is: " + (System.currentTimeMillis() - a));
    }

}
