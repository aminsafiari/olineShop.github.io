package amin.shop.app.helper.ui;

import amin.shop.app.enums.ResponseStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T> implements Serializable {
    private List<T> dataList;
    private ResponseStatus status;
    private boolean hasError;
    private String message;
    //because pagination need variable totalCount.
    private long totalCount;

    public ServiceResponse(ResponseStatus status, List<T> dataList) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 0;
    }

    public ServiceResponse(ResponseStatus status, List<T> dataList, long totalCount) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = totalCount;
    }

    public ServiceResponse(ResponseStatus status, T data) {
        this.dataList = new ArrayList<T>();
        this.dataList.add(data);
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 1;
    }

    //if error status use this constructor.
    public ServiceResponse(ResponseStatus status, String message) {
        this.dataList = new ArrayList<T>();
        this.status = status;
        this.message = message;
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 0;
    }

    public ServiceResponse(Exception ex) {
        this.dataList = new ArrayList<T>();
        this.status = ResponseStatus.EXCEPTION;
        this.message = ex.getMessage();
        this.hasError = true;
        this.totalCount = 0;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
