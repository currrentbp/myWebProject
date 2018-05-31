package com.currentbp.entity;

import java.io.Serializable;

/**
 * @author baopan
 * @createTime 20180531
 */
public class BpTest implements Serializable{
    private static final long serialVersionUID = 8673255925289267621L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
