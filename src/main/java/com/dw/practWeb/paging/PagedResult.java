package com.dw.practWeb.paging;

import java.util.List;

public class PagedResult<T> {
  private List<T> results;

  private Integer rpp;

  private Integer pageNo;

  private String sortOrder;

  private String sortOn;

  private Long totalResults;

  private Integer totalPage;

  public PagedResult() {}

  public PagedResult(Integer rpp, String sortOrder, String sortOn) {
    super();
    this.rpp = rpp;
    this.sortOrder = sortOrder;
    this.sortOn = sortOn;
  }

  public List<T> getResults() {
    return results;
  }

  public void setResults(List<T> results) {
    this.results = results;
  }

  public Integer getRpp() {
    return rpp;
  }

  public void setRpp(Integer rpp) {
    this.rpp = rpp;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public String getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }

  public String getSortOn() {
    return sortOn;
  }

  public void setSortOn(String sortOn) {
    this.sortOn = sortOn;
  }

  public Long getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(Long totalResults) {
    this.totalResults = totalResults;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }
}
