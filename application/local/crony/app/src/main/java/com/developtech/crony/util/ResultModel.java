package com.developtech.crony.util;

/**
 * Created by Divya on 10-05-2017.
 */
public class ResultModel<T> {
    private T result_data;
    private long result_code;

    public T getResult_data() {
        return result_data;
    }

    public void setResult_data(T result_data) {
        this.result_data = result_data;
    }

    public long getResult_code() {
        return result_code;
    }

    public void setResult_code(long result_code) {
        this.result_code = result_code;
    }
}
