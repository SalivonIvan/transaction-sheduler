package com.datastech.transactionsheduler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author ivan
 */
@Data
//@JsonInclude(Include.NON_NULL)
public class TransferDTO implements Serializable{
    private Integer _id;
    private String name;
    private String data;
}
