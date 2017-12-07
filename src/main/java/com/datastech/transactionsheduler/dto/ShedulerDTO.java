package com.datastech.transactionsheduler.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ivan salivon on 07.12.17.
 */
@Data
public class ShedulerDTO implements Serializable {

    private static final long serialVersionUID = -1193629980047872623L;

    private String routeId;
    private String status;
    private String cron;
    private String action;
}
