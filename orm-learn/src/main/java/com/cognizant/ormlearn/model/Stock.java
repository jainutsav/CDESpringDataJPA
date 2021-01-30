package com.cognizant.ormlearn.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;;

@Entity
@Table(name = "stock")
public class Stock {
	@Id
	@Column(name = "st_id")
	private int id;

	@Column(name = "st_code")
	private String code;

	@Column(name = "st_date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "st_open", precision=10, scale=2)
	private BigDecimal open;
	
	@Column(name = "st_close", precision=10, scale=2)
	private BigDecimal close;
	
	@Column(name = "st_volume", precision=18, scale=0)
	private BigDecimal volume;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Stock [code=" + code + ", date=" + date + ", open=" + open + ", close=" + close + ", volume=" + volume
				+ "]";
	}



}
