package com.teck.kai.dao;

import com.teck.kai.entity.*;

import java.util.List;

public interface HistoricalDao {

    void create(HistoricalBean historicalBean);
    HistoricalBean get(Integer id);
    List<HistoricalBean> getAll();
    void createBuy(BuyListBean buyListBean);
    void createSell(SellListBean sellListBean);
    List<BuyListBean> getBuyList();
    void createCombination(CombinationBean combinationBean);
    List<BuyListV2Bean> getBuyListV2();
    List<HistoricalV2Bean> getHistoricalListV2();
}
