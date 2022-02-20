package com.teck.kai.controller;

import com.teck.kai.entity.*;
import com.teck.kai.dao.HistoricalDao;
import org.apache.commons.lang3.StringUtils;
import org.paukov.combinatorics3.Generator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StockController {

    private final HistoricalDao historicalDao;
    private final DataSource dataSource;

    public StockController(HistoricalDao historicalDao, DataSource dataSource) {
        this.historicalDao = historicalDao;
        this.dataSource = dataSource;
    }

    @PostMapping("/addData")
    @Transactional
    public boolean addData(@RequestBody List<HistoricalBean> historicalBeanList){
        Calendar calendar= Calendar.getInstance();
//        calendar.add(Calendar.HOUR, 8);
        calendar.add(Calendar.DATE, 1);

        for(HistoricalBean bean: historicalBeanList){
            bean.setUpdatedate(calendar.getTime());
            historicalDao.create(bean);
        }

        return true;
    }

    @PostMapping("/addDataWithDate")
    @Transactional
    public boolean addData(@RequestBody List<HistoricalBean> historicalBeanList,
                           @RequestParam int day){
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);

        for(HistoricalBean bean: historicalBeanList){
            bean.setUpdatedate(calendar.getTime());
            historicalDao.create(bean);
        }

        return true;
    }

    @GetMapping("/get")
    @Transactional
    public HistoricalBean get(){
        return historicalDao.get(2121);
    }

    @GetMapping("/getAll")
    @Transactional
    public Map<String, List<HistoricalBean>> getAll(){
        List<HistoricalBean> data= historicalDao.getAll();

        return data.stream().collect(Collectors.groupingBy(HistoricalBean::getName));
    }

    @PostMapping("/saveBuyList")
    @Transactional
    public boolean saveBuyList(@RequestBody List<BuyListBean> historicalBeanList){
        for(BuyListBean bean: historicalBeanList){
            historicalDao.createBuy(bean);
        }

        return true;
    }

    @PostMapping("/saveSellList")
    @Transactional
    public boolean saveSellList(@RequestBody List<SellListBean> historicalBeanList){
        for(SellListBean bean: historicalBeanList){
            historicalDao.createSell(bean);
        }

        return true;
    }

    @GetMapping("/getBuyList")
    @Transactional
    public List<BuyListBean> getBuyList(){
        return historicalDao.getBuyList();
    }

    @GetMapping("/createCombi")
    @Transactional
    public void createCombi(int index){
        Generator.combination("cci20","recommendall","aroondown","aroonup","chaikinmoneyflow","adx","perf3m","perf6m",
                "ao","bbpower","macdmacd","macdsignal","mom","moneyflow","perf1m","recommendma","adxdin","recommendother",
                "adxdip","relativevolume10dcalc","stochd","stochk","stochrsik","stochrsid","uo","volatilityd","volatilitym","volatilityw",
                "vwma","perfytd")
                .simple(index)
                .stream()
                .forEach(value -> historicalDao.createCombination(new CombinationBean(StringUtils.join(value, ","))));

    }

    @GetMapping("/getColumn")
    @Transactional
    public Map<String, String> getColumn(){
        JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);

        String result= jdbcTemplate.queryForObject("Select fields from combination where enable=1 order by id limit 1", String.class);

        jdbcTemplate.update("update combination set enable= 0 where enable= 1 order by id limit 1");

        Map<String, String> response= new HashMap<>();
        response.put("data", result);

        return response;
    }

    @GetMapping("/updateColumnScore")
    @Transactional
    public void updateColumnScore(@RequestParam(required = false) BigDecimal score, @RequestParam(required = false) BigDecimal scorev2, @RequestParam String fields) {
        JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);

        if(score != null) {
            jdbcTemplate.update("update combination set score= " + score + " where fields='" + fields + "'");
        }else{
            jdbcTemplate.update("update combination set scorev2= " + scorev2 + " where fields='" + fields + "'");
        }
    }

    @GetMapping("/getBuyListV2")
    @Transactional
    public List<BuyListV2Bean> getBuyLisV2(){
        return historicalDao.getBuyListV2();
    }

    @GetMapping("/getHistoricalListV2")
    @Transactional
    public List<HistoricalV2Bean> getHistoricalListV2(){
        return historicalDao.getHistoricalListV2();
    }

}
