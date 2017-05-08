$(function () {
    function showTrace(traceId) {
        $.getJSON('traces/' + traceId, function (spans) {
            var spanList = [];
            processSpans(spans, null, spanList);

            var tracing = new Tracing(spanList);
            tracing.show('tracing-container');
        });
    }

    // process span list to a tree structure with spans in the same level sorted.
    function processSpans(spans, parentId, list) {
        $.each(spans, function (index, span) {
            if (span.parentId == parentId) {
                list.push(span);

                span.children = [];
                processSpans(spans, span.id, span.children);

                spans.sort(function(a, b) {
                    return a.beginTime - b.beginTime;
                });
            }
        });
    }

    showTrace('bfca8a1893b11a7a');
});