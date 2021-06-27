package com.nacol.shardingsphereorder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.nacol.shardingsphereorder.entity.OrderPayment;
import com.nacol.shardingsphereorder.mapper.OrderPaymentMapper;
import lombok.val;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ShardingsphereOrderApplicationTests {

	@Autowired
	OrderPaymentMapper orderPaymentMapper;

	@Test
	void delete() {
		orderPaymentMapper.delete(new QueryWrapper<OrderPayment>().eq("del_flag", 0));
	}

	@Test
	void contextLoads() {
		//STEP 新增
		System.out.println("新增");
		for (int i = 0; i < 100; i++) {
			OrderPayment order = new OrderPayment()
				.setOrderBaseId((long)i)
					.setAmount(new BigDecimal(new Random().nextInt(1000)));
			orderPaymentMapper.insert(order);
		}

		//STEP 全部搜索
		List<OrderPayment> list = new LambdaQueryChainWrapper<>(orderPaymentMapper)
				.eq(OrderPayment::getDelFlag, 0)
				.list();

		//STEP 打印
		print();

		//STEP 单个修改
		System.out.println("单个修改");
		for (OrderPayment order : list) {
			new LambdaUpdateChainWrapper<>(orderPaymentMapper)
					.eq(OrderPayment::getId, order.getId())
					.set(OrderPayment::getAmount, new BigDecimal(new Random().nextInt(10000)))
					.update();
		}

		//STEP 打印
		print();

		//STEP 批量修改
		System.out.println("批量修改");
		new LambdaUpdateChainWrapper<>(orderPaymentMapper)
				.eq(OrderPayment::getDelFlag, 0)
				.set(OrderPayment::getAmount, 888)
				.update();


		//STEP 打印
		print();

		//STEP 删除
		delete();

		//STEP 打印
		print();

	}

	private void print() {
		//STEP 搜索
		List<OrderPayment> list = new LambdaQueryChainWrapper<>(orderPaymentMapper)
				.eq(OrderPayment::getDelFlag, 0)
				.list();
		System.out.println("--------------------全部搜索 start ------------------------");
		list.forEach(System.out::println);
		System.out.println("--------------------全部搜索 end ------------------------");
	}
}
