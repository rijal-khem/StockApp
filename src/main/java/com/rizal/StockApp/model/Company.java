package com.rizal.StockApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rizal.StockApp.entity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("symbol")
    private String tickerSymbol;




    public Company(){

    }

    public Company(String companyName, String tickerSymbol) {
        this.companyName = companyName;
        this.tickerSymbol = tickerSymbol;
    }



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName.toUpperCase();
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol.toUpperCase();
    }

    public Long getId() {
        return companyId;
    }

    public void setId(Long companyId) {
        this.companyId = companyId;
    }


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getCompanyName().equals(company.getCompanyName()) && getTickerSymbol().equals(company.getTickerSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyName(), getTickerSymbol());
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                '}';
    }
}
