package cn.beerate.dao;

import cn.beerate.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IBaseDao<T extends Model> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {


}
