package com.github.shenzhang.monitor.server.service;

import com.github.shenzhang.monitor.server.dao.SpanDao;
import com.github.shenzhang.monitor.server.domain.Span;
import com.github.shenzhang.monitor.server.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 11/7/14
 * Time: 4:25 PM
 */
@Service
public class CollectSpanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectSpanService.class);

    @Autowired
    private SpanDao spanDao;

    public void addSpan(Span span) {
        try {
            spanDao.addSpan(newArrayList(span));
        } catch (DaoException e) {
            LOGGER.error("Store span failed", e);
        }
    }
}
