package com.zhaocheng.project.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ZC
 * @date 2020/8/19 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private Long id;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String createBy;

    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
