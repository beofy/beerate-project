package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.Projector;
import org.springframework.data.domain.Page;

public interface ProjectorService {

     Message<Page<Projector>> pageOfProjectList(int page, int size, String column, String order);
}
