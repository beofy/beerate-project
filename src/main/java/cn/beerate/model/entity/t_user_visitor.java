package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user_visitor",comment="用户访客表")
public class t_user_visitor extends Model {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private t_user user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_user_id")
    private t_user visitor_user;

}
