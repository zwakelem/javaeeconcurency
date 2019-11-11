import java.util.concurrent.ExecutionException;

public class Test123 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AsyncTest asyncTest = new AsyncTest();
        System.out.println("**** start ****");
        asyncTest.testAsyncAnnotationForMethodsWithReturnType();
    }
}
