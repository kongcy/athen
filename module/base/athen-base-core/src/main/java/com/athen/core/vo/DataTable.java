package com.athen.core.vo;

import com.athen.core.json.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/*
 * 前端dataTable.js分页参数
 * 
 */
public class DataTable<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	//-- 分页参数 --//
    protected int sEcho=1;
    protected int iDisplayStart=0;
    protected int iDisplayLength=20;
    protected long iTotalRecords;
    protected long iTotalDisplayRecords=0L;
    protected List<T> aaData;//返回结果
    private String sortValue;
    private String sortColName;
    private String sSearch;
    private String iSortCol_0;
    private String sSortDir_0;
    public int getsEcho(){
        return sEcho;
    }
    public void setsEcho(int sEcho){
        this.sEcho=sEcho;
    }
    public int getiDisplayStart(){
        return iDisplayStart;
    }
    public void setiDisplayStart(int iDisplayStart){
        this.iDisplayStart=iDisplayStart;
    }
    public int getiDisplayLength(){
        if(iDisplayLength==-1){
            iDisplayLength=Integer.MAX_VALUE;
        }
        return iDisplayLength;
    }
    public void setiDisplayLength(int iDisplayLength){
        this.iDisplayLength=iDisplayLength;
    }
    public long getiTotalRecords(){
        return iTotalRecords;
    }
    public void setiTotalRecords(long iTotalRecords){
        this.iTotalRecords=iTotalRecords;
    }
    public long getiTotalDisplayRecords(){
        return iTotalDisplayRecords;
    }
    public void setiTotalDisplayRecords(long iTotalDisplayRecords){
        this.iTotalDisplayRecords=iTotalDisplayRecords;
    }
    public List<T> getAaData(){
        return aaData;
    }
    public void setAaData(List<T> aaData){
        this.aaData=aaData;
    }
    public String getsSearch(){
        return sSearch;
    }
    public void setsSearch(String sSearch){
        if(sSearch!=null){
            this.sSearch=sSearch.trim();
        }else{
            this.sSearch=null;
        }
    }
    public String getSortColName(){
        return sortColName;
    }
    public void setSortColName(String sortColName){
        this.sortColName=sortColName;
    }
    public String getSortValue(){
        return sortValue;
    }
    public void setSortValue(String sortValue){
        this.sortValue=sortValue;
    }
    public String getiSortCol_0(){
        return iSortCol_0;
    }
    public void setiSortCol_0(String iSortCol_0){
        this.iSortCol_0=iSortCol_0;
    }
    public String getsSortDir_0(){
        return sSortDir_0;
    }
    public void setsSortDir_0(String sSortDir_0){
        this.sSortDir_0=sSortDir_0;
    }
    public int pageNo(){
        if(this.getiDisplayLength()!=0){
            return this.iDisplayStart/this.getiDisplayLength()+1;
        }
        return -1;
    }

   public void toJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJsonNoPage(this));
    }


}
