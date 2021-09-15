package com.managementkasmasjid.repository;

import com.managementkasmasjid.entity.Dana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanaRepository extends JpaRepository<Dana,Long> {
}
