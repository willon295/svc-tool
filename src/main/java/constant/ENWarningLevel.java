package constant;

/**
 * Created by lyd on 2017-06-30.
 */
public enum ENWarningLevel {
        INFO,
        WARNING,
        ERROR
    ;

    private String cmd;


    ENWarningLevel() {

    }
    ENWarningLevel(String cmd) {
        this.cmd = cmd;
    }
    public String getCmd(){
        return this.cmd;
    }
}
