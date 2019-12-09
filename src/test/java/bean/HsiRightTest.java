package bean;

import org.junit.Test;
import util.StringUtils;

import java.io.Serializable;

/**
 * Created by lyd on 2017-06-28.
 */
public class HsiRightTest implements Serializable,Cloneable {

    @Test
    public void cloneTest()  {
        HsiRight hsiRight=new HsiRight("123","123","123","123","123","123","123","123","123","123");
        HsiRight hsiRight1=hsiRight;
        HsiRight hsiRight2= (HsiRight) hsiRight.clone();
        System.out.println(hsiRight==hsiRight1);
        System.out.println(hsiRight==hsiRight2);

        hsiRight2.setC_islimit("1");
        System.out.println(hsiRight.equals(hsiRight2));


    }
}
