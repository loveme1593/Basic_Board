package moonkyung.park.board.repository;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import moonkyung.park.board.dao.CustomerDAO;
import moonkyung.park.board.vo.Customer;

@Repository
public class CustomerRepository {

	@Inject
	private SqlSession sqlSession;

	CustomerDAO cdao;

	private static final String namespace = "moonkyung.park.board.mappers.customerMapper";

	public int insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int result = 0;
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			result = cdao.insertCustomer(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Customer selectCustomer(String cus_id) {
		Customer customer = new Customer();
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			customer = cdao.selectCustomer(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public String selectNickname(String cus_id) {
		String nickname = "";
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			nickname = cdao.selectNickname(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nickname;
	}
}
