<template>
  <div class="container-main" style="padding-top: 20px;background-color: #fff;">
      <div class="tpl-app app-main-content" v-loading="fullscreenLoading">
          <div class="header">
              服务列表
              <span style="font-size: 13px;color: #a5a5a5;">这里包含所有需要运营的能力服务列表</span>
          </div>
           
          <section class="section" v-for="(type, index) in productList" :key="index">
              <h2 class="section-title">
                  <i :class="type.banner" /> {{ type.name }}
              </h2>
              <div class="section-body">
                  <div class="app-item border-bottom" v-for="(item, index) in type.subList" :key="index">
                      <div class="app-item__icon wk wk-icon-user">
                          <i v-if="index % 2 == 0" style="color:#fff;" class="icon-img fas fa-feather fa-fw" />
                          <i v-if="index % 2 == 1" style="color:#fff;" class="fab fa-pinterest-p fa-fw" />
                      </div>
                      <div class="content">
                          <h3 class="app-item__title">
                              {{ item.name }}
                              <a class="cf-service-nav-item-label" style="margin-left:10px"
                                  @click="saveCollectProduct(item)" :title="item.name">
                                  <el-button type="text" size="medium"><i class="fa-regular fa-star"></i> 收藏</el-button>
                              </a>

                          </h3>
                          <div class="app-item__desc">
                              业务基础服务规划和建设，描述待补充
                          </div>
                      </div>
                      <div class="app-item__control">
                          <a class="cf-service-nav-item-label" @click="openService(item)" :title="item.name">
                              <el-button type="text" size="medium" icon="el-icon-link">打开服务</el-button>
                          </a>
                      </div>
                  </div>
              </div>
          </section>
      </div>
  </div>
</template>

<script setup name="ServiceList">

const productList = ref([
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "核心开发框架",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-common"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "前端开发框架（桌面）",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ui"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "前端开发框架（移动）",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-app"
                }
            ],
            "name": "核心框架",
            "banner": "fas fa-chart-area",
            "id": "1"
        },
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-mdm-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "主数据服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-mdm"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-report-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据上报服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-report"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-pipeline-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据集成服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-pipeline"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-dolphinscheduler-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据开发服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-dolphinscheduler"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-flink-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "实时计算服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-flink"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-security-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据安全服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-security"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-brain-quality.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据质量服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-quality"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-bus-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据总线服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-bus"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-assets-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据资产服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-assets"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-algorithm-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据算法服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-algorithm"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-data-fastapi-admin.beta.data.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "数据接口服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-data-fastapi"
                }
            ],
            "name": "数据服务",
            "banner": "fab fa-angular",
            "id": "fbd5d9a8d5f84c50b12235363f7f8e8b"
        },
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-authority-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "权限配置服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-authority"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-starter-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "代码生成器",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-init"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-config-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "分布式配置服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-config"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-message-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "事务消息服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-message"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-notices-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "通知管理服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-notices"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-storage-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "存储管理服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-storage"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-identity-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "单点登陆服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-identity"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-gateway-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "网关配置服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-gateway"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-validate-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "安全验证码服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-validate"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-im-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "IM消息服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-im"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-email-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "邮箱管理服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-email"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-document-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "文档搜索服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-document"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-id-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "分布式ID服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-id"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-sensitive-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "敏感词过滤服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-sensitive"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-pay-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "支付服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-pay"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-member-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "会员服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-member"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-cms-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "内容服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-cms"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-shop-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "商城服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-shop"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "流程中心服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-workflow"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-fileshow-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "文档查看服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-fileshow"
                }
            ],
            "name": "基础服务",
            "banner": "fas fa-chart-area",
            "id": "2"
        },
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "OCR视觉识别服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-smart-ocr"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "自然语言识别服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-smart-nlp"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "GPT推理服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-smart-brain"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "流媒体识别服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-smart-media"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "智能助手服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-smart-assistant"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "目标检测识别服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-smart-detection"
                }
            ],
            "name": "智能服务",
            "banner": "fas fa-chalkboard-teacher",
            "id": "fbd5d9a8d5f84c50b12235363f7f8e8b"
        },
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-ops-scheduler-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "自动化任务服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ops-scheduler"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-ops-logback-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "分布式日志服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ops-logback"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-ops-telemetry-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "分布式链路跟踪服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ops-telemetry"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-ops-pipeline-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "持续集成服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ops-pipeline"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-ops-container-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "持续集成服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ops-container"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-ops-watcher-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "监控预警服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-ops-watcher"
                }
            ],
            "name": "运维服务",
            "banner": "fas fa-chalkboard-teacher",
            "id": "fbd5d9a8d5f84c50b12235363f7f8e8b"
        },
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "商品秒杀服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-bus-limit"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "实时推荐服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-bus-recommend"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "实时画像服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-bus-profiling"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "无代码开发服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-bus-nocode"
                }
            ],
            "name": "业务服务",
            "banner": "fas fa-chalkboard-teacher",
            "id": "fbd5d9a8d5f84c50b12235363f7f8e8b"
        },
        {
            "subList": [
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-platform-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "基设平台管理服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-platform"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "安全感触服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-plat-security"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-smart-brain-admin.beta.smart.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "智能运营大脑服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-plat-brain"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-project-admin.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "项目管理服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-project"
                },
                {
                    "hasInner": 0,
                    "linkPath": "http://alinesno-infra-base-identity-auth-application.beta.base.infra.linesno.com",
                    "prodStatus": "normal",
                    "name": "认证授权服务",
                    "id": "c6fca12e348cccf97495b3aea33adcef",
                    "desc": "alinesno-infra-base-platform"
                }
            ],
            "name": "运营服务",
            "banner": "fas fa-chalkboard-teacher",
            "id": "fbd5d9a8d5f84c50b12235363f7f8e8b"
        }
    ]);
const fullscreenLoading = false;

// function getProductList() {
//   getProduct().then(response => {
//       console.log('response = ' + response);
//       productList.value = response.data ;
//   });
// };

function openService(item){
  var link = item.linkPath ;
  // 使用 window.open 在当前窗口打开链接
  window.open(link, '_self');
}

// 获取产品列表
// getProductList() ;


</script>

<style lang="scss" scoped>
.tpl-app {
  overflow: auto;
  color: #172b4d;
  background-color: #fff;
  padding: 10px;
  margin: 10px;
  /* max-width: 1240px; */
  width: 90%;
  margin: auto;

  .header {
      font-size: 24px;
      margin-left: 0px;
  }

  .popular {
      display: flex;
      align-content: center;
      justify-content: space-between;
      width: calc(100% - 20px);
      padding-bottom: 24px;
      margin-top: 24px;

      .popular-item {
          width: calc(25% - 20px);
          padding-right: 20px;
          cursor: pointer;
      }

      .popular-item__banner {
          width: 100%;
          border-radius: 4px;
      }

      .popular-item__title {
          margin: 20px 0 6px;
          font-size: 20px;

          i {
              font-size: 40px;
              color: #3b5998;
              margin-right: 10px;
          }
      }

      .popular-item__desc {
          font-size: 13px;
          color: #6b778c;
      }
  }

  .section {
      padding: 26px 20px 30px;

      &:last-child {
          border-bottom: 0 none;
      }

      .section-title {
          margin-left: -20px;
          font-size: 20px;
      }

      .section-body {
          display: flex;
          align-items: center;
          justify-content: flex-start;
          flex-wrap: wrap;
          width: 100%;
          margin: 0 -2.5%;

          .app-item {
              width: calc(33% - 20px);
              padding: 20px 0;
              margin: 0 10px;

              .content {
                  -webkit-box-flex: 1;
                  -ms-flex: 1;
                  flex: 1;
                  overflow: hidden;
              }
          }

          .app-item__icon {
              display: flex;
              align-items: center;
              justify-content: center;
              margin-top: 15px;
              width: 48px;
              height: 48px;
              margin-right: 15px;
              font-size: 30px;
              color: #fff;
              background-color: #3b5998;
              border-radius: 4px;
          }

          .app-item__control {
              margin-left: 15px;

              .plain-btn {
                  padding: 8px 12px;
                  color: #3b5998;
                  font-size: 13px;
                  cursor: pointer;
                  border: 1px solid #3b5998;
                  border-radius: 4px;
              }
          }

          .app-item__title {
              margin-bottom: 0px;
              font-size: 16px;
          }

          .app-item__desc {
              overflow: hidden;
              font-size: 13px;
              color: #6b778c;
              text-overflow: ellipsis;
              white-space: nowrap;
          }
      }
  }
}

.border-bottom {
  border-bottom: 1px solid #dfe1e6;
}

img {
  border-style: none;
}

.tpl-app .section .section-body .app-item,
.tpl-app .section .section-body {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.wk {
  font-family: wk !important;
  font-size: 16px;
  font-style: normal;
}

span.product-text-label {
  color: #3b5998;
  font-size: .7rem !important;
  border: 1px solid #3b5998;
  padding: 2px 5px;
  border-radius: 3px;
}

span.normal {
  color: #fe892c;
  border: 1px solid #fe892c;
}

span.design {
  color: #d5d5d5 !important;
  border: 1px solid #d5d5d5 !important;
}
</style>