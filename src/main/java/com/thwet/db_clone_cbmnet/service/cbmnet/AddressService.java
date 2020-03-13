package com.thwet.db_clone_cbmnet.service.cbmnet;

import java.util.List;

import com.thwet.db_clone_cbmnet.entity.Address;

public interface AddressService {

	List<Address> findAll();
	
	void save(Address address);
}
