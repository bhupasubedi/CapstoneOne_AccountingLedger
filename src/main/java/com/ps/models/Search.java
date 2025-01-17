package com.ps.models;

import java.time.LocalDate;

public class Search {
    private int minAmount;
    private int maxAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String vendor;
    private String description;

    public Search() {}

    public Search(int minAmount, int maxAmount, LocalDate startDate, LocalDate endDate, String vendor, String description) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vendor = vendor;
        this.description = description;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Search{" +
                "minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vendor='" + vendor + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
