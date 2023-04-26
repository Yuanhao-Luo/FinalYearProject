package com.example.watchvideo.Controller;

public class Error {
    private int error_id;
    private String error_msg;

    public Error(int error_id, String error_msg){
        this.error_id = error_id;
        this.error_msg = error_msg;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
