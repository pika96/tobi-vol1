package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String path) throws IOException {
        BufferedReaderCallback sumCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        Integer sum = 0;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sum += Integer.valueOf(line);
                        }

                        return sum;
                    }
                };

        return fileReadTemplate(path, sumCallback);
    }

    public Integer calcMultiply(String numFilepath) throws IOException {
        BufferedReaderCallback sumCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        Integer multiply = 1;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            multiply *= Integer.valueOf(line);
                        }

                        return multiply;
                    }
                };

        return fileReadTemplate(numFilepath, sumCallback);
    }

    public Integer fileReadTemplate(String path, BufferedReaderCallback callBack) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            int ret = callBack.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if(br != null) {
                try { br.close(); }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
