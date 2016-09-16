package com.testthree.message.model.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.testthree.message.model.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	@Query("select m from Message m where m.recipient like %?1%")
	List<Message> findByRecipient(String recipient);
}
