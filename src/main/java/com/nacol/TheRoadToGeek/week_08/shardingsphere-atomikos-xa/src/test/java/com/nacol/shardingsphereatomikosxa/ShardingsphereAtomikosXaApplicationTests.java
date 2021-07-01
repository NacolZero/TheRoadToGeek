package com.nacol.shardingsphereatomikosxa;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.nacol.shardingsphereatomikosxa.entity.OrderPayment;
import com.nacol.shardingsphereatomikosxa.mapper.OrderPaymentMapper;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.beans.Transient;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ShardingsphereAtomikosXaApplicationTests {

	@Autowired
	OrderPaymentMapper orderPaymentMapper;

	@Autowired
	DataSource dataSource;

	@Test
	void testJdbcXA() {
		TransactionTypeHolder.set(TransactionType.XA); // 支持 TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
		try (Connection conn = dataSource.getConnection()) { // 使用 ShardingSphereDataSource
			conn.setAutoCommit(false);
			for (int i = 0; i < 100; i++) {
				if (i == 99) {
					int num = 1/0;
				}
				PreparedStatement ps = conn.prepareStatement("INSERT INTO order_payment (id, order_base_id) VALUES (?, ?)");
				ps.setObject(1, i);
				ps.setObject(2, i + new Random().nextInt(10));
				ps.execute();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testMyBatisPlusXA() {
		TransactionTypeHolder.set(TransactionType.XA);
		OrderPayment order = new OrderPayment()
				.setId((long)174)
				.setOrderBaseId((long)174)
				.setAmount(new BigDecimal(new Random().nextInt(1000)));
		orderPaymentMapper.insert(order);
		OrderPayment order2 = new OrderPayment()
				.setId((long)175)
				.setOrderBaseId((long)175)
				.setAmount(new BigDecimal(new Random().nextInt(1000)));
		orderPaymentMapper.insert(order2);

		int a = 1/0;
		OrderPayment order6 = new OrderPayment()
				.setId((long)176)
				.setOrderBaseId((long)176)
				.setAmount(new BigDecimal(new Random().nextInt(1000)));
		orderPaymentMapper.insert(order2);
	}

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
					.setId((long)i)
					.setOrderBaseId((long)i)
					.setAmount(new BigDecimal(new Random().nextInt(1000)));
			orderPaymentMapper.insert(order);
		}

		//STEP 全部搜索
		List<OrderPayment> list = new LambdaQueryChainWrapper<>(orderPaymentMapper)
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
				.set(OrderPayment::getAmount, 888)
				.update();


		//STEP 打印
		print();

//		//STEP 删除
//		delete();

		//STEP 打印
		print();

	}

	private void print() {
		//STEP 搜索
		List<OrderPayment> list = new LambdaQueryChainWrapper<>(orderPaymentMapper)
				.list();
		System.out.println("--------------------全部搜索 start ------------------------");
		list.forEach(System.out::println);
		System.out.println("--------------------全部搜索 end ------------------------");
	}

}
