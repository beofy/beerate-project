package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyPreIpo;
import cn.beerate.model.dto.PreIpo;
import cn.beerate.model.dto.PreIpoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PreIpoRepositoryImpl implements PreIpoRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyPreIpo> pageMyPreIpoByUser(Pageable pageable, long userId) {
        return null;
    }

    @Override
    public PreIpoDetail preIpoDetailByUser(long preIpoId, long userId) {
        return null;
    }

    @Override
    public Page<PreIpo> pagePreIpo(Pageable pageable) {
        return null;
    }
}
