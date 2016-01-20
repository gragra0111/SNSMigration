package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleArticleDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8568298657619436324L;
	private String temp_article_id;
	private String article_id;
	private String share_article_id;
	private String article_type;
	private String ctnt;
	private String ctnt_raw;
	private String link_url;
	private String link_site;
	private String link_thumb;
	private String link_title;
	private String link_sum;
	private String del_stat;
	private String create_id;
	private String create_dt;
	private String update_id;
	private String update_dt;
	
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getShare_article_id() {
		return share_article_id;
	}
	public void setShare_article_id(String share_article_id) {
		this.share_article_id = share_article_id;
	}
	public String getArticle_type() {
		return article_type;
	}
	public void setArticle_type(String article_type) {
		this.article_type = article_type;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public String getCtnt_raw() {
		return ctnt_raw;
	}
	public void setCtnt_raw(String ctnt_raw) {
		this.ctnt_raw = ctnt_raw;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getLink_site() {
		return link_site;
	}
	public void setLink_site(String link_site) {
		this.link_site = link_site;
	}
	public String getLink_thumb() {
		return link_thumb;
	}
	public void setLink_thumb(String link_thumb) {
		this.link_thumb = link_thumb;
	}
	public String getLink_title() {
		return link_title;
	}
	public void setLink_title(String link_title) {
		this.link_title = link_title;
	}
	public String getLink_sum() {
		return link_sum;
	}
	public void setLink_sum(String link_sum) {
		this.link_sum = link_sum;
	}
	public String getDel_stat() {
		return del_stat;
	}
	public void setDel_stat(String del_stat) {
		this.del_stat = del_stat;
	}
	public String getCreate_id() {
		return create_id;
	}
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	public String getTemp_article_id() {
		return temp_article_id;
	}
	public void setTemp_article_id(String temp_article_id) {
		this.temp_article_id = temp_article_id;
	}
	
}
