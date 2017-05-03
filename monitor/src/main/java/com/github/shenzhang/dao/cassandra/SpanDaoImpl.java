package com.github.shenzhang.dao.cassandra;

import com.datastax.driver.core.*;
import com.github.shenzhang.dao.SpanDao;
import com.github.shenzhang.domain.Span;
import com.github.shenzhang.exception.DaoException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/2/17
 * Time: 4:27 PM.
 */
@Repository
public class SpanDaoImpl implements SpanDao, InitializingBean {
    @Autowired
    private Session session;

    private PreparedStatement insertStatement;
    private PreparedStatement searchByTraceStatement;

    @Override
    public void addSpan(List<Span> spans) throws DaoException {
        for (Span span : spans) {
            BoundStatement bind = insertStatement.bind(span.getId(), span.getTraceId(), span.getParentId(),
                    span.getSource(), span.getBegin(), span.getEnd(), span.getDuration());
            session.execute(bind);
        }
    }

    public List<Span> getSpanByTraceId(String traceId) throws DaoException {
        BoundStatement bind = searchByTraceStatement.bind(traceId);
        ResultSet resultSet = session.execute(bind);

        List<Span> spans = new ArrayList<>();
        for (Row row : resultSet) {
            Span span = new Span();
            span.setId(row.getString("id"));
            span.setParentId(row.getString("parent_id"));
            span.setTraceId(row.getString("trace_id"));
            span.setSource(row.getString("source"));
            span.setBegin(row.getLong("begin"));
            span.setEnd(row.getLong("end"));
            span.setDuration(row.getInt("duration"));

            spans.add(span);
        }

        return spans;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        insertStatement = session.prepare(
                "insert into span(id, trace_id, parent_id, source, begin_time, end_time, duration) " +
                        "values(?, ?, ?, ?, ?, ?, ?)");

//        searchByTraceStatement = session.prepare("select * from span where trace_id = ?");
    }
}
