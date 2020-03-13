package com.thwet.db_clone_cbmnet.service.cbs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thwet.db_clone_cbmnet.entity.User;
import com.thwet.db_clone_cbmnet.repository.cbs.UserRepository;
import com.thwet.db_clone_cbmnet.service.cbs.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional("cbsTransactionManager")
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

}
