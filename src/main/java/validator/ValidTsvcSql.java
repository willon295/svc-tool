package validator;

import bean.TsvcInterface;
import bean.TsvcSql;
import util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * 日期：2019/10/25
 *
 * @author：eric 功能：
 */
public class ValidTsvcSql {

    public static void valid(TsvcInterface tsvcInterface ) throws Exception {
        if (StringUtils.isNotNullAndNotEmpty(tsvcInterface.getC_existvalue()) ){
            validSql(tsvcInterface.getC_existvalue());
        }
        if (StringUtils.isNotNullAndNotEmpty(tsvcInterface.getC_existvalue_mysql()) ){
            validSql(tsvcInterface.getC_existvalue_mysql());
        }
    }
    /**
     * 校验包含sql不可包含单独换行、单独行@where_rep@
     * @param tsvcSql
     */
    public static void valid(TsvcSql tsvcSql) throws Exception {
        if (StringUtils.isNotNullAndNotEmpty(tsvcSql.getC_sqlstatement()) ){
            validSql(tsvcSql.getC_sqlstatement());
        }
        if (StringUtils.isNotNullAndNotEmpty(tsvcSql.getC_sqlstatement_mysql()) ){
            validSql(tsvcSql.getC_sqlstatement_mysql());
        }

    }
    private static void validSql(String sql) throws Exception {
        sql+=" ";
        Set<String> set=new HashSet<>();
        /**
         * 1、判断单独换行，
         */
        String[] sqlwords=sql.split("\r\n");
        set.addAll(Arrays.asList(sqlwords));

        sqlwords=sql.split("\r");
        set.addAll(Arrays.asList(sqlwords));

        sqlwords=sql.split("\n");
        set.addAll(Arrays.asList(sqlwords));
        for(String str : set){
            str=str.replaceAll(" ","").toUpperCase();
            if(StringUtils.isNullOrEmpty(str)){
                throw new Exception("不允许出现空行");
            }
            if("@WHERE_REP@".equals(str)){
                throw new Exception("@WHERE_REP@不允许独占一行");
            }

        }

    }
}
