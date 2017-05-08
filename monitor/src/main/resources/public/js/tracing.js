/** ----------------- Tracing ------------- */
function Tracing(spans) {
    this.spans = spans;
    this.root = spans[0];
}

Tracing.prototype.show = function (container) {
    if (typeof container === 'string') {
        this.container = $('#' + container);
    } else {
        this.container = container;
    }

    this.container.empty();

    // var table = $('<table style="width: 100%"><tr>' +
    //     '<td>Application</td>' +
    //     '<td>Status</td>' +
    //     '<td>Time Serial</td>' +
    //     '</tr></table>');
    //
    // this.container.append(table);

    var bar = new Bar(this, this.root, 0);
    bar.show(this.container);
}

/** ------------------ Duration Bar ----------------- */
/**
 * A duration bar widget
 * @param total
 * @param begin
 * @param duration
 * @constructor
 */
function Bar(tracing, span, depth) {
    this.tracing = tracing;
    this.rootSpan = tracing.root;
    this.span = span;
    this.depth = depth;
}

Bar.prototype.APP_WIDTH = 200;
Bar.prototype.MAX_DURATION_WIDTH = 800;

Bar.prototype.left = function () {
    // var offset = this.depth * 5;
    var offset = 0;

    for (var index = 0; index < arguments.length; index++) {
        offset += arguments[index];
    }
    return offset;
}

Bar.prototype.show = function (container) {
    if (typeof container === 'string') {
        this.container = $('#' + container);
    } else {
        this.container = container;
    }

    var bar = $('<div class="bar-container">' +
        '<div class="bar">' +
        '<div class="app"></div>' +
        '<div class="duration">&nbsp</div>' +
        '<div class="duration-info"></div>' +
        '</div>' +
        '<div class="bar-children"></div>' +
        '</div>');
    this.bar = bar;

    var offset = this.depth * 10;
    bar.find('.app').text(this.span.source).css('marginLeft', offset + 'px').css('width', Bar.prototype.APP_WIDTH - offset - 2 + 'px');

    var unit = this.MAX_DURATION_WIDTH / this.rootSpan.duration;
    var begin = this.span.beginTime - this.rootSpan.beginTime;
    begin = begin * unit;
    bar.find('.duration').css('left', this.left(Bar.prototype.APP_WIDTH, begin) + 'px').css('width', this.span.duration * unit + 'px');
    bar.find('.duration-info').css('left', this.left(Bar.prototype.APP_WIDTH, begin, 1) + 'px').text(this.span.duration + 'ms : ' + this.span.name);
    bar.find('.bar').attr('id', this.span.id);

    this.showChildren();
    this.container.append(bar);
}

Bar.prototype.showChildren = function () {
    var childrenContainer = this.bar.find('.bar-children').attr('id', this.span.id + '-children');

    var self = this;
    $.each(this.span.children, function (index, value) {
        var child = new Bar(self.tracing, value, self.depth + 1);
        child.show(childrenContainer);
    });
}

