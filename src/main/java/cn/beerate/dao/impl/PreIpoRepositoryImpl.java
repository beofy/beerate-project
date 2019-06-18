package cn.beerate.dao.impl;

import cn.beerate.dao.support.GenericRepository;
import cn.beerate.model.dto.MyPreIpo;
import cn.beerate.model.dto.PreIpo;
import cn.beerate.model.dto.PreIpoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PreIpoRepositoryImpl implements PreIpoRepository {
    @Autowired
    private GenericRepository genericRepository;

    @Override
    public Page<MyPreIpo> pageMyPreIpoByUser(Pageable pageable, long userId) {
        String querySql="SELECT id, preIpoName, industryRealm, ratchetTerms, isNewThirdBoardListing, iPOBaseDate, auditStatus FROM t_item_pre_ipo WHERE userId = :userId";
        String countSql="SELECT COUNT(1) FROM t_item_pre_ipo WHERE userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("userId",userId);
        return genericRepository.getPage(querySql,countSql,args,pageable, MyPreIpo.class);
    }

    @Override
    public PreIpoDetail preIpoDetailByUser(long preIpoId, long userId) {
        String querySql="SELECT id, preIpoName, isNewThirdBoardListing, stockCode, isPrincipalUnderwriter, isTutoringBrokerage, TutoringBrokerageName, bidName, companyNameIsPublic, companyName, city, industryRealm, iPOBaseDate, ratchetTerms, ratchetTermsDescription, isPriceNegotiable, intentionalPrice, exchangeShares, sharesAmount, thresholdAmount, currency, loanAmount, IntentionValuation, lastYearProfits, loanPeriod, purpose, investLightSpot, businessProposalUri, contact, contactMobile, contentDescription, endTime, isUrgent, isPlatformAuthentication, isFirstHandle, auditStatus, description, adminId, userId FROM t_item_pre_ipo WHERE id = :id AND userId = :userId";

        Map<String,Object> args= new HashMap<>();
        args.put("id",preIpoId);
        args.put("userId",userId);

        return genericRepository.getObject(querySql,args,PreIpoDetail.class);
    }

    @Override
    public Page<PreIpo> pagePreIpo(Pageable pageable) {
        return null;
    }
}
