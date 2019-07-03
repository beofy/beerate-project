package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_platform_log",comment="平台操作日志")
public class t_platform_log extends Model {

    @Column(columnDefinition = "text not null  comment '操作者'")
    private String operator;

    @Column(columnDefinition = "varchar(5000) not null default '' comment '请求地址'")
    private String action;

    @Column(columnDefinition = "varchar(5000) not null default '' comment '请求参数'")
    private String params;

    @Column(columnDefinition = "text not null  comment '处理结果'")
    private String result;

    @Column(columnDefinition = "varchar(20) not null default '' comment '执行时间'")
    private String executionTime;

    @Column(columnDefinition = "varchar(64) not null default '' comment 'ip地址'")
    private String ipAddr;

    @Column(columnDefinition = "varchar(64) not null default '' comment '操作者类型'")
    private String operatorType;

}
