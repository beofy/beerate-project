package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_block_trade",comment="大宗交易表")
public class t_item_block_trade extends Model {
    /** 项目名称 */
    private String blockTradeName;

    /** 股票代码 */
    private String stockCode;

    /** 交易折价 */
    private Double exchangeAgioRate;

    /** 减持股数 */
    private Integer underweightShares;

    /** 减持总金额 */
    private Double underweightAmount;

    /** 减持方身份 */
    private  Integer underweightIdentification;

    /** 是否增信 */
    private Boolean isConfidence;

    /** 预期收益率（增信） */
    private Double expectedReturn;

    /** 增信期限（增信） */
    private Date confidencePeriod;

    /** 增信身份（增信） */
    private Integer confidenceIdentification;

    /** 增信方超额收益分成（增信） */
    private  Integer confidenceShare;

    /** 增信是否公开（增信） */
    private Boolean confidenceIsPublic;

    /** 持仓低价 */
    private Double positionLowPrice;

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
    @JoinColumn(name = "block_trade_id")
    private List<t_item_message_board> message_boards;

}
