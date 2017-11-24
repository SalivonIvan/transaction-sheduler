package com.datastech.transactionsheduler;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author ivan
 */
@Data
public class TransferDTO implements Serializable{
    private String name;
    private String data;
}
