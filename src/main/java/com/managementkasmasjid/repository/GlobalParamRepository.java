package com.managementkasmasjid.repository;

import com.managementkasmasjid.entity.GlobalParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalParamRepository extends JpaRepository<GlobalParam,Long> {
    List<GlobalParam> findAllByParamCondition(String paramCondition);
    GlobalParam findByParamConditionAndParamDesc(String paramCondition, String paramDesc);
    List<GlobalParam> findAllByParamDesc(String paramDesc);
}
