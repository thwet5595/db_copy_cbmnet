package com.thwet.db_clone_cbmnet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thwet.db_clone_cbmnet.entity.Address;
import com.thwet.db_clone_cbmnet.entity.User;
import com.thwet.db_clone_cbmnet.service.cbmnet.AddressService;
import com.thwet.db_clone_cbmnet.service.cbs.UserService;

@SpringBootApplication
public class DbCloneCbmnetApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	public static void main(String[] args) {
		SpringApplication.run(DbCloneCbmnetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		processUsers();
		processAddress();
	}

	public void processUsers() {
		System.out.println("Start Users........");
		List<User> users = this.userService.findAll();
		System.out.println("Users size..." + users.size());
	}

	public void processAddress() {
		System.out.println("Start Address......");
		List<Address> address = this.addressService.findAll();
		System.out.println(" Address size..." + address.size());
		/*Address address = new Address();
		address.setName("Hledan");
		address.setTownship("Kamayut");
		this.addressService.save(address);*/
	}
}
