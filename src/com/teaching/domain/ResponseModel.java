package com.teaching.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 显示数据的实体类
 *
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class ResponseModel<M> implements Serializable {
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    private Integer code;//结果码
    private String msg;//结果消息
    private Integer count;//总数据量
    private Integer currentPage;//当前页
    private Integer perPageSize;//每页数量
    private List<M> data;//当前页数据量

    private ResponseModel() {
        this.code = SUCCESS;
        this.msg = "ok";
    }

    public ResponseModel(Integer count, Integer currentPage, Integer perPageSize) {
        this();
        this.count = count;
        this.currentPage = currentPage;
        this.perPageSize = perPageSize;
    }

    private ResponseModel(Integer code, String msg, Integer count, List<M> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getPageStart() {
        return (currentPage == null || perPageSize == null) ? 0 : (currentPage - 1) * perPageSize + 1;
    }

    public Integer getPageEnd() {
        return (currentPage == null || perPageSize == null) ? 0 : (currentPage * perPageSize);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<M> getData() {
        return data;
    }

    public void setData(List<M> data) {
        this.data = data;
    }

    /**
     * 失败
     *
     * @param <M>
     * @return
     */
    public static <M> ResponseModel<M> buildError() {
        return new ResponseModel<>(ERROR, "获取失败", 0, null);
    }

    /**
     * 获取所有的数据
     *
     * @param count
     * @param data
     * @param <M>
     * @return
     */
    public static <M> ResponseModel<M> buildSuccess(Integer count, List<M> data) {
        return new ResponseModel<M>(SUCCESS, "ok", count, data);
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
