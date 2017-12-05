
import java.io.Serializable;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danid
 */
public class Message implements Serializable{
    static int STRING_MESSAGE=1,ID_MESSAGE=2;
    int type;
    String id;
    String text;
    Date sentDate;
    
    public Message()
    {}

    public Message(int type, String id, String text, Date sentDate) {
        this.type = type;
        this.id = id;
        this.text = text;
        this.sentDate = sentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
