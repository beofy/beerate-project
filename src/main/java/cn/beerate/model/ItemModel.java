package cn.beerate.model;

import cn.beerate.model.entity.t_admin;
import cn.beerate.model.entity.t_user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class ItemModel extends Model {

    @Column(columnDefinition = "datetime default null comment '项目结束时间'")
    private Date endTime;

    @Column(columnDefinition = "bit not null default 0 comment '是否加急'")
    private Boolean isUrgent;

    @Column(columnDefinition = "bit not null default 0 comment '查看是否需要平台认证'")
    private Boolean isPlatformAuthentication;

    @Column(columnDefinition = "bit not null default 1 comment '是否一手'")
    private Boolean isFirstHandle;

    @Column(columnDefinition = "bit not null default 1 comment '是否前台展示'")
    private Boolean isShow;

    @Column(columnDefinition = "varchar(12) not null default '' comment '审核状态'")
    private String auditStatus;

    @Column(columnDefinition = "varchar(500) not null default '' comment '审核描述'")
    private String description;

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus.name();
    }

    public AuditStatus getAuditStatus(){
       return EnumUtils.getEnumIgnoreCase(AuditStatus.class,this.auditStatus);
    }

    //==========================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private t_user user;

    //==========================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private t_admin admin;

}
