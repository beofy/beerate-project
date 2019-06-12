package cn.beerate.path;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class PathTest {

    public static void main(String[] args) {
        try {
            String projectDir = ResourceUtils.getURL("").getPath();

            System.out.println(projectDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
