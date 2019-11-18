package com.hdt.example_assess.response;

public class SuccessfulResponse {
    private String status;
    private Object data;

    public SuccessfulResponse() {
    }

    public SuccessfulResponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
