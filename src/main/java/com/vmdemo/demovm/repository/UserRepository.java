package com.vmdemo.demovm.repository;

import org.springframework.data.repository.CrudRepository;

import com.vmdemo.demovm.model.uservo;;
public interface UserRepository extends CrudRepository<uservo, Integer>
{
	

}
