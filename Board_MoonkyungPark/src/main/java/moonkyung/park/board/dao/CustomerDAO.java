package moonkyung.park.board.dao;

import moonkyung.park.board.vo.Customer;

public interface CustomerDAO {

	// 회원 가입
	public int insertCustomer(Customer customer) throws Exception;

	// 회원 검색
	public Customer selectCustomer(String cus_id) throws Exception;

	// 닉넴검색
	public String selectNickname(String cus_id) throws Exception;

}
