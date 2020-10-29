package alphago.propertysale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @program: propertysale
 * @description: Auction Entity
 * @author: XIAO HAN
 * @create: 2020-10-15 15:35
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Auction {
    public final static String REGISTERED = "R";
    public final static String AUCTION = "A";
    public final static String SUCCESS = "S";
    public final static String FAIL = "F";

    @TableId(type = IdType.AUTO)
    private long aid;
    private long pid;
    private long seller;
    @TableField(value = "start_date")
    private LocalDateTime startdate;
    @TableField(value = "end_date")
    private LocalDateTime enddate;
    @TableField(value = "reserve_price")
    private String price;
    private String minimumPrice = "1000000";
    private long currentBid;
    private String status;
    private long bidderNum = 0;
}