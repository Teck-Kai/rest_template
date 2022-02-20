package com.teck.kai.dao;

import com.teck.kai.entity.*;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class HistoricalDaoImpl implements  HistoricalDao{
    private final EntityManager entityManager;

    public HistoricalDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected final Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void create(HistoricalBean historicalBean) {
        getSession().save(historicalBean);
    }

    @Override
    public HistoricalBean get(Integer id){
        return getSession().get(HistoricalBean.class, id);
    }

    @Override
    public List<HistoricalBean> getAll() {
//        return getSession().createSQLQuery("SELECT * from historical").getResultList();
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<HistoricalBean> cq = cb.createQuery(HistoricalBean.class);
        Root<HistoricalBean> rootEntry = cq.from(HistoricalBean.class);
        CriteriaQuery<HistoricalBean> all = cq.select(rootEntry);

        TypedQuery<HistoricalBean> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void createBuy(BuyListBean buyListBean) {
        getSession().save(buyListBean);
    }

    @Override
    public void createSell(SellListBean sellListBean) {
        getSession().save(sellListBean);
    }

    @Override
    public List<BuyListBean> getBuyList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<BuyListBean> cq = cb.createQuery(BuyListBean.class);
        Root<BuyListBean> rootEntry = cq.from(BuyListBean.class);
        CriteriaQuery<BuyListBean> all = cq.select(rootEntry);

        TypedQuery<BuyListBean> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void createCombination(CombinationBean combinationBean) {
        getSession().save(combinationBean);
    }

    @Override
    public List<BuyListV2Bean> getBuyListV2() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<BuyListV2Bean> cq = cb.createQuery(BuyListV2Bean.class);
        Root<BuyListV2Bean> rootEntry = cq.from(BuyListV2Bean.class);
        CriteriaQuery<BuyListV2Bean> all = cq.select(rootEntry);

        TypedQuery<BuyListV2Bean> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<HistoricalV2Bean> getHistoricalListV2() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<HistoricalV2Bean> cq = cb.createQuery(HistoricalV2Bean.class);
        Root<HistoricalV2Bean> rootEntry = cq.from(HistoricalV2Bean.class);
        CriteriaQuery<HistoricalV2Bean> all = cq.select(rootEntry);

        TypedQuery<HistoricalV2Bean> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
