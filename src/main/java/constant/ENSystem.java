package constant;

/**
 * Created by lyd on 2018-03-31.
 */
public enum ENSystem {
        SAAS("SAAS"),
        ATS("ATS")
    ;

    private String cmd;


    ENSystem() {

    }
    ENSystem(String cmd) {
        this.cmd = cmd;
    }
    public String getSystemName(){
        return this.cmd;
    }
}
