package com.viktor.repo;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viktor.model.Status;

@Repository
public interface StatusRepo extends CrudRepository<Status, String>{

	public List<Status> findByTag(String tag);
	public List<Status> findAll();
}
