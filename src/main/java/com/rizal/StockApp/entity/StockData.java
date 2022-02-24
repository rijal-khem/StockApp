package com.rizal.StockApp.entity;


import com.rizal.StockApp.model.Company;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class StockData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockDataId;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal price;
    private BigDecimal volume;
    private LocalDate date;

    @ManyToOne
    private Company company;

    public Long getStockDataId() {
        return stockDataId;
    }

    public void setStockDataId(Long stockDataId) {
        this.stockDataId = stockDataId;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
