package exception;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-03-14<br>
 * <br>
 */
public class ViewException extends RuntimeException {
    public ViewException(String message) {
        super(message);
    }

    public ViewException(Throwable cause) {
        super(cause);
    }
}
