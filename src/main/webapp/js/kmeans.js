var buttonGenerate = $("#buttonGenerate");
buttonGenerate.click(pointGenerator);
var buttonCluster = $("#buttonCluster");
buttonCluster.click(kmeans);


var width = 800;
var height = 500;
var points = [];
var centers = [];
var color = d3.scale.category20();


var svg = d3.select("body")
    .append("svg")
    .attr("width", width)
    .attr("height", height)
    .style("display", "block")
    .style("margin", "0 auto")
    .style("background-color", "#ffffff")
    .style("border", "1px solid black");

var svgnodes = svg.selectAll("circle");


function pointGenerator() {
    centers = [];
    points = [];
    svg.selectAll("circle").remove();

    $.ajax({
        url: "http://localhost:8080/visualization_war_exploded/kmeansDataGenerator",
        type: "get",
        dateType: "json",
        success: function (data) {
            clusters = data;
            for (var i = 0; i < clusters.length; i++) {
                centers.push(clusters[i].center);
                members = clusters[i].members;
                points = points.concat(members);
            }

            points.sort(function sortId(a, b) {
                return a.id - b.id
            });


            svgnodes.data(points)
                .enter()
                .append("circle")
                .attr("id", "point")
                .attr("r", 5)
                .transition()
                .duration(1000)
                .attr("cx", function (d) {
                    return d.x;
                }).attr("cy", function (d) {
                return d.y;
                })
                .style("fill", function (d) {
                    return color(d.clazz);
                });


            svgnodes.data(centers)
                .enter()
                .append("circle")
                .attr("id", "center")
                .attr("r", 5)
                .transition()
                .duration(1000)
                .attr("cx", function (d) {
                    return d.x;
                }).attr("cy", function (d) {
                return d.y;
            })
                .attr("fill", "red");
        },
        error: function (msg) {
        }
    });

}

function updateCenter(centers) {

    svg.selectAll("circle#center")
        .data(centers)
        .transition()
        .duration(1000)
        .attr("cx", function (d, i) {
            return d.x;

        })
        .attr("cy", function (d, i) {
            return d.y;
        });

}

function updatePoint(points) {
    svg.selectAll("circle#point")
        .data(points)
        .transition()
        .duration(1000)
        .style("fill", function (d) {
        return color(d.clazz);
    });
}
function kmeans() {
    $.ajax({
        url: "http://localhost:8080/visualization_war_exploded/doKmeans",
        type: "get",
        dateType: "json",
        success: function (data) {
            var kmeansClusters = data;

            for (var i = 0; i < kmeansClusters.length; i++) {
                centers = [];
                points = [];
                for (var j = 0; j < kmeansClusters[i].length; j++) {
                    centers.push(kmeansClusters[i][j].center);
                    points = points.concat(kmeansClusters[i][j].members);

                }

                points.sort(function sortId(a, b) {
                    return a.id - b.id
                });

                updatePoint(points);
                updateCenter(centers);
            }
        },
        error: function (msg) {
        }

    });

}


