package com.gfbdev.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateFilter {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date endDate;

    public DateFilter() {
    }

    public DateFilter(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
