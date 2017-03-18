<<<<<<< HEAD
package com.danchu.momuck.view;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ResultView
 *
 * @author geine
 */
public class ResultView {

    private String code;
    private String message;
    private Object data;

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty(value = "data")
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultView(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultView [code=" + code + ", message=" + message + "]";
    }
}
=======
package com.danchu.momuck.view;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ResultView
 *
 * @author geine
 */
public class ResultView {

    private String code;
    private String message;
    private Object data;

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty(value = "data")
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultView(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultView [code=" + code + ", message=" + message + "]";
    }
}

>>>>>>> branch 'feature/#20_review' of https://github.com/GESCC/danchu.git
