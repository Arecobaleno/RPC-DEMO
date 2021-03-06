package org.example.client.param;

import java.util.concurrent.atomic.AtomicLong;

public class ClientRequest {
    private final long id;
    private Object content; // 方法的输入参数
    private String command; // 要调用的方法

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private final AtomicLong aid = new AtomicLong(1);

    public ClientRequest(){
        id = aid.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
