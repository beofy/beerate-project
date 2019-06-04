package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.ProjectorList;
import org.springframework.data.domain.Page;

public interface ProjectorService {

     Message<Page<ProjectorList>> pageOfProjectList(int page, int size, String column, String order);
}
