package com.managementkasmasjid.repository;

import com.managementkasmasjid.entity.CommonUser;
import com.managementkasmasjid.entity.GlobalParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser,Long> {
    CommonUser findByName(String name);
    List<CommonUser> findAllByCategoryCommonUser(GlobalParam categoryCommonUser);
}
