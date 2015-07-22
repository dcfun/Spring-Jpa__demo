package junit.test;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcfun.bean.QueryResult;
import com.dcfun.bean.product.ProductType;
import com.dcfun.service.product.ProductTypeService;

public class ProductTest {

	private static ApplicationContext cxt;
	private static ProductTypeService productService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			productService = (ProductTypeService) cxt
					.getBean("productTypeServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 20; i++) {
			ProductType type = new ProductType();
			type.setName("����� " + (i + 1));
			type.setNote("�����שҵ");
			productService.save(type);
		}
	}

	@Test
	public void testFind() {
		ProductType type = productService.find(ProductType.class, 1);
		Assert.assertNotNull("û�ҵ�", type);
		System.out.println(type.getName() + "..." + type.getName());
	}

	@Test
	public void testUpdate() {
		ProductType type = productService.find(ProductType.class, 2);
		type.setName("������Ϣ����");
		type.setNote("�հ��ӵ�");
		productService.update(type);
	}

	@Test
	public void testDelete() {
		for (int i = 44; i < 64; i++) {
			productService.delete(ProductType.class, i);
		}

	}

	@Test
	public void testDelete2() {
		productService.delete(ProductType.class, new Object[] { 2 });
	}

	@Test
	public void testgetScrollData() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "asc");
		orderby.put("typeid", "asc");

		QueryResult<ProductType> qr = productService.getScrollData(
				ProductType.class, 0, 5, " where o.visible=?",
				new Object[] { true }, orderby);
		for (ProductType t : qr.getResultList()) {
			System.out.println(t.getName());
		}
	}

	@Test
	// ��������
	public void testgetScrollData2() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "asc");
		orderby.put("typeid", "asc");

		QueryResult<ProductType> qr = productService
				.getScrollData(ProductType.class);
		for (ProductType t : qr.getResultList()) {
			System.out.println(t.getName());
		}
	}
	
	@Test
	//��1��orderby
	public void testgetScrollData3() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "asc");
		orderby.put("typeid", "asc");

		QueryResult<ProductType> qr = productService.getScrollData(
				ProductType.class, 0, 5, orderby);
		for (ProductType t : qr.getResultList()) {
			System.out.println(t.getName());
		}
	}

	@Test
	//��1��where
	public void testgetScrollData4() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "asc");
		orderby.put("typeid", "asc");

		QueryResult<ProductType> qr = productService.getScrollData(
				ProductType.class, 0, 5, " where o.visible=?",
				new Object[] { true });
		for (ProductType t : qr.getResultList()) {
			System.out.println(t.getName());
		}
	}

	@Test
	//ֻ�з�ҳ
	public void testgetScrollData5() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "asc");
		orderby.put("typeid", "asc");

		QueryResult<ProductType> qr = productService.getScrollData(
				ProductType.class, 0, 5);
		for (ProductType t : qr.getResultList()) {
			System.out.println(t.getName());
		}
	}
}
