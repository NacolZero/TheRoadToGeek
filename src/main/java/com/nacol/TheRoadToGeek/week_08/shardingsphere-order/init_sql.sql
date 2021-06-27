#drop schema geek0;
#drop schema geek1;

create schema geek1;
use geek1;
CREATE TABLE `order_payment_0` (
                                    `id` bigint DEFAULT NULL COMMENT '付款id',
                                    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
                                    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
                                    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
                                    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
                                    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
                                    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
                                    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
                                    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
                                    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_1` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_2` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_3` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_4` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_5` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_6` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_7` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_8` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_payment_9` (
    `id` bigint DEFAULT NULL COMMENT '付款id',
    `order_base_id` bigint DEFAULT 0 COMMENT '运单主表 id',
    `payment_type` smallint DEFAULT '3' COMMENT '付款类型：0.银行卡；1.信用卡；2.花呗；3.支付宝；',
    `user_bank_id` bigint DEFAULT 0 COMMENT '用户本次支付的银行卡 id',
    `alipy_id` bigint DEFAULT 0 COMMENT '用户本次支付支付宝 id',
    `amount` decimal(10,2) DEFAULT '0.00' COMMENT '付款金额',
    `seller_id` bigint DEFAULT 0 COMMENT '卖家ID',
    `create_time` bigint DEFAULT 0 COMMENT '创建时间',
    `update_time` bigint DEFAULT 0 COMMENT '修改时间',
    `del_flag` smallint DEFAULT 0 COMMENT '删除标记：0.未删除; 1.删除;'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
