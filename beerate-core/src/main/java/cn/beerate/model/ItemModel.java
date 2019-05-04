package cn.beerate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class ItemModel extends Model {

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

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus.name();
    }

}
