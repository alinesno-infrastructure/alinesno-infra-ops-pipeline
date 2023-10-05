<template>
  <div class="app-container" v-loading="loading">
    <el-row>
      <el-col :span="24">
        <div class="top-header">
          <div class="header-icon-banner">
            <i class="fa-solid fa-ferry"></i>
          </div>
          <div class="icon">
            <div class="title">
              集群状态
              <div class="cluster-info">
                <span
                  ><i class="el-icon-monitor"></i>
                  {{ currentEnvClusterObj.clusterName }}</span
                >
                <span
                  ><i class="el-icon-link"></i> ApiServer:
                  {{ currentEnvClusterObj.apiServerUrl }}</span
                >
              </div>
            </div>
            <div class="title-desc">
              集群状态展示集群资源的概览和详情，您可以查看集群资源的监控数据和用量排行情况。
            </div>
          </div>
        </div>
      </el-col>
    </el-row>

    <!-- 集群信息_start -->
    <el-row>
      <el-col
        :span="7"
        style="width: calc(33.3333333333% - 25px) !important; margin-right: 10px">
        <div class="box-header">
          <div class="title">集群节点状态</div>
          <div class="box-body">
            <div class="sidecard-pie">
              <div
                id="echarts-pie-chart"
                :style="{ width: '150px', height: '150px' }"
              ></div>
            </div>
            <div class="sidecard">
              <div class="box-title">节点在线状态</div>
              <div>
                <div class="node-item">
                  在线节点: {{ currentEnvClusterObj.onlineNode }}
                </div>
                <div class="node-item">
                  全部节点: {{ currentEnvClusterObj.totalNode }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="box-header">
          <div class="title">组件状态</div>
          <div class="box-body" style="background-color: #fff">
            <ul>
              <li
                class="item-list"
                :class="item.status === '0' ? 'no-install-plugin' : ''"
                v-for="(item, index) in icons"
                :key="index"
              >
                <div class="item-icon">
                  <img :src="getImagePath(item.icon)" alt="icon" />
                </div>
                <div class="item-label" v-if="item.status === '1'">
                  <span style="font-weight: 600">{{ item.usage }}</span
                  >/<span style="font-size: 13px">{{ item.total }}</span>
                </div>
                <div class="item-label" v-if="item.status === '0'">
                  <span
                    style="padding-top: 10px; font-size: 13px"
                    disabled="disabled"
                    >未安装</span
                  >
                </div>
              </li>
            </ul>
          </div>
        </div>
      </el-col>
    </el-row>
    <!-- 集群信息_end -->

    <el-row>
      <el-col :span="24">
        <div class="box-header">
          <div class="title">集群资源用量</div>
          <el-row>
            <el-col :span="4">
              <div class="item-box">
                <ul>
                  <li
                    class="item-box-info"
                    :class="index === 0 ? 'active' : ''"
                    v-for="(item, index) in resources"
                    :key="index"
                  >
                    <div class="item-status">
                      <div
                        class="server-desc"
                        style="margin-top: 10px; margin-left: 10px"
                      >
                        <i :class="item.icon"></i>
                      </div>
                    </div>
                    <div class="status-info">
                      <div class="item-text">
                        {{ item.title }} ( {{ item.usagePre }}%)
                      </div>
                      <div class="item-num">
                        {{ item.usage }} /
                        <span class="total-num">{{ item.total }} </span>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </el-col>
            <el-col :span="20">
              <div class="sidecard-bar">
                <div
                  id="echarts-bar-chart"
                  :style="{ width: '100%', height: '270px' }"
                ></div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="box-header">
          <div class="title">服务组件监控</div>
          <el-row>
            <el-col :span="4">
              <div class="item-box">
                <ul>
                  <li
                    class="item-box-info"
                    :class="index === 0 ? 'active' : ''"
                    v-for="(item, index) in components"
                    :key="index"
                  >
                    <div class="item-status">
                      <div
                        class="server-desc"
                        style="margin-top: 10px; margin-left: 10px"
                      >
                        <i :class="item.icon"></i>
                      </div>
                    </div>
                    <div class="status-info">
                      <div class="item-text">{{ item.title }}</div>
                      <div class="item-num">
                        {{ item.usage }}
                        <span class="total-num">{{ item.total }}</span>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </el-col>
            <el-col :span="20">
              <div class="sidecard-bar">
                <div
                  id="echarts-bar2-chart"
                  :style="{ width: '100%', height: '270px' }"
                ></div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">

import * as echarts from "echarts";

const currentEnvClusterObj = ref({ 
        clusterName: "测试集群",
        apiServerUrl: "http://localhost:8001",
        totalNode: 2,
        onlineNode: 2,
      }) 
const loading = ref(false) 

/// 声明定义一下echart
let echart = echarts;


const icons = ref([
        {icon: "assets/icons/dashboard/kubernetes.svg",name: "Kubernetes",total: "3",usage: "3",status: "1",},
        {icon: "assets/icons/dashboard/istio.svg",name: "Istio",total: "1",usage: "1",status: "0",},
        {icon: "assets/icons/dashboard/monitoring.svg",name: "Monitoring",total: "10",usage: "10",status: "1",},
        {icon: "assets/icons/dashboard/logging.svg",name: "Logging",total: "5",usage: "5",status: "0",},
        {icon: "assets/icons/dashboard/dev-ops.svg",name: "Dev Ops",total: "4",usage: "4",status: "1",},
        {icon: "assets/icons/dashboard/logging.svg",name: "Logging",total: "4",usage: "4",status: "0",},
        {icon: "assets/icons/dashboard/monitoring.svg",name: "Monitoring",total: "7",usage: "7",status: "0",}
      ])  ;

const resources = ref([
        {
          icon: "fas fa-microchip",
          title: "CPU core",
          total: "4",
          usage: "0.64",
          usagePre: "12%",
        },
        {
          icon: "fas fa-memory",
          title: "内存 Gi",
          total: "7.68",
          usage: "4.81",
          usagePre: "12%",
        },
        {
          icon: "fas fa-hdd",
          title: "磁盘 GB",
          total: "21.57",
          usage: "207.71",
          usagePre: "12%",
        },
        {
          icon: "fab fa-docker",
          title: "容器组",
          total: "220",
          usage: "28",
          usagePre: "12%",
        },
      ]) ; 

const components = ref([
        {
          icon: "fas fa-tools",
          title: "API 服务器",
          total: "延迟",
          usage: "请求",
        },
        {
          icon: "fas fa-shipping-fast",
          title: "API 服务器",
          total: "速率",
          usage: "请求",
        },
        { icon: "fas fa-route", title: "调度器", total: "速率", usage: "调度" },
        {
          icon: "fab fa-firefox",
          title: "调度器",
          total: "次数",
          usage: "调度",
        },
      ]) ; 

function getImagePath(icon) {
      console.log("icon = " + icon);
      const imageUrl = new URL('/src/' + icon , import.meta.url)
      return imageUrl ;
} ;

function drawBar2() {
      let barChart = echart.init(
        document.getElementById("echarts-bar2-chart")
      );

      var lineOption = {
        title: {
          text: "请求延迟(ms)",
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["访问流量", "访问IP"],
        },
        grid: {
          x: 40,
          x2: 40,
          y2: 24,
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: [
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
            ],
          },
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              formatter: "{value} K",
            },
          },
        ],
        series: [
          {
            name: "访问流量",
            type: "line",
            smooth: true,
            data: [
              11, 10, 12, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 12, 13,
              5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 15, 13, 5, 13, 8, 11,
              10, 12, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 15, 13, 5,
              13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 12, 13, 5, 13, 8, 11, 9,
              15, 8, 12, 8, 8, 11, 10, 15, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8,
              11, 9, 15, 8, 12, 8, 8, 11, 10, 12, 13, 5, 13, 8, 11, 9, 15, 8,
              12, 8, 8, 11, 10, 15, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11,
              10, 15, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8,
            ],
            markPoint: {
              data: [
                { type: "max", name: "最大值" },
                { type: "min", name: "最小值" },
              ],
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }],
            },
          },
          {
            name: "访问IP",
            type: "line",
            smooth: true,
            data: [
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0,
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0,
              8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7,
              1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7,
            ],
            markPoint: {
              data: [{ name: "周最低", value: -2, xAxis: 1, yAxis: -1.5 }],
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }],
            },
          },
        ],
      };
      barChart.setOption(lineOption);
    } ; 

function drawBar() {
      let barChart = echart.init(document.getElementById("echarts-bar-chart"));
      var lineOption = {
        title: {
          text: "CPU 用量(%)",
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["访问流量", "访问IP"],
        },
        grid: {
          x: 40,
          x2: 40,
          y2: 24,
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: [
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
            ],
          },
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              formatter: "{value} K",
            },
          },
        ],
        series: [
          {
            name: "访问流量",
            type: "line",
            smooth: true,
            data: [
              11, 10, 12, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 12, 13,
              5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 12, 13, 5, 13, 8, 11, 9,
              15, 8, 12, 8, 8, 11, 10, 12, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8,
              11, 10, 15, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 15, 13,
              5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 15, 13, 5, 13, 8, 11, 9,
              15, 8, 12, 8, 8, 11, 10, 12, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8,
              11, 10, 15, 13, 5, 13, 8, 11, 9, 15, 8, 12, 8, 8, 11, 10, 15, 13,
              5, 13, 8, 11, 9, 15, 8, 12, 8, 8,
            ],
            markPoint: {
              data: [
                { type: "max", name: "最大值" },
                { type: "min", name: "最小值" },
              ],
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }],
            },
          },
          {
            name: "访问IP",
            type: "line",
            smooth: true,
            data: [
              8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7, 1, 2, 2, 5, 3, 2, 0,
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7,
              1, 2, 2, 5, 3, 2, 0, 8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7,
              8, 2, 7, 5, 9, 10, 7, 8, 2, 7, 5, 9, 10, 7,
            ],
            markPoint: {
              data: [{ name: "周最低", value: -2, xAxis: 1, yAxis: -1.5 }],
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }],
            },
          },
        ],
      };

      barChart.setOption(lineOption);
    } ;

function drawPie() {
      let barChart = echart.init(document.getElementById("echarts-pie-chart"));

      let lineOption = {
        tooltip: {
          trigger: "item",
        },
        legend: {
          top: "5%",
          left: "center",
          show: false,
        },
        series: [
          {
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: false,
                fontSize: 40,
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: 1048, name: "运行" },
              { value: 348, name: "异常" },
            ],
          },
        ],
      };
      barChart.setOption(lineOption);
    } ;

onMounted(() => {
  drawPie() ;
  drawBar2() ;
  drawBar() ;
});

onUnmounted(() => {
  echart.dispose;
});

</script>

<style lang="scss" scoped>
.top-header {
  position: relative;
  padding-bottom: 24px;
  overflow: hidden;

  .header-icon-banner {
    float: left;
    font-size: 2.4rem;
    margin-right: 10px;
    color: #3b5998;
  }

  .icon {
    float: left;
  }

  .title {
    display: block;
    font-weight: 600;
    font-style: normal;
    font-size: 24px;
    color: #242e42;
    text-shadow: 0 4px 8px rgba(36, 46, 66, 0.1);
    margin-bottom: 10px;
  }

  .title-desc {
    color: #79879c !important;
    font-size: 12px;
  }
}

.cluster-info {
  float: right;
  position: relative;
  font-size: 14px;
  margin-left: 10px;
  font-weight: bold;
  margin-top: 10px;
  color: #3b5998;

  span {
    margin-left: 20px;
  }
}

.node-item {
  font-size: 12px;
  margin-bottom: 5px;
}

.no-install-plugin {
  opacity: 0.5;
}

.item-box {
  ul {
    list-style: none;
    padding: 0px;
    margin: 0px;

    .server-desc {
      font-size: 1.6rem;
      color: #3b5998 ; 
    }

    .active {
      background: #3b5998 !important;
      color: #fff !important;

      .server-desc {
        color: #fff !important;
      }
    }

    li.item-box-info {
      padding: 8px;
      float: left;
      width: 100%;
      background: #f9fbfd;
      border-radius: 5px;
      color: #222;
      margin-bottom: 5px;

      .item-status {
        float: left;
      }

      .status-info {
        float: left;
        margin-left: 20px;

        .item-text {
          margin-bottom: 5px;
          font-size: 12px;
          font-weight: 600;
          line-height: 1.67;
        }

        .item-num {
          height: 22px;
          line-height: 22px;
          font-size: 18px;
          font-weight: 600;

          .total-num {
            font-size: 14px;
          }
        }
      }
    }
  }
}

.sidecard-bar {
  padding: 20px;
}

.item-list {
  .item-icon {
    img {
      width: 94px;
      height: 20px;
    }
  }
}

.box-header {
  .title {
    position: relative;
    height: 20px;
    margin-bottom: 20px;
    font-size: 14px;
    font-weight: 600;
    line-height: 1.43;
    zoom: 1;
  }

  .box-body {
    // padding: 20px;
    float: left;
    width: 100%;
    border-radius: 5px;
    background: #f9fbfd;

    ul {
      list-style: none;
      padding: 0px;
      margin: 0px;

      li.item-list {
        float: left;
        width: calc(33% - 10px);
        margin-right: 10px;
        background: #f9fbfd;
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 10px;

        .item-icon {
          float: left;
        }

        .item-label {
          float: right;
        }
      }
    }

    .sidecard-pie {
      float: left;
      margin-left: 30px;
    }

    .sidecard {
      float: left;
      display: flex;
      flex-direction: column;
      justify-content: center;
      margin-left: 45px;
      margin-top: 40px;

      .box-title {
        font-size: 16px;
        font-weight: 600;
        line-height: 1.43;
        margin-bottom: 5px;
      }
    }
  }
}
</style>
