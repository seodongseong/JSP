package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Order2;
import com.example.demo.Domain.Common.Entity.OrderId2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Order2Repository extends JpaRepository<Order2, OrderId2> {
}
