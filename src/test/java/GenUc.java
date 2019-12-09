import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 功能说明:
 * 系统版本: 2.5.7.0
 *
 * @author: eric
 * 开发时间: 2019-05-08
 */
public class GenUc {

    public static void main(String[] args) {
        String sql="INSERT INTO HSI_RIGHT(C_RIGHTCODE,C_RIGHTNAME,C_CLASS,C_SYSNAME,C_FUNCTIONNO,C_JAVACLASS,C_JAVAMETHOD,C_CLIENTPROGS,C_TABLENAME,C_UCTYPE,C_ISLIMIT) VALUES ('%s','%s','2','FINGARDATS','%s','com.fingard.rdc.devtools.app.pub.bo.CommonQueryBO','execute',NULL,NULL,'1','0');";
        Scanner scanner=new Scanner(System.in);
        List<String> ucList=new ArrayList<>();
        List<String> ucsqlList=new ArrayList<>();
        while (scanner.hasNext()){
            String str=scanner.nextLine();
            if(str.equals("end")){
                break;
            }
            ucList.add(str);
        }
        System.out.println(ucList.toArray());
        for(String ucInfo : ucList){
            String no=ucInfo.split(",")[0];
            String uc=ucInfo.split(",")[1];
            String name=ucInfo.split(",")[2];
            ucsqlList.add(String.format(sql,no,name,uc));
        }
        for(String ucsql : ucsqlList){
            System.out.println(ucsql);
        }
    }
}
