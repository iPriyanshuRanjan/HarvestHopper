package com.HarvestHopper.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int id;
    private String user_id;
    private BigDecimal total_price;
    private Date order_date;
    private String status;
}
