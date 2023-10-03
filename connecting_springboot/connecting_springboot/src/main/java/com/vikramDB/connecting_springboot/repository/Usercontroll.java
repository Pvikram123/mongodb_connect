package com.vikramDB.connecting_springboot.repository;

import com.vikramDB.connecting_springboot.userdata.Userinformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Usercontroll extends MongoRepository<Userinformation ,String > {


}
