package eshopGery.model;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 21.8.14
 */
public class EmailMessage {

    private String from;
    private String to;
    private String hiddenCopy;
    private String subject;
    private String message;

    private List<File> files;
    
    private Map<String, Object> templateCnx = new HashMap<String, Object>();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHiddenCopy() {
        return hiddenCopy;
    }

    public void setHiddenCopy(String hiddenCopy) {
        this.hiddenCopy = hiddenCopy;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Map<String, Object> getTemplateCnx() {
        return templateCnx;
    }

    public void addTemplateItem(String key, Object value) {
        templateCnx.put(key, value);
    }
}
