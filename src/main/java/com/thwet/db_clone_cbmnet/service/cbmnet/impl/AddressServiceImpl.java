package com.thwet.db_clone_cbmnet.service.cbmnet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thwet.db_clone_cbmnet.entity.Address;
import com.thwet.db_clone_cbmnet.repository.cbmnet.AddressRepository;
import com.thwet.db_clone_cbmnet.service.cbmnet.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Override
	@Transactional("cbmnetTransactionManager")
	public List<Address> findAll() {
		return this.addressRepository.findAll();
	}

	@Override
	@Transactional("cbmnetTransactionManager")
	public void save(Address address) {
		this.addressRepository.save(address);
	}

}
