package an.qqwer.wallet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志工具类类
 */
public class LogUtil {

    // 获取日志文件，没有则创建
    public static File getLogFile(String userID) {
        // 构建文件名
        String fileName = "log/log-" + userID + ".log";
        // 文件是否存在的判断
        File file = new File(fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    /**
     * 日志追加记录
     *  日期 - 动作 - 花费 - 余额
     * @param userId  用户id
     * @param movement  动作
     * @param useMoney  花费
     * @param useMoney  余额
     */
    public static void appendLog(String userId, String movement, Integer useMoney, Integer totalMoney ){
        // 判断文件是否存在，否则创建
        File logFile = getLogFile(userId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        FileOutputStream fos = null;
        try {

            // 获取文件流，以追加的形式
            fos = new FileOutputStream(logFile, true);
            String log = dateTime + "   "+ movement + "   "+ useMoney + "   "+ totalMoney + "\n";
            fos.write(log.getBytes());
            fos.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 流的关闭
    public static void cloneStream(FileInputStream fis, InputStreamReader isr, BufferedReader br){
        if (br != null){
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (isr != null){
            try {
                isr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (fis != null){
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
