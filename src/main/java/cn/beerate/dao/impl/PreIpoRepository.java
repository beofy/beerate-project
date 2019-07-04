package cn.beerate.dao.impl;

import cn.beerate.model.dto.MyPreIpo;
import cn.beerate.model.dto.PreIpo;
import cn.beerate.model.dto.PreIpoDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PreIpoRepository {
    /**
     * 我的项目列表
     */
    Page<MyPreIpo> pageMyPreIpoByUser(Pageable pageable, long userId);

    /**
     * 查询项目详情
     */
    PreIpoDetail preIpoDetailByUser(long preIpoId, long userId);


    /**
     * 项目集列表
     */
    Page<PreIpo> pagePreIpo(Pageable pageable);
}
