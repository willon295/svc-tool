import org.junit.Test;

import java.io.File;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-31<br>
 * <br>
 */
public class ReplaceTest {
    @Test
    public void test(){
        String str="\\\\123\\123123//123";
        System.out.println(File.separator);
        String sep=File.separator;
        System.out.println(str.replaceAll("\\\\", sep));
    }
}
