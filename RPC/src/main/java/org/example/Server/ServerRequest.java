package org.example.Server;

import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;

public class ServerRequest {
    private Long id;
    private Object content;
    private String command; // 类.方法

    public Long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
