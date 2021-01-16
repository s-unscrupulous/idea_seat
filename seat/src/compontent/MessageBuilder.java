package compontent;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;


public class MessageBuilder {
    /**
     * 自己的一个返回消息主体
     */
    private String body;

    /**
     * 存放参数变量的数组
     */
    private Object[] params;

    /**
     * 构造方法
     */
    public MessageBuilder() {
        this.body = "这是一个MessageBuilder模板，使用占位符表示:变量1({0}),变量2({1})";
    }

    /**
     * 设置消息主体
     *
     * @param body 消息主体
     * @return MessageBuilder.this
     */
    public MessageBuilder setBody(String body) {
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("消息主体不能为空！");
        } else {
            this.body = body;
        }

        return this;
    }

    /**
     * 设置变量
     *
     * @param params 变量参数们
     * @return MessageBuilder.this
     */
    public MessageBuilder setVariables(Object... params) {
        Objects.requireNonNull(params);
        for (Object o : params) {
            Objects.requireNonNull(o, "变量参数不能为空！");
        }

        this.params = params;
        return this;
    }

    /**
     * build出String字符串的方法
     *
     * @return String  完整提示消息
     * @see #toString()
     */
    public final String build() {
        return toString();
    }

    /**
     * build出String字符串的方法
     *
     * @return String  完整提示消息
     */
    @Override
    public String toString() {
        return MessageFormat.format(body, params);
    }
}
