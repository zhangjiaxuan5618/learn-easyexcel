package com.kimmich.service;

import com.kimmich.domain.Bayernn;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kim
 * @since 2023-06-29
 */
@Service
@Transactional
public interface IBayernnService extends IService<Bayernn> {



}
