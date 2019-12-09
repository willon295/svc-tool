package util;



import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtil {

    private static int seed = 0;// 作为产生唯一码的种子
    private static final Object object = new Object();

    /**
     * 集群环境下Util.genAbstract(String args0)
     * 标识(5,6,7,8,9,Z,Y,X,W,V,U,T,S,R,Q,P,O,N)
     */
    public static final char CLUSTERIDENTITY = '5';

    private CommonUtil() {
        super();
    }

    /**
     * 数字转为会计格式
     */
    public static String changeNumToMoney(BigDecimal money) {
        DecimalFormat dec = new DecimalFormat("##,##0.00");
        return dec.format(money);
    }

    /**
     * 求拆分次数和余额 如果整除：10000/5000 返回2 余数5000 注意整除余数不是0 ！！！！！ 根据和顾总商量修改
     */
    public static List<String> getSumAndRemainder(double upstreamfunds,
                                                  double splitmoney) {
        List<String> list = new ArrayList<String>();
        list.add((int) (upstreamfunds % splitmoney > 0 ? (upstreamfunds / splitmoney) + 1
                : upstreamfunds / splitmoney)
                + "");
        if (new BigDecimal(upstreamfunds % splitmoney).setScale(2,
                BigDecimal.ROUND_HALF_UP).compareTo(
                new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0) {
            list.add(new BigDecimal(splitmoney).setScale(2,
                    BigDecimal.ROUND_HALF_UP) + "");
        } else {
            list.add(new BigDecimal(upstreamfunds % splitmoney).setScale(2,
                    BigDecimal.ROUND_HALF_UP) + "");
        }
        return list;
    }

    /**
     * 求拆分次数和余数
     */
    public static List<Integer> getSumAndRemainder(int upstreamfunds,
                                                   int splitmoney) {
        List<Integer> list = new ArrayList<Integer>();
        list.add((int) (upstreamfunds % splitmoney > 0 ? (upstreamfunds / splitmoney) + 1
                : upstreamfunds / splitmoney));
        list.add(upstreamfunds % splitmoney);
        return list;
    }

    /**
     * 求上划(支付)金额
     */
    public static Double getAmountAfteIntRate(Double bkBlance,
                                              Double resvBalance, int intRate) {
        Double amountAfterIntRate = 0.0;
        Double amountAfter = Math.round((bkBlance - resvBalance) * 100) * 1.0 / 100;
        switch (intRate) {
            case 1:// 万
                amountAfterIntRate = Math.floor(amountAfter / 10000) * 10000;
                break;
            case 2:// 千
                amountAfterIntRate = Math.floor(amountAfter / 1000) * 1000;
                break;
            case 3:// 百
                amountAfterIntRate = Math.floor(amountAfter / 100) * 100;
                break;
            case 4:// 十
                amountAfterIntRate = Math.floor(amountAfter / 10) * 10;
                break;
            default:
                amountAfterIntRate = amountAfter;
                break;
        }
        return amountAfterIntRate;
    }

    /**
     * 取整
     *
     * @param amount      金额
     * @param integerRate 取整等级
     * @return
     */
    public static double getAmountByIntRate(double amount, int integerRate) {
        switch (integerRate) {
            case 1:// 万
                return Math.floor(amount / 10000) * 10000;
            case 2:// 千
                return Math.floor(amount / 1000) * 1000;
            case 3:// 百
                return Math.floor(amount / 100) * 100;
            case 4:// 十
                return Math.floor(amount / 10) * 10;
            default:
                return amount;
        }
    }

    /**
     * @Title: checkPeriodDate
     * @Description:(判断source 是否在start 和end 之间)
     */
    public static boolean checkPeriodDate(Date start, Date end, Date source) {
        boolean flag = false;
        if (start.before(source) && end.after(source)) {
            flag = true;
        }
        return flag;
    }

    /**
     * @Title: getSplitNumInMap
     * @Description: 在map中获取银行发报文拆分账户数
     */
    public static int getSplitNumInMap(Map<String, Object> map) {
        if (map.isEmpty()) {
            return 1;
        } else {
            if (StringUtils.isEmpty(String.valueOf(map.get("splitnum")))) {
                return 1;
            }
            return Integer.parseInt(String.valueOf(map.get("splitnum")));

        }
    }









    /**
     * @Title: getSysGuid
     * @Description: 获取下一个uuid
     */
    public static String getSysGuid() {
        // String retstr = "";
        // String sql = "select rawtohex(sys_guid()) as sysguid from dual";
        // Map<String, Object> map = (Map<String, Object>)JQuery.query(sql, new
        // MapHandler());
        // if(map!=null&&!map.isEmpty()){
        // if(map.get("sysguid")!=null&&!map.get("sysguid").equals("")){
        // retstr = map.get("sysguid").toString();
        // }
        // }
        return StringUtils.UUID();
    }

    public static Object getSeqID() {
        Date date = new Date();
        String hour = "";
        String minute = "";
        String second = "";
        if (date.getHours() < 10) {
            hour = "0" + date.getHours();
        } else {
            hour = String.valueOf(date.getHours());
        }
        if (date.getMinutes() < 10) {
            minute = "0" + date.getMinutes();
        } else {
            minute = String.valueOf(date.getMinutes());
        }

        if (date.getSeconds() < 10) {
            second = "0" + date.getSeconds();
        } else {
            second = String.valueOf(date.getSeconds());
        }
        String seqId = hour + minute + second;
        return seqId + new Random().nextInt(10000);
    }






    /**
     * 产生 select、delete、update 时候where 语句中in部分的String 长度可以超1000<br/>
     * <br/>
     * <p/>
     * ex:" where "+this.getInString(list, true, "a.name", null);<br/>
     * = where (a.name in ('list.1', 'list.2', 'list.3'……) or a.name in
     * ('list.1001', 'list.1002'...))
     * <p/>
     * <br/>
     *
     * @param <X>
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @param name    the column name
     * @param size    default is 1000 for oracle
     * @param condition    只接受 in或者 not in(不区分大小写) ，其它情况则使用默认 in，
     * @return
     * @author ylc
     */
    public static <X extends Collection<T>, T> String getInOrNotInString(X ids,
                                                                     boolean addSemi, String name, Integer size,String condition) {
        if(!StringUtils.isNull(condition)){
            if( condition.equalsIgnoreCase("not in")) {
                 return getNotInString(ids, addSemi, name, size);
            }
        }
        return getInString(ids, addSemi, name, size);
    }
    /**
     * 产生 select、delete、update 时候where 语句中in部分的String 长度可以超1000<br/>
     * <br/>
     * <p/>
     * ex:" where "+this.getInString(list, true, "a.name", null);<br/>
     * = where (a.name in ('list.1', 'list.2', 'list.3'……) or a.name in
     * ('list.1001', 'list.1002'...))
     * <p/>
     * <br/>
     *
     * @param <X>
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @param name    the column name
     * @param size    default is 1000 for oracle
     * @return
     * @author ylc
     */
    public static <X extends Collection<T>, T> String getNotInString(X ids,
                                                                  boolean addSemi, String name, Integer size) {
        if (ids == null || ids.size() == 0) {
            return null;
        }

        List<List<T>> idGroup = splitList1(ids, size);

        StringBuilder sql = new StringBuilder();
        sql.append(" (");
        for (List<T> idList : idGroup) {
            sql.append(name + " not in (");
            StringBuilder subsql = new StringBuilder();
            for (Object id : idList) {
                if (id == null) {
                    continue;
                }
                if (addSemi) {
                    subsql.append("'").append(id.toString()).append("',");
                } else {
                    subsql.append(id.toString()).append(",");
                }
            }
            String temp = subsql.toString();
            sql.append(temp.substring(0, temp.length() - 1)).append(')');
            if (idGroup.indexOf(idList) < idGroup.size() - 1) {
                sql.append(" or ");
            }
        }
        sql.append(") ");
        return sql.toString();
    }
    /**
     * 产生 select、delete、update 时候where 语句中in部分的String 长度可以超1000<br/>
     * <br/>
     * <p/>
     * ex:" where "+this.getInString(list, true, "a.name", null);<br/>
     * = where (a.name in ('list.1', 'list.2', 'list.3'……) or a.name in
     * ('list.1001', 'list.1002'...))
     * <p/>
     * <br/>
     *
     * @param
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @param name    the column name
     * @param size    default is 1000 for oracle
     * @return
     * @author ylc
     */
    public static <T> String getInString(T[] ids, boolean addSemi, String name,
                                         Integer size) {
        List<T> list = Arrays.asList(ids);
        return getInString(list, addSemi, name, size);
    }

    /**
     * 产生 select、delete、update 时候where 语句中in部分的String 长度可以超1000<br/>
     * <br/>
     * <p/>
     * ex:" where "+this.getInString(list, true, "a.name", null);<br/>
     * = where (a.name in ('list.1', 'list.2', 'list.3'……) or a.name in
     * ('list.1001', 'list.1002'...))
     * <p/>
     * <br/>
     *
     * @param
     * @param
     * @param addSemi 是否单引号，
     * @param name    the column name
     * @param size    default is 1000 for oracle
     * @return
     * @author ylc
     */
    public static String getInString(String source, String split,
                                     boolean addSemi, String name, Integer size) {
        String[] sourceStrs = source.split(split);
        return getInString(sourceStrs, addSemi, name, size);
    }

    /**
     * 产生 select、delete、update 时候where 语句中in部分的String 长度可以超1000<br/>
     * <br/>
     * <p/>
     * ex:" where "+this.getInString(list, true, "a.name", null);<br/>
     * = where (a.name in ('list.1', 'list.2', 'list.3'……) or a.name in
     * ('list.1001', 'list.1002'...))
     * <p/>
     * <br/>
     *
     * @param <X>
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @param name    the column name
     * @param size    default is 1000 for oracle
     * @return
     * @author ylc
     */
    public static <X extends Collection<T>, T> String getInString(X ids,
                                                                  boolean addSemi, String name, Integer size) {
        if (ids == null || ids.size() == 0) {
            return null;
        }

        List<List<T>> idGroup = splitList1(ids, size);

        StringBuilder sql = new StringBuilder();
        sql.append(" (");
        for (List<T> idList : idGroup) {
            sql.append(name + " in (");
            StringBuilder subsql = new StringBuilder();
            for (Object id : idList) {
                if (id == null) {
                    continue;
                }
                if (addSemi) {
                    subsql.append("'").append(id.toString()).append("',");
                } else {
                    subsql.append(id.toString()).append(",");
                }
            }
            String temp = subsql.toString();
            sql.append(temp.substring(0, temp.length() - 1)).append(')');
            if (idGroup.indexOf(idList) < idGroup.size() - 1) {
                sql.append(" or ");
            }
        }
        sql.append(") ");
        return sql.toString();
    }

    /**
     * 产生 select、delete、update 时候where 语句中in部分的String
     *
     * @param ids     数组类型的 值列表
     * @param addSemi 是否单引号，
     * @return
     */
    public static String getInString(Object[] ids, boolean addSemi) {
        if (ids == null || ids.length == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" in (");
        for (Object id : ids) {
            if (addSemi) {
                sql.append("'").append(id.toString()).append("',");
            } else {
                sql.append(id.toString()).append(",");
            }
        }
        String result = sql.toString();
        result = result.substring(0, result.length() - 1) + ")";
        return result;
    }

    /**
     * 产生 select、delete、update 时候where 语句中in部分的String
     *
     * @param <X>
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @return
     */
    public static <X extends Collection<?>> String getInString(X ids,
                                                               boolean addSemi) {
        if (ids == null || ids.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" in (");
        for (Object id : ids) {
            if (id == null) {
                continue;
            }
            if (addSemi) {
                sql.append("'").append(id.toString()).append("',");
            } else {
                sql.append(id.toString()).append(",");
            }
        }
        String result = sql.toString();
        result = result.substring(0, result.length() - 1) + ")";
        return result;
    }

    /**
     * 产生 select、delete、update 时候where 语句中in部分的String，不包含 in 本身
     * @param <X>
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @return
     */
    public static <X extends Collection<?>> String getExclusiveInString(X ids,
                                                               boolean addSemi) {
        if (ids == null || ids.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder();     
        for (Object id : ids) {
            if (id == null) {
                continue;
            }
            if (addSemi) {
                sql.append("'").append(id.toString()).append("',");
            } else {
                sql.append(id.toString()).append(",");
            }
        }
        String result = sql.toString();
        result = result.substring(0, result.length() - 1);
        return result;
    }
    
    /**
     * 产生 select、delete、update 时候where 语句中in部分的String
     *
     * @param <X>
     * @param ids     collection类型的 值列表
     * @param addSemi 是否单引号，
     * @return
     */
    public static <X extends Collection<?>> String getInStringOther(X ids,
                                                                    boolean addSemi) {
        if (ids == null || ids.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("in(");
        for (Object id : ids) {
            if (id == null) {
                continue;
            }
            if (addSemi) {
                sql.append("'").append(id.toString()).append("',");
            } else {
                sql.append(id.toString()).append(",");
            }
        }
        String result = sql.toString();
        result = result.substring(0, result.length() - 1) + ")";
        return result;
    }

    /**
     * list 转换成用逗号隔开的string
     *
     * @param <X>
     * @return
     */
    public static <X extends Collection<?>> String getInString(X ids) {
        if (ids == null || ids.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder();
        for (Object id : ids) {
            if (id == null) {
                continue;
            }
            sql.append(id.toString()).append(",");
        }
        String result = sql.toString();
        result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * 将集合拆分成指定长度的 “集合的集合”
     *
     * @param list
     * @param size 默认为1000
     * @return List&lt;List&lt;Object>>
     */
    public static <X extends Collection<T>, T> List<List<T>> splitList1(X list,
                                                                        Integer size) {
        if (size == null) {
            size = Integer.valueOf(1000);
        }
        List<List<T>> idGroup = new ArrayList<List<T>>();
        List<T> tmp = null;
        int i = 0;
        for (T id : list) {
            if (i % size == 0) {
                tmp = new ArrayList<T>();
                idGroup.add(tmp);
            }
            tmp.add(id);
            ++i;
        }
        return idGroup;
    }

    /**
     * @param @param  date
     * @param @return
     * @return String 返回类型
     * @throws
     * @Title: getDateTimeString
     * @Description: 返回格式为：yyyy-MM-dd hh:mm:ss 的字符串
     * @version 创建时间：2010-10-23 下午03:00:18
     */
    public static String getDateTimeString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * @param @param  date
     * @param @return
     * @return String 返回类型
     * @throws
     * @Title: getDateTimeString
     * @Description: 返回格式为：yyyy-MM-dd 的字符串
     * @version 创建时间：2010-10-23 下午03:00:18
     */
    public static String getDateString(Date date, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * @param @param  source
     * @param @return
     * @return Date 返回类型
     * @throws
     * @Title: getDateTimeString
     * @Description: 将字符串转成时间类型
     * @version 创建时间：2010-11-16 上午11:38:54
     */
    public static Date getDateTimeString(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (Exception e) {
            return date;
        }
        return date;
    }

    /**
     * @param @param  source
     * @param @return
     * @return Date 返回类型
     * @throws
     * @Title: getDateTimeString
     * @Description: 将字符串转成时间类型
     * @version 创建时间：2010-11-16 上午11:38:54
     */
    public static Date getDateString(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            return date;
        }
        return date;
    }

    /**
     * @param date
     * @param defaultDatee 为空返回默认值
     * @return
     */
    public static Date getDateStringCommon(Object date, Date defaultDatee) {
        if (date == null) {
            return defaultDatee;
        }
        Date tmp = getDateStringCommon(date.toString());
        return tmp == null ? defaultDatee : tmp;
    }

    /**
     * 将日期字符串转为日期
     *
     * @param date
     * @return
     */
    public static Date convertToDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        if (date.contains("-")) {
            //2016-03-21 00:01:40这种格式不支持，要转成斜杠
            date = date.replace('-', '/');
        }

        return new Date(date);
    }

    /**
     * 根据date str的长度判断出格式转化为date
     *
     * @param date
     * @return
     */
    public static Date getDateStringCommon(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = null;
        if (date.length() == 8) {
            if (date.indexOf('-') > 0
                    && date.indexOf('-') != date.lastIndexOf('-')) {
                simpleDateFormat = new SimpleDateFormat("yyyy-M-d");
            } else if (date.indexOf('/') > 0
                    && date.indexOf('/') != date.lastIndexOf('/')) {
                simpleDateFormat = new SimpleDateFormat("yyyy/M/d");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            }
        } else if (date.length() == 9) {
            if (date.indexOf('-') > 0
                    && date.indexOf('-') != date.lastIndexOf('-')) {
                if (date.lastIndexOf('-') - date.indexOf('-') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-d");
                } else if (9 - date.lastIndexOf('-') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
                }
            } else if (date.indexOf('/') > 0
                    && date.indexOf('/') != date.lastIndexOf('/')) {
                if (date.lastIndexOf('/') - date.indexOf('/') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy/MM/d");
                } else if (9 - date.lastIndexOf('/') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");
                }
            }
        } else if (date.length() == 10 && date.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (date.length() == 10 && date.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        } else if (date.length() == 19 && date.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (date.length() == 20 && date.indexOf('.') == 18
                && date.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.f");
        } else if (date.length() == 23 && date.indexOf('.') == 18
                && date.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.fff");
        } else if (date.length() == 19 && date.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if (date.length() == 20 && date.indexOf('.') == 18
                && date.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.f");
        } else if (date.length() == 23 && date.indexOf('.') == 18
                && date.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.fff");
        } else if (date.length() == 15) {
            simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        } else if (date.length() == 14) {
            simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        }
        if (!(date.indexOf(".") > -1) && !(date.indexOf("-") > -1) && !(date.indexOf("/") > -1)) {
            simpleDateFormat = findString2dateFormat(date);
        }
        Date d = null;
        try {
            if (simpleDateFormat == null) {
               // log.error("无法识别日期格式！");
            } else {
                d = simpleDateFormat.parse(date);
            }
        } catch (ParseException e) {
            //log.error("解析日期异常", e);
            return d;
        } catch (Exception e) {
            //log.error("解析日期异常", e);
            return d;
        }
        return d;
    }

    private static SimpleDateFormat findString2dateFormat(String target) {
        //无法区分2015111、
        //yyyyMMd HH:mm:ss
        //yyyyMdd HH:mm:ss
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(target);
        String regex = "";
        if (matcher.find()) {
            int len = target.length();
            if (!(target.indexOf(" ") > -1))
                if (len == 14)
                    regex = "yyyyMdHH:mm:ss";
                else
                    regex = "yyyyMMddHH:mm:ss";
            else if (len == 15)
                regex = "yyyyMd HH:mm:ss";
            else
                regex = "yyyyMMdd HH:mm:ss";
        }
        return new SimpleDateFormat(regex);
    }


    public static String file2str(String path1, String fileEncoding,
                                  String errInfo) throws IOException {
        StringBuilder sb = new StringBuilder();

        File file = new File(path1);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.exists()) {
            FileInputStream fs = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fs, fileEncoding);
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            fs.close();
            isr.close();
            br.close();

        } else {
            //PubLogManager.getInstance().getLog().error(errInfo);
        }
        return sb.toString();
    }

    public static String TrimEnd(String str, String trim) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(trim)) {
            return str;
        }
        while (str.endsWith(trim)) {
            if (StringUtils.isEmpty(str) || StringUtils.isEmpty(trim)) {
                break;
            }
            str = str.substring(0, str.length() - trim.length());
        }
        return str;
    }

   /* *//**
     * xml 内容填充到map中
     *
     * @param
     * @param map
     * @param
     * @throws ParseException
     *//*
    public static void xml2map(String xml, Map<String, Date> map,
                               boolean cover, String errInfo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Document d = null;
        try {
            d = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            PubLogManager.getInstance().getLog().error(errInfo + xml, e);
            return;
        }
        Element root = d.getRootElement();// ATS node
        @SuppressWarnings("unchecked")
        List<Element> list = root.elements("node");
        for (Element node : list) {
            if (cover
                    || (!cover && !map.containsKey(node.attributeValue("key")))) {
                map.put(node.attributeValue("key"), sdf.parse(node.getText()));
            }
        }
        d.clearContent();
    }
*/

    public static StringBuilder map2xml(Map<String, Date> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("<FileHash>\r\n");
        for (Entry<String, Date> entry : map.entrySet()) {
            sb.append("\t<node key='" + entry.getKey() + "'>");
            sb.append(sdf.format(entry.getValue()));
            sb.append("</node>\r\n");
        }

        return sb.append("</FileHash>");
    }




    /*
 * 明细信息重复，请勿重复插入
 */
    public static String exceptionConvert(Throwable ex, String description) {
        Throwable current = ex;
        Throwable cause = ex.getCause();
        while (cause != null) {
            current = cause;
            if (current.getCause().getMessage().indexOf("ORA-00001") != -1) {
                description = "明细信息重复，请勿重复插入!";
                break;
            }
            cause = cause.getCause();
        }
        return description;
    }

    public static List mapvalueToList(Map<String,?> map){

        List list=new ArrayList();
        for(Map.Entry<String,?> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }
}
