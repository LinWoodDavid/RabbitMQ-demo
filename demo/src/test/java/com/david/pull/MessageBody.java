package com.david.pull;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Athor weimc
 * @CreateTime 2019/8/8 10:03
 * @Description: 收到的消息
 */
public class MessageBody implements Serializable {

    private Long tag;
    private String msg;

    public MessageBody() {
    }

    public MessageBody(Long tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageBody)) return false;
        MessageBody that = (MessageBody) o;
        return Objects.equals(tag, that.tag) &&
            Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, msg);
    }

    @Override
    public String toString() {
        return "MessageBody{" +
            "tag=" + tag +
            ", msg='" + msg + '\'' +
            '}';
    }
}


