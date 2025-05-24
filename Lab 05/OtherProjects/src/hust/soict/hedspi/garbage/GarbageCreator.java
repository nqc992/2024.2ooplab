package hust.soict.hedspi.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) throws Exception {
        String filename = "test.exe";
        byte[] inputBytes = { 0 };
        long startTime, endTime;

        inputBytes = Files.readAllBytes(Paths.get(filename));
        startTime = System.currentTimeMillis();
        StringBuffer outputBuffer = new StringBuffer();
        for (byte b : inputBytes) {
        	outputBuffer.append((char) b);
        }
        String outputString = outputBuffer.toString();
        
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        StringBuilder outputStringBuilder = new StringBuilder();
        for (byte b : inputBytes) {
        	outputStringBuilder.append((char) b);
        }
    }
}
