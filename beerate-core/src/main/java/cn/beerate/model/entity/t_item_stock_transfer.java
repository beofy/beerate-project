package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_stock_transfer",comment="老股转让表")
public class t_item_stock_transfer extends Model {

    /** 标的名称 */
    private String bid_name;

    /** 标的所属阶段 */
    @Column(columnDefinition = " bit(1) COMMENT '标的所属阶段 1 - 已上市 2-未上市'")
    private Boolean is_quoted;

    /** 企业名称是否公开 */
    @Column(columnDefinition = " bit(1) COMMENT '企业名称是否公开 1 - 公开 2-未公开'")
    private Boolean companyNameIsPublic;

    /** 企业名称 */
    private String companyName;

    /** 所属领域： */
    private Integer realm;

    /** 选择币种 0- 未选择 1-人名币 2-美元 */
    @Column(columnDefinition = " int(1) COMMENT '选择币种 1 - 人名币 2 - 美元'")
    private Integer currency;

    /** 去年净利润 */
    private Double lastYearProfits;

    /** 本轮估值 */
    private Double currentAppraisement;

    /** 转让金额 */
    private Double transferAmount;

    /** 转让股权比例是否保密 */
    private boolean isPrivaryEquityRatio;

    /** 转让股权比例 */
    private Double equityRatio;

    /** 投资亮点 */
    private String investLightspot;

    /** 联系人 */
    private String contact;

    /** 联系人电话 */
    private String contactMobile;

    /** 内容描述 */
    private String contentDescription;

    /** 项目结束时间 */
    private Date endTime;

    /** 是否加急 */
    private Boolean isUrgent;

    /** 查看是否需要平台认证 */
    private Boolean isPlatformAuthentication;

    /** 是否一手 */
    private Boolean isFirstHandle;

    /** 是否前台展示 */
    private Boolean isShow;

    private Boolean isAudit;

    //==========================================

    @ManyToOne
    @JoinColumn(name = "user_id")
    private t_user user;

    //==========================================

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private t_admin admin;

    //==========================================

    @OneToMany
    @JoinColumn(name = "stock_transfer_id")
    private List<t_item_message_board> message_boards;

}
