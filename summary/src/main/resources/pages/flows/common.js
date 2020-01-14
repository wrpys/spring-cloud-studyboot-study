/**
 公共设置、方法
 */
$(function () {

    $('html,body').animate({"scrollTop": 0}, function () {
        jsPlumb.ready(function () {
            var flows = $(".diagramContainer");
            for (var i = 0; i < flows.length; i++) {
                var flow = flows[i]
                var steps = $(flow).find("div.jtk-endpoint-anchor.jtk-connected");
                for (var j = 0; j < steps.length - 1; j++) {
                    var stepLeft = steps[j];
                    var stepRight = steps[j + 1];
                    jsPlumb.connect({
                        source: $(stepLeft).attr("id"),
                        target: $(stepRight).attr("id"),
                        connector: ['Straight'],
                        anchor: ['Left', 'Right'],
                        overlays: [['Arrow', {location: 0.92, width: 12, length: 12}]],
                        endpoint: ["Dot", {radius: 4}],
                        paintStyle: {stroke: '#7B868C', strokeWidth: 1.5},
                        endpointStyle: {fill: '#7B868C', outlineWidth: 1}
                    })
                }
            }
        })
    });

    $(".item").click(function () {
        var stepContnt = $(this).parents("div.diagramContainer");
        stepContnt.find("div.jtk-endpoint-anchor.jtk-connected").removeClass("item-color");
        $(this).addClass("item-color");

        var taskId = $(this).attr("task-id");
        console.log(stepContnt);
        console.log(stepContnt.find(".step-task"));
        stepContnt.find(".step-task").hide();
        $("#" + taskId).show();
    });

    var flows = $(".diagramContainer");
    for (var i = 0; i < flows.length; i++) {
        var flow = flows[i]
        var steps = $(flow).find("div.jtk-endpoint-anchor.jtk-connected");
        // 默认选择第一个节点
        $(steps[0]).addClass("item-color");
        var taskId = $(steps[0]).attr("task-id");
        $("#" + taskId).show();
    }

})

// 返回概览
function toSummary() {
    window.location.href = "summary";
}

// 打开门户tab
function openTab(menuName, menuAddr) {
    parent.open_cct_tab(menuName, menuAddr);
}

// 打开浏览器tab
function openBrowserTab(url) {
    window.open(url, "_blank");
}