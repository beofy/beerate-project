package cn.beerate.model.entity;

import cn.beerate.model.Model;
import cn.beerate.model.RightColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_right",comment="权限表")
public class t_right extends Model {
    @Column(columnDefinition = "varchar(255) not null default '' comment '权限地址'")
    private String action;

    @Column(columnDefinition = "varchar(255) not null default '' comment '权限描述'")
    private String description;

    @Column(columnDefinition = "varchar(30) not null default '' comment '权限描述'")
    private String rightColumn;

    public RightColumn getRightColumn() {
        return EnumUtils.getEnumIgnoreCase(RightColumn.class,this.rightColumn);
    }

    public void setRightColumn(RightColumn rightColumn) {
        this.rightColumn = rightColumn.name();
    }
}
