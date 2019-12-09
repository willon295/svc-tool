package bean;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-10-11
 */
public class FileInfo {
    private String fileName;
    private String filePath;
    private String content;

    public FileInfo(String fileName, String filePath, String content) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
