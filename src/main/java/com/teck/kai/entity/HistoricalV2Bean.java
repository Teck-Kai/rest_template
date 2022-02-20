package com.teck.kai.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "historical_v2")
public class HistoricalV2Bean implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private BigDecimal cci20;
  private String recommendall;
  private BigDecimal close;
  private BigDecimal aroondown;
  private BigDecimal aroonup;
  private BigDecimal chaikinmoneyflow;
  private BigDecimal adx;
  private BigDecimal perf3m;
  private BigDecimal perf6m;
  private BigDecimal ao;
  private BigDecimal bbpower;
  private BigDecimal macdmacd;
  private BigDecimal macdsignal;
  private BigDecimal mom;
  private BigDecimal moneyflow;
  private BigDecimal perf1m;
  private String recommendma;
  private BigDecimal adxdin;
  private String recommendother;
  private BigDecimal adxdip;
  private BigDecimal relativevolume10dcalc;
  private BigDecimal stochd;
  private BigDecimal stochk;
  private BigDecimal stochrsik;
  private BigDecimal stochrsid;
  private BigDecimal uo;
  private BigDecimal volatilityd;
  private BigDecimal volatilitym;
  private BigDecimal volatilityw;
  private BigDecimal vwma;
  private BigDecimal perfytd;
  private String description;
  private Date updatedate;
  private String buyCall;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getCci20() {
    return cci20;
  }

  public void setCci20(BigDecimal cci20) {
    this.cci20 = cci20;
  }

  public String getRecommendall() {
    return recommendall;
  }

  public void setRecommendall(String recommendall) {
    this.recommendall = recommendall;
  }

  public BigDecimal getClose() {
    return close;
  }

  public void setClose(BigDecimal close) {
    this.close = close;
  }

  public BigDecimal getAroondown() {
    return aroondown;
  }

  public void setAroondown(BigDecimal aroondown) {
    this.aroondown = aroondown;
  }

  public BigDecimal getAroonup() {
    return aroonup;
  }

  public void setAroonup(BigDecimal aroonup) {
    this.aroonup = aroonup;
  }

  public BigDecimal getChaikinmoneyflow() {
    return chaikinmoneyflow;
  }

  public void setChaikinmoneyflow(BigDecimal chaikinmoneyflow) {
    this.chaikinmoneyflow = chaikinmoneyflow;
  }

  public BigDecimal getAdx() {
    return adx;
  }

  public void setAdx(BigDecimal adx) {
    this.adx = adx;
  }

  public BigDecimal getPerf3m() {
    return perf3m;
  }

  public void setPerf3m(BigDecimal perf3m) {
    this.perf3m = perf3m;
  }

  public BigDecimal getPerf6m() {
    return perf6m;
  }

  public void setPerf6m(BigDecimal perf6m) {
    this.perf6m = perf6m;
  }

  public BigDecimal getAo() {
    return ao;
  }

  public void setAo(BigDecimal ao) {
    this.ao = ao;
  }

  public BigDecimal getBbpower() {
    return bbpower;
  }

  public void setBbpower(BigDecimal bbpower) {
    this.bbpower = bbpower;
  }

  public BigDecimal getMacdmacd() {
    return macdmacd;
  }

  public void setMacdmacd(BigDecimal macdmacd) {
    this.macdmacd = macdmacd;
  }

  public BigDecimal getMacdsignal() {
    return macdsignal;
  }

  public void setMacdsignal(BigDecimal macdsignal) {
    this.macdsignal = macdsignal;
  }

  public BigDecimal getMom() {
    return mom;
  }

  public void setMom(BigDecimal mom) {
    this.mom = mom;
  }

  public BigDecimal getMoneyflow() {
    return moneyflow;
  }

  public void setMoneyflow(BigDecimal moneyflow) {
    this.moneyflow = moneyflow;
  }

  public BigDecimal getPerf1m() {
    return perf1m;
  }

  public void setPerf1m(BigDecimal perf1m) {
    this.perf1m = perf1m;
  }

  public String getRecommendma() {
    return recommendma;
  }

  public void setRecommendma(String recommendma) {
    this.recommendma = recommendma;
  }

  public BigDecimal getAdxdin() {
    return adxdin;
  }

  public void setAdxdin(BigDecimal adxdin) {
    this.adxdin = adxdin;
  }

  public String getRecommendother() {
    return recommendother;
  }

  public void setRecommendother(String recommendother) {
    this.recommendother = recommendother;
  }

  public BigDecimal getAdxdip() {
    return adxdip;
  }

  public void setAdxdip(BigDecimal adxdip) {
    this.adxdip = adxdip;
  }

  public BigDecimal getRelativevolume10dcalc() {
    return relativevolume10dcalc;
  }

  public void setRelativevolume10dcalc(BigDecimal relativevolume10dcalc) {
    this.relativevolume10dcalc = relativevolume10dcalc;
  }

  public BigDecimal getStochd() {
    return stochd;
  }

  public void setStochd(BigDecimal stochd) {
    this.stochd = stochd;
  }

  public BigDecimal getStochk() {
    return stochk;
  }

  public void setStochk(BigDecimal stochk) {
    this.stochk = stochk;
  }

  public BigDecimal getStochrsik() {
    return stochrsik;
  }

  public void setStochrsik(BigDecimal stochrsik) {
    this.stochrsik = stochrsik;
  }

  public BigDecimal getStochrsid() {
    return stochrsid;
  }

  public void setStochrsid(BigDecimal stochrsid) {
    this.stochrsid = stochrsid;
  }

  public BigDecimal getUo() {
    return uo;
  }

  public void setUo(BigDecimal uo) {
    this.uo = uo;
  }

  public BigDecimal getVolatilityd() {
    return volatilityd;
  }

  public void setVolatilityd(BigDecimal volatilityd) {
    this.volatilityd = volatilityd;
  }

  public BigDecimal getVolatilitym() {
    return volatilitym;
  }

  public void setVolatilitym(BigDecimal volatilitym) {
    this.volatilitym = volatilitym;
  }

  public BigDecimal getVolatilityw() {
    return volatilityw;
  }

  public void setVolatilityw(BigDecimal volatilityw) {
    this.volatilityw = volatilityw;
  }

  public BigDecimal getVwma() {
    return vwma;
  }

  public void setVwma(BigDecimal vwma) {
    this.vwma = vwma;
  }

  public BigDecimal getPerfytd() {
    return perfytd;
  }

  public void setPerfytd(BigDecimal perfytd) {
    this.perfytd = perfytd;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getUpdatedate() {
    return updatedate;
  }

  public void setUpdatedate(Date updatedate) {
    this.updatedate = updatedate;
  }

  public String getBuyCall() {
    return buyCall;
  }

  public void setBuyCall(String buyCall) {
    this.buyCall = buyCall;
  }
}
