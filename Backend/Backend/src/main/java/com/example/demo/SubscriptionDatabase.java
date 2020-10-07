package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.*;


@Repository
public interface SubscriptionDatabase extends JpaRepository<Subscription, Integer> {

}
