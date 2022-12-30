package dfs;


import com.cczu.examples.hdfs.client.FileSystem;
import com.cczu.examples.hdfs.client.FileSystemImpl;

/**
 * @author yjz
 * @date 2022/1/9
 */
public class FileSystemTest {
    public static void main(String[] args) throws Exception {

        FileSystem fileSystem = new FileSystemImpl();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    try {
                        fileSystem.mkdir("/user/aaa" + j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
