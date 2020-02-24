package com.acompe.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GitHub {
    @JSONField(serialize = false)
    private int id;
    @JSONField(serialize = false)
    private String gitHubId;
    @JSONField(serialize = false)
    private int userId;
    private String name;
    private String avatarUrl;
}
